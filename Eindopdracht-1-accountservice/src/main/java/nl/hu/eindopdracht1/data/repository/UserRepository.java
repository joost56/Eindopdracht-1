package nl.hu.eindopdracht1.data.repository;

import nl.hu.eindopdracht1.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Override
    List<User> findAll();

    Optional<User> findById(String id);
}
