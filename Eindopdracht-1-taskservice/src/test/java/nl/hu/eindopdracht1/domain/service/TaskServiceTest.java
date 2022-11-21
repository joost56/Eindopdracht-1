package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void editTask() throws TaskNotFoundException {
        //Given
        Column column = new Column("kolom1");
        Task task = new Task(1L, "dit is een test task", column);
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(taskRepository.save(taskArgumentCaptor.capture())).thenReturn(task);

        // When
        Task editTask = new Task(1L, "Dit is een aanpassing", column);
        final Task taskResult = taskService.editTask(task.getId(), editTask.getDescription());

        // Then
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
        assertEquals(taskResult.getDescription(), editTask.getDescription());
    }

    @Test
    void findTaskWithNoExistingTask() {
        // When
        Throwable thrown = catchThrowable(() -> {
            final Task taskResult = taskService.findTaskById(100L);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("Task with id 100 does not exist.");
    }
}