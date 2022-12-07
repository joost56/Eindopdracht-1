package nl.hu.eindopdracht1.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Name;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Name("task_id")
    private Long id;
    private String description;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="column_id", nullable = false)
    private Column column;

    @ManyToMany
    @JoinColumn(name = "user_id", nullable = true)
    private List<User> users = new ArrayList<>();

    public Task(String description, Column column) {
        this.description = description;
        this.column = column;
    }

    public Task(Long id, String description, Column column) {
        this.id = id;
        this.description = description;
        this.column = column;
    }

    public Task(String description, List<User> users) {
        this.description = description;
        this.users = users;
    }

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Task(String description) {
        this.description = description;
    }

    public void editTask(String newDescription) {
        description = newDescription;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description +
                ", users=" + users +
                '}';
    }
}
