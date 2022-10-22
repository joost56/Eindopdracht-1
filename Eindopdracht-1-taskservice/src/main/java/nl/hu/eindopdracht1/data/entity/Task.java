package nl.hu.eindopdracht1.data.entity;

import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
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
    @JoinColumn(name="column_id", nullable=false)
    private Column column;

    public Task(String description, Column column) {
        this.description = description;
        this.column = column;
    }

    public void editTask(String newDescription) {
        description = newDescription;
    }
}
