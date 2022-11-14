package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)

class TaskServiceTest {
    @Mock
    TaskRepository taskRepository;
    @InjectMocks // Class Under Test
    TaskService taskService;

    @Captor
    ArgumentCaptor<Task> taskArgumentCaptor;

    @Test
    public void taskCreationSuccessful(){
        // Given
        Column column = new Column("dit is een test kolom");
        Task task = new Task("dit is een test task", column);
        Mockito.when(taskRepository.save(taskArgumentCaptor.capture())).thenReturn(task);

        // When
        final Task taskResult = taskService.save(task);

        // Then
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
        assertThat(taskResult).isEqualTo(task);
        assertThat(taskArgumentCaptor.getValue()).isEqualTo(task);
        assertThat(taskArgumentCaptor.getValue().getColumn()).isNotNull();
    }
}