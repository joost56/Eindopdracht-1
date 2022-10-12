package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
