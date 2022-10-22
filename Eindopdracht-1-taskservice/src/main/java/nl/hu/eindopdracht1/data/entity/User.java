package nl.hu.eindopdracht1.data.entity;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @Column
    private List<Task> tasks = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }
}
