package nl.hu.eindopdracht1.domain.service;

import lombok.RequiredArgsConstructor;
import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.data.repository.ColumnRepository;
import nl.hu.eindopdracht1.data.repository.TaskRepository;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final TaskRepository taskRepository;

    public Column save(Column column){
        return columnRepository.save(column);
    }

    public Column findColumnById(String columnId) throws ColumnNotFoundException {
        return this.columnRepository.findById(columnId).orElseThrow(() -> new ColumnNotFoundException(columnId));
    }
}
