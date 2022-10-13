package nl.hu.eindopdracht1.domain;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException(String password) {
        super("Password " + password + " is not correct.");
    }
}
