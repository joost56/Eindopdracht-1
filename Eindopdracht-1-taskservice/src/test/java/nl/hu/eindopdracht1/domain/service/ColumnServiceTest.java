package nl.hu.eindopdracht1.domain.service;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ColumnServiceTest {

    @Mock
    ColumnRepository columnRepository;//MOCK
    @Mock
    TaskRepository taskRepository;//MOCK

    @Mock
    TaskService taskService;//MOCK

    @InjectMocks // Class Under Test
    ColumnService columnService;

    @Captor
    ArgumentCaptor<Column> columnArgumentCaptor;

    @Captor
    ArgumentCaptor<Task> taskArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;

    @Test
    public void saveColumnSuccessful(){
        // Given
        Column column = new Column("kolom1");
        Mockito.when(columnRepository.save(columnArgumentCaptor.capture())).thenReturn(column);

        // When
        final Column columnResult = columnService.save(column);

        // Then
        Mockito.verify(columnRepository, Mockito.times(1)).save(column);
        assertThat(columnResult).isEqualTo(column);
        assertThat(columnArgumentCaptor.getValue()).isEqualTo(column);
        assertThat(columnArgumentCaptor.getValue().getId()).isNotNull();
    }

    @Test
    public void getColumnById() throws ColumnNotFoundException {
        // Given
        Column column = new Column("kolom1");
        Mockito.when(columnRepository.save(column)).thenReturn(column);
        Mockito.when(columnRepository.findById("kolom1")).thenReturn(java.util.Optional.of(column));

        // When
        columnService.save(column);
        final Column columnResult = columnService.findColumnById("kolom1");

        // Then
        Mockito.verify(columnRepository, Mockito.times(1)).findById("kolom1");
        assertThat(columnResult).isEqualTo(column);
    }

    @Test
    void findColumnWithNoExistingColumn() {
        // When
        Throwable thrown = catchThrowable(() -> {
            final Column columnResult = columnService.findColumnById("kolom100");
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ColumnNotFoundException.class)
                .hasMessageContaining("Column with id kolom100 does not exist.");
    }

    @Test
    public void switchTaskBetweenColumnsSuccesful() throws ColumnNotFoundException, TaskNotFoundException {
        // Given
        Column column = new Column("kolom1");
        Column column2 = new Column("kolom2");
        Task task = new Task(1L, "dit is een task", column);
        Mockito.when(taskService.findTaskById(1L)).thenReturn(task);
        Mockito.when(columnRepository.findById("kolom2")).thenReturn(java.util.Optional.of(column2));
        Mockito.when(columnRepository.findById("kolom1")).thenReturn(java.util.Optional.of(column));
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        // When
        Task res = columnService.switchTask("kolom1", "kolom2", 1L);

        // Then
        assertThat(res.getColumn()).isEqualTo(column2);
    }
}