package nl.hu.eindopdracht1.web.controller;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.domain.service.TaskService;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import nl.hu.eindopdracht1.web.dto.CreateTaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class TaskControllerTest {
    @Mock
    ColumnService columnService;

    @Mock
    TaskService taskService;

    @InjectMocks // Class Under Test
    ColumnController columnController;

    @InjectMocks
    TaskController taskController;

    @Test
    public void testCreateTaskSuccessful() throws TaskNotFoundException, ColumnNotFoundException {
    }


}