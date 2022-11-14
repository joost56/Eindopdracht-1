package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.data.repository.UserRepository;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.model.HttpResponse;

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



//    @Test
//    public void testAccountCreationWithNoHolder() {
//        // Given
//        Account account = new Account(null, new BigDecimal(123), new HashSet<>(), true);
//
//        // When
//        Throwable thrown = catchThrowable(() -> {
//            final Account accountResult = accountService.create(account);
//        });
//
//        // Then
//        assertThat(thrown)
//                .isInstanceOf(NoAccountHolderException.class)
//                .hasMessageContaining("has no holder");
//        Mockito.verify(accountRepository, Mockito.times(0)).save(account);
//    }
}