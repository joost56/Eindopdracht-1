package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final TaskService taskService;
    private final HttpRequestService requestService;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findUserById(String username) throws UserNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    //only for test purposes
    public String findUserByIdAccountService(String username) {
        return requestService.getUserById("http://localhost:9080/users/", username).getBody();
    }

    public List<User> assignTaskToUserAndUserToTask(String username, Long taskId) throws UserNotFoundException, TaskNotFoundException, TaskAlreadyAssignedToUser {
        if (requestService.userExists(username)) {
            if (!userRepository.findById(username).isPresent()) {
                save(new User(username));
            }
            User user = findUserById(username);
            Task task = taskService.findTaskById(taskId);
            if (user.getTasks().contains(task)) {
                throw new TaskAlreadyAssignedToUser(taskId, username);
            }
            user.getTasks().add(task);
            task.getUsers().add(user);
            save(user);
            taskService.save(task);
            return task.getUsers();
        }
        throw new UserNotFoundException(username);
    }
}
