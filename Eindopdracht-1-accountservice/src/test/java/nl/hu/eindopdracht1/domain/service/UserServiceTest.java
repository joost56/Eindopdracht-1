package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
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
    @Test
    public void testAccountCreationSuccessful() throws UserNotFoundException {
        User user = new User();
        Mockito.when(userRepository.save(userArgumentCaptor.capture())).thenReturn(user);
        final User userRes = userService.save(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        assertThat(userRes).isEqualTo(user);
        assertThat(userArgumentCaptor.getValue()).isEqualTo(user);
    }
}