package nl.hu.eindopdracht1.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String description;

    @ManyToOne
    private Column column;

    public Task(String description, Column column) {
        this.description = description;
        this.column = column;
    }
}
