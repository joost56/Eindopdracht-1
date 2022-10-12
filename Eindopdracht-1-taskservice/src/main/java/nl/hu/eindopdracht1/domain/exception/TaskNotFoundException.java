package nl.hu.eindopdracht1.domain.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String id) {
        super("Task with id " + id + " does not exist.");
    }
}
