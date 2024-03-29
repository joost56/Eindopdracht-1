package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/boards/tasks")
@RequiredArgsConstructor
@Validated
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final ColumnService columnService;
    private final UserService userService;

    @PostMapping
    public Task createTask(@RequestBody CreateTaskDto createTaskDto) throws ColumnNotFoundException {
        Column column = columnService.findColumnById(createTaskDto.getColumnId());
        return taskService.save(new Task(createTaskDto.getTaskDescription(), column));
    }

    @PutMapping()
    public Task editTask(@RequestBody EditTaskDto editTaskDto) throws TaskNotFoundException {
        return taskService.editTask(editTaskDto.getTaskId(), editTaskDto.getTaskDescription());
    }

    @PutMapping("/assign")
    public List<User> assignUser(@RequestBody AssignUserToTaskDto assignUserToTaskDto) throws UserNotFoundException, TaskNotFoundException, IOException, InterruptedException, TaskAlreadyAssignedToUser {
        return userService.assignTaskToUserAndUserToTask(assignUserToTaskDto.getUsername(), assignUserToTaskDto.getTaskId());
    }

    //Only for test purposes
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id) {
        return userService.findUserByIdAccountService(id);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskService.findTaskById(taskId);
    }
}
