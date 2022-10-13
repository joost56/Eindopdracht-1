package nl.hu.eindopdracht1.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hu.eindopdracht1.data.entity.Board;
import nl.hu.eindopdracht1.domain.service.BoardService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public Board createBoard(){
        return boardService.save(new Board("Dit is een board"));
    }


}
