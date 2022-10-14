package nl.hu.eindopdracht1.domain.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String id) {
        super("User with id " + id + " does not exist.");
    }
}
