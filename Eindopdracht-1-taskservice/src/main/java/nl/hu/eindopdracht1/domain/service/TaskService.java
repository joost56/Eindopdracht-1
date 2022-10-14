package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public Task findTaskById(Long taskId) throws TaskNotFoundException {
        return this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Task editTask(Long taskId, String newDescription) throws TaskNotFoundException {
        Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.editTask(newDescription);
        return task;
    }
}
