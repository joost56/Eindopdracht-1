package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.domain.exception.PasswordIncorrectException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import nl.hu.eindopdracht1.domain.service.UserService;
import nl.hu.eindopdracht1.web.dto.UserDto;
import nl.hu.eindopdracht1.web.dto.UserIdDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/logIn")
    public boolean logIn(@RequestBody UserDto userDto) throws UserNotFoundException, PasswordIncorrectException {
        return userService.logIn(userDto.getUsername(), userDto.getPassword());
    }

    @PostMapping("/getUser")
    public boolean getUserById(@RequestBody UserIdDto userIdDto) {
        try {
            userService.findUserById(userIdDto.getUserId());
            return true;
        }catch (UserNotFoundException e) {
            return false;
        }
    }
}
