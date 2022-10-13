package nl.hu.eindopdracht1.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.domain.PasswordIncorrectException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import nl.hu.eindopdracht1.domain.service.UserService;
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
    public User register(@RequestBody String username, String password){
        return userService.register(username, password);
    }

    @GetMapping("/logIn")
    public boolean logIn(@RequestBody String username, String password) throws UserNotFoundException, PasswordIncorrectException {
        return userService.logIn(username, password);
    }
}
