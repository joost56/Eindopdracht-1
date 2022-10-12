package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/columns")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ColumnController {
    private final ColumnService columnService;

    @PostMapping("/create")
    public Column createColumn(){
        return columnService.save(new Column("Dit is een column"));
    }

    @PutMapping("/switch/{oldColumnId}/{newColumnId}/{taskId}")
    public Column switchTaskBetweenColumns(@PathVariable String oldColumnId, String newColumnId, String taskId) throws ColumnNotFoundException, TaskNotFoundException {
        return columnService.switchTask(oldColumnId, newColumnId, taskId);
    }
}
