package nl.hu.eindopdracht1.data.entity;

import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//idk
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

    @ManyToOne
    @JoinColumn(name="column_id", nullable = false)
    private Column column;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    public Task(String description, Column column) {
        this.description = description;
        this.column = column;
    }

    public void editTask(String newDescription) {
        description = newDescription;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", column=" + column +
                ", users=" + users +
                '}';
    }
}
