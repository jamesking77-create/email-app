package myEmailApp.exceptions;

public class UnregisteredUserException extends Exception {
    public UnregisteredUserException(String error) {
        super(error);
    }
}
