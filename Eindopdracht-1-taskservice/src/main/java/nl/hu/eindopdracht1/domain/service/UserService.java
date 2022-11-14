package nl.hu.eindopdracht1.domain.service;

import com.nimbusds.jose.shaded.gson.Gson;
import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.config.ConfigUri;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private HttpClient client = HttpClient.newHttpClient();
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Autowired
    private ConfigUri configUri;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findUserById(String username) throws UserNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public HttpResponse<String> getUserById(String uri, String userId) throws IOException, InterruptedException {
        String body = new Gson().toJson(Map.of("userId", userId));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .uri(URI.create(uri))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public List<User> assignTaskToUserAndUserToTask(String username, Long taskId) throws UserNotFoundException, TaskNotFoundException, IOException, InterruptedException, TaskAlreadyAssignedToUser {
        boolean userExists = Boolean.parseBoolean(getUserById(configUri.getUri(), username).body());
        if (userExists) {
            if (!userRepository.findById(username).isPresent()) {
                save(new User(username));
            }
            User user = findUserById(username);
            Task task = taskService.findTaskById(taskId);
            System.out.println(user.getTasks());
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
