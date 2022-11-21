package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.PasswordIncorrectException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserById(String userId) throws UserNotFoundException {
        return this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User register(String username, String password) {
        User user = new User(username, password);
        return save(user);
    }

    public boolean logIn(String username, String password) throws UserNotFoundException, PasswordIncorrectException {
        User user = findUserById(username);
        if (Objects.equals(user.getPassword(), password)) {
            return true;
        }
        throw new PasswordIncorrectException(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
