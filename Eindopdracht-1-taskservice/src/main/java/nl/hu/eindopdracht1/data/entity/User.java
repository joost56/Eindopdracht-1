package nl.hu.eindopdracht1.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="user_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Name("user_id")
    private String username;

    @JsonIgnoreProperties(value = "users")
    @ManyToMany(mappedBy = "users")
    @Column
    private List<Task> tasks = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        List<Long> taskIds = tasks.stream().map(Task::getId).toList();
        return "true";
    }
}
