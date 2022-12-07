package nl.hu.eindopdracht1.data.repository;

import nl.hu.eindopdracht1.data.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColumnRepository extends JpaRepository<Column, String> {
    Optional<Column> findById(String id);
}
