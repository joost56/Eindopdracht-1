package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.model.HttpResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Captor
    ArgumentCaptor<String> uriArgumentCaptor;

    @Captor
    ArgumentCaptor<String> userIdArgumentCaptor;

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

//    //Given
//    User user = new User("user1", "pass");
//        Mockito.when(userRepository.save(userArgumentCaptor.capture())).thenReturn(user);
//
//    //When
//    final User userRes = userService.save(user);
//
//    //Then
//        Mockito.verify(userRepository, Mockito.times(1)).save(user);
//    assertThat(userRes).isEqualTo(user);
//    assertThat(userArgumentCaptor.getValue()).isEqualTo(user);

//    @Test
//    void assignTaskToUserAndUserToTask() throws IOException, InterruptedException {
//        //Given
//        Column column = new Column("kolom1");
//        Task task = new Task("dit is een task", column);
//        Mockito.when(userService.getUserById(uriArgumentCaptor.capture(), userIdArgumentCaptor.capture())).thenReturn(HttpResponse.response("true"));
//
//    }
}