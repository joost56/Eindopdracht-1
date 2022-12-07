package nl.hu.eindopdracht1.web.controller;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.entity.User;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskAlreadyAssignedToUser;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.domain.service.TaskService;
import nl.hu.eindopdracht1.domain.service.UserService;
import nl.hu.eindopdracht1.web.dto.AssignUserToTaskDto;
import nl.hu.eindopdracht1.web.dto.CreateTaskDto;
import nl.hu.eindopdracht1.web.dto.EditTaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

class TaskControllerTest {
    @Mock
    ColumnService columnService;

    @Mock
    TaskService taskService;

    @Mock
    UserService userService;

    @InjectMocks // Class Under Test
    ColumnController columnController;

    @InjectMocks
    TaskController taskController;

    @Captor
    ArgumentCaptor<Task> taskArgumentCaptor;

    @Test
    public void testCreateTaskSuccessful() throws ColumnNotFoundException {
        //Given
        Column exampleColumn = new Column("kolom1");
        Task exampleTask = new Task("dit is een task", exampleColumn);
        CreateTaskDto taskDto = new CreateTaskDto();
        Mockito.when(taskService.save(taskArgumentCaptor.capture())).thenReturn(exampleTask);

        //When
        final Task tasksResult = taskController.createTask(taskDto);

        //Then
        assertThat(tasksResult).isEqualTo(exampleTask);
    }


    @Test
    void getAllTasksSuccesful() {
        //Given
        Column column1 = new Column("kolom1");
        Column column2 = new Column("kolom2");
        Task task1 = new Task("dit is een task", column1);
        Task task2 = new Task("dit is een task2", column2);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        //When
        final List<Task> tasksResult = taskController.getAllTasks();

        //Then
        assertThat(tasksResult.size()).isEqualTo(2);
    }

    @Test
    void editTask() throws TaskNotFoundException {
        //Given
        Column column = new Column("kolom1");
        Task restask = new Task(1L, "nieuwe description", column);
        EditTaskDto editTaskDto = new EditTaskDto(1L, "nieuwe description");
        Mockito.when(taskService.editTask(1L, "nieuwe description")).thenReturn(restask);

        // When
        final Task taskResult = taskController.editTask(editTaskDto);

        // Then
        assertEquals(taskResult.getDescription(), restask.getDescription());
    }

    @Test
    void assignUser() throws UserNotFoundException, TaskNotFoundException, TaskAlreadyAssignedToUser, IOException, InterruptedException {
        //Given
        Column column = new Column("kolom1");
        User user = new User("joost");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Task restask = new Task(1L, "nieuwe description", column, userList);
        Mockito.when(userService.assignTaskToUserAndUserToTask("joost", 1L)).thenReturn(restask.getUsers());
        AssignUserToTaskDto assignUserToTaskDto = new AssignUserToTaskDto("joost", 1L);

        //When
        List<User> taskUsers = taskController.assignUser(assignUserToTaskDto);

        //Then
        assertEquals(taskUsers, userList);

    }
}