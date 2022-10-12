package nl.hu.eindopdracht1.domain.exception;

public class ColumnNotFoundException extends Exception {
    public ColumnNotFoundException(String id) {
        super("Column with id " + id + " does not exist.");
    }
}
