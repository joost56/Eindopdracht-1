package nl.hu.eindopdracht1.web.controller;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.domain.service.TaskService;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import nl.hu.eindopdracht1.web.dto.SwitchTaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ColumnControllerTest {
    @Mock
    ColumnService columnService;

    @InjectMocks // Class Under Test
    ColumnController columnController;

    @Captor
    ArgumentCaptor<Column> columnArgumentCaptor;

    @Test
    public void testGetAllColumnSuccessful() {
        //Given
        Column column1 = new Column("kolom1");
        Column column2 = new Column("kolom2");
        List<Column> columns = new ArrayList<>();
        columns.add(column1);
        columns.add(column2);
        Mockito.when(columnService.getAllColums()).thenReturn(columns);

        //When
        final List<Column> columnsResult = columnController.getAllColumns();

        //Then
        assertThat(columnsResult.size()).isEqualTo(2);
    }

    @Test
    public void testCreateColumnSuccessful() {
        //Given
        Column exampleColumn = new Column("kolom1");
        CreateColumnDto columnDto = new CreateColumnDto("kolom1");
        Mockito.when(columnService.save(columnArgumentCaptor.capture())).thenReturn(exampleColumn);

        //When
        final Column columnsResult = columnController.createColumn(columnDto);

        //Then
        assertThat(columnsResult).isEqualTo(exampleColumn);
    }

    @Test
    void getColumn() throws ColumnNotFoundException {
        //Given
        Column exampleColumn = new Column("kolom1");
        Mockito.when(columnService.findColumnById(exampleColumn.getId())).thenReturn(exampleColumn);

        //When
        final Column columnResult = columnController.getColumn(exampleColumn.getId());

        //Then
        assertThat(columnResult).isEqualTo(exampleColumn);
    }

    @Test
    void switchTaskBetweenColumns() throws TaskNotFoundException, ColumnNotFoundException {
        // Given
        Column column2 = new Column("kolom2");
        Task resTask = new Task(1L, "dit is een task", column2);
        SwitchTaskDto switchTaskDto = new SwitchTaskDto("kolom1", "kolom2", 1L);
        Mockito.when(columnService.switchTask("kolom1", "kolom2", 1L)).thenReturn(resTask);

        // When
        Task res = columnController.switchTaskBetweenColumns(switchTaskDto);

        // Then
        assertThat(res.getColumn()).isEqualTo(column2);
    }
}