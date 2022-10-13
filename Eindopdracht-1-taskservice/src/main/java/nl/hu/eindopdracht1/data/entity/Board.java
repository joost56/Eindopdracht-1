package nl.hu.eindopdracht1.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    private String id;

    @OneToMany
    private Set<Column> columns = new HashSet<>();

    public Board(String id) {
        this.id = id;
    }

    public boolean addColumn(Column column) {
        return columns.add(column);
    }

    public boolean removeColumn(Column column) {
        return columns.remove(column);
    }
}
