package nl.hu.eindopdracht1.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "column")
    @javax.persistence.Column
    private List<Task> tasks = new ArrayList<>();

    public Column(String id) {
        this.id = id;
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }
}
