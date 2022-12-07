package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    TaskService taskService;

    @Mock
    HttpRequestService httpRequestService;

    @Mock
    UserRepository userRepository;

    @Test
    void findUserWithNoExistingUser() {
        // When
        Throwable thrown = catchThrowable(() -> {
            final User userResult = userService.findUserById("IDONOTEXIST");
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User with id IDONOTEXIST does not exist.");
    }

    @Test
    void assignTaskToUserAndUserToTask() throws IOException, InterruptedException, UserNotFoundException, TaskNotFoundException, TaskAlreadyAssignedToUser {
        //Given
        Column column = new Column("kolom1");
        Task task = new Task(1L, "dit is een task", column);
        User user = new User("username");
        Mockito.when(httpRequestService.userExists("username")).thenReturn(true);
        Mockito.when(userRepository.findById("username")).thenReturn(java.util.Optional.of(user));
        Mockito.when(taskService.findTaskById(1L)).thenReturn(task);

        //When
        List<User> res = userService.assignTaskToUserAndUserToTask("username", 1L);

        //then
        assertThat(res.size() == 1);
        assertThat(res.get(0)).isEqualTo(user);
    }
}