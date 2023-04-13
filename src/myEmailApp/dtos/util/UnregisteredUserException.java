package myEmailApp.dtos.util;

public class UnregisteredUserException extends Exception {
    public UnregisteredUserException(String error) {
        super(error);
    }
}
