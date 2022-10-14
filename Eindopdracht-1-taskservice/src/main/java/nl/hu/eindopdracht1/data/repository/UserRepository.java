package nl.hu.eindopdracht1.data.repository;

import nl.hu.eindopdracht1.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
