package nl.hu.eindopdracht1.domain.exception;

public class TaskAlreadyAssignedToUser extends Exception {
    public TaskAlreadyAssignedToUser(Long id, String userId) {
        super("Task with id " + id + " is already assigned to user " + userId);
    }
}
