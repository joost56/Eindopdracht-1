package nl.hu.eindopdracht1.web.controller;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.domain.service.TaskService;
import nl.hu.eindopdracht1.domain.service.UserService;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import nl.hu.eindopdracht1.web.dto.CreateTaskDto;
import nl.hu.eindopdracht1.web.dto.SwitchTaskDto;
import org.iban4j.Iban;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ColumnControllerTest {
    @Mock
    ColumnService columnService;

    @Mock
    TaskService taskService;

    @InjectMocks // Class Under Test
    ColumnController columnController;

    @InjectMocks
    TaskController taskController;

    @Captor
    ArgumentCaptor<Iban> columnIdCaptor1;

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
        assertThat(columnsResult).isNotEmpty();
    }

    @Test
    public void testCreateColumnSuccessful() throws ColumnNotFoundException {
    }
}