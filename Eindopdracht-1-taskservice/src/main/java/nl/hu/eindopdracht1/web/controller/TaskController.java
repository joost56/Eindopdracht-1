package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.domain.service.TaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
@Slf4j
public class TaskController {
    private final TaskService taskService;
    private final ColumnService columnService;

    @PostMapping("/create/{columnId}")
    public Task createTask(@PathVariable String columnId, @RequestBody String taskDescription) throws ColumnNotFoundException {
        Column column = columnService.findColumnById(columnId);
        return taskService.save(new Task(taskDescription, column));
    }

    @PatchMapping("/edit/{taskId}")
    public Task editTask(@PathVariable String taskId, @RequestBody String newTaskDescription) throws TaskNotFoundException {
        Task task = taskService.editTask(taskId, newTaskDescription);
        return taskService.save(task);
    }
}
