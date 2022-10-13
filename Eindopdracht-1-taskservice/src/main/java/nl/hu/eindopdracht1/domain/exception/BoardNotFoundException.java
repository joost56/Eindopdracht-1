package nl.hu.eindopdracht1.domain.exception;

public class BoardNotFoundException extends Exception {
    public BoardNotFoundException(String id) {
        super("Board with id " + id + " does not exist.");
    }
}
