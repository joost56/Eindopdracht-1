package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.PasswordIncorrectException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;
    @Captor
    ArgumentCaptor<User> userArgumentCaptor;
//    @Test
//    public void testAccountCreationSuccessful() {
//        //Given
//        User user = new User("user1", "pass");
//        Mockito.when(userRepository.save(userArgumentCaptor.capture())).thenReturn(user);
//
//        //When
//        final User userRes = userService.save(user);
//
//        //Then
//        Mockito.verify(userRepository, Mockito.times(1)).save(user);
//        assertThat(userRes).isEqualTo(user);
//        assertThat(userArgumentCaptor.getValue()).isEqualTo(user);
//    }

    @Test
    public void testLogInSuccessful() throws UserNotFoundException, PasswordIncorrectException {
        //Given
        User user = new User("user1", "pass");
        userRepository.save(user);
        Mockito.when(userRepository.findById("user1")).thenReturn(java.util.Optional.of(user));

        //When
        final boolean correctLogin = userService.logIn("user1", "pass");

        //Then
        Mockito.verify(userRepository, Mockito.times(1)).findById("user1");
        assertThat(correctLogin).isEqualTo(true);
    }

//    @Test
//    public void tesLogInUnSuccesful() {
//        //Given
//        User user = new User("user1", "pass");
//        userRepository.save(user);
//        Mockito.when(userRepository.findById("user1")).thenReturn(java.util.Optional.of(user));
//
//        //Then
//        assertThrows(PasswordIncorrectException.class, () -> userService.logIn("user1", "incorrectpass"));
//    }
}