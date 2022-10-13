package nl.hu.eindopdracht1.data.repository;

import nl.hu.eindopdracht1.data.entity.Board;
import nl.hu.eindopdracht1.data.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    Optional<Board> findById(String id);
}
