package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        return userRepository.save(user);
    }

//    public User logIn(String username, String password) {
//
//    }
}
