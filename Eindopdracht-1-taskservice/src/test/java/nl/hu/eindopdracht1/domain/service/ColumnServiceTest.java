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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ColumnServiceTest {

    @Mock
    ColumnRepository columnRepository;//MOCK
    @Mock
    TaskRepository taskRepository;//MOCK

    @InjectMocks
    TaskService taskService;//MOCK

    @Captor
    ArgumentCaptor<Column> columnArgumentCaptor;

    @Captor
    ArgumentCaptor<Task> taskArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;


    @Test
    public void getAllColumns() {


    }

//    @Test
//    public void switchTaskBetweenColumnsSuccesful() throws ColumnNotFoundException, TaskNotFoundException {
//        //Given
//        Column column = new Column("dit is een test kolom");
//        Column column2 = new Column("dit is een tweede test kolom");
//        Task task = new Task(1L, "dit is een test task", column);
//        Mockito.when(columnRepository.save(any(Column.class))).thenReturn(column);
//        Mockito.when(columnRepository.save(any(Column.class))).thenReturn(column2);
//        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(task);
//        Mockito.when(taskService.findTaskById(any(Long.class))).thenReturn(1L);
//        ColumnService columnService = new ColumnService(columnRepository, taskRepository, taskService);
//
//        // When
//        final Task columnResult = columnService.switchTask("dit is een test kolom", "dit is een tweede test kolom", 1L);
//
//        // Then
//        Mockito.verify(columnRepository, Mockito.times(1)).save(column);
//        Mockito.verify(columnRepository, Mockito.times(1)).save(column2);
//        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
//        assertThat(columnResult).isEqualTo(task);
//        assertThat(columnArgumentCaptor.getValue()).isEqualTo(task);
//        assertThat(columnArgumentCaptor.getValue().getTasks()).isNotNull();
//    }

//    @Test
//    public void editTaskDescriptionSuccesful() throws ColumnNotFoundException, TaskNotFoundException {
//        //Given
//        Column column = new Column("dit is een test kolom");
//        Task task = new Task(1L, "dit is een test task", column);
//        Mockito.when(columnRepository.save(columnArgumentCaptor.capture())).thenReturn(column);
//        Mockito.when(taskRepository.save(taskArgumentCaptor.capture())).thenReturn(task);
//        Mockito.when(taskService.findTaskById(any(Long.class))).thenReturn(1L);
//        ColumnService columnService = new ColumnService(columnRepository, taskRepository, taskService);
//
//        // When
//        final Task columnResult = columnService.switchTask("dit is een test kolom", "dit is een tweede test kolom", 1L);
//
//        // Then
//        Mockito.verify(columnRepository, Mockito.times(1)).save(column);
//        Mockito.verify(columnRepository, Mockito.times(1)).save(column2);
//        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
//        assertThat(columnResult).isEqualTo(task);
//        assertThat(columnArgumentCaptor.getValue()).isEqualTo(task);
//        assertThat(columnArgumentCaptor.getValue().getTasks()).isNotNull();
//    }
}