package nl.hu.eindopdracht1.data.entity;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="column_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Column {
    @Id
    @Name("column_id")
    private String id;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL)
    @javax.persistence.Column
    private Set<Task> tasks = new HashSet<>();

    public Column(String id) {
        this.id = id;
    }

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }
}
