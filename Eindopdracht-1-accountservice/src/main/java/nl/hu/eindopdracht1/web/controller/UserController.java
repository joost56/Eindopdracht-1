package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.domain.exception.PasswordIncorrectException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import nl.hu.eindopdracht1.domain.service.UserService;
import nl.hu.eindopdracht1.web.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDto userDto){
        return userService.register(userDto.getUsername(), userDto.getPassword());
    }

    @PostMapping("/login")
    public boolean logIn(@RequestBody UserDto userDto) throws UserNotFoundException, PasswordIncorrectException {
        return userService.logIn(userDto.getUsername(), userDto.getPassword());
    }

    @GetMapping("/{id}")
    public boolean getUserById(@PathVariable String id) {
        try {
            userService.findUserById(id);
            return true;
        }catch (UserNotFoundException e) {
            return false;
        }
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
