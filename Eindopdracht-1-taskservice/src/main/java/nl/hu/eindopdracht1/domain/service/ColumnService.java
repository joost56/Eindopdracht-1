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
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public Column save(Column column) {
        return columnRepository.save(column);
    }

    public Column findColumnById(String columnId) throws ColumnNotFoundException {
        return this.columnRepository.findById(columnId).orElseThrow(() -> new ColumnNotFoundException(columnId));
    }

//    public Column addTask(String columnId, Task task) throws ColumnNotFoundException {
//        Column column = findColumnById(columnId);
//        column.addTask(task);
//        taskRepository.save(task);
//        return columnRepository.save(column);
//    }

    public Column removeTask(String columnId, Long taskId) throws ColumnNotFoundException, TaskNotFoundException {
        Column column = findColumnById(columnId);
        Task task = taskService.findTaskById(taskId);
        column.removeTask(task);
        taskRepository.delete(task);
        return columnRepository.save(column);
    }

    public Column switchTask(String oldColumnId, String newColumnId, Long taskId) throws ColumnNotFoundException, TaskNotFoundException {
        Task task = taskService.findTaskById(taskId);
        removeTask(oldColumnId, taskId);
//        addTask(newColumnId, task);
        Column columnOld = findColumnById(oldColumnId);
        Column columnNew = findColumnById(newColumnId);
        columnRepository.save(columnOld);
        taskRepository.save(task);
        return columnRepository.save(columnNew);
    }
}