package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.exception.TaskNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import nl.hu.eindopdracht1.web.dto.SwitchTaskDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/columns")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ColumnController {
    private final ColumnService columnService;

    @PostMapping
    public Column createColumn(@RequestBody CreateColumnDto columnId){
        return columnService.save(new Column(columnId.getColumnId()));
    }

    @PutMapping("/switch")
    public Task switchTaskBetweenColumns(@RequestBody SwitchTaskDto switchTaskDto) throws ColumnNotFoundException, TaskNotFoundException {
        return columnService.switchTask(switchTaskDto.getOldColumnId(), switchTaskDto.getNewColumnId(), switchTaskDto.getTaskId());
    }
}
