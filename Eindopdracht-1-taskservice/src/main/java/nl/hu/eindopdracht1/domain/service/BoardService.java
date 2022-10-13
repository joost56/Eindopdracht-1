package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.Board;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.repository.BoardRepository;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.BoardNotFoundException;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Board findBoardById(String boardId) throws BoardNotFoundException {
        return this.boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(boardId));
    }
}
