package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Task;
import nl.hu.eindopdracht1.domain.service.TaskService;
import nl.hu.eindopdracht1.web.dto.TaskCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
@Slf4j
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public String createTask(){
        return "ja";
    }
}
