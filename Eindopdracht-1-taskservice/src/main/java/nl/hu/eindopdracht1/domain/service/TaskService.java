package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final ColumnRepository columnRepository;
    private final TaskService taskService;
    private final ColumnService columnService;

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public Task findTaskById(String taskId) throws TaskNotFoundException {
        return this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Task editTask(String taskId, String newDescription) throws TaskNotFoundException {
        Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.editTask(newDescription);
        return task;
    }

    public Column addTask(String columnId, String taskId) throws TaskNotFoundException, ColumnNotFoundException {
        Column column = columnService.findColumnById(columnId);
        Task task = taskService.findTaskById(taskId);
        column.addTask(task);
        taskRepository.save(task);
        return columnRepository.save(column);
    }

    public Column removeTask(String columnId, String taskId) throws ColumnNotFoundException, TaskNotFoundException {
        Column column = columnService.findColumnById(columnId);
        Task task = taskService.findTaskById(taskId);
        column.removeTask(task);
        taskRepository.delete(task);
        return columnRepository.save(column);
    }
}
