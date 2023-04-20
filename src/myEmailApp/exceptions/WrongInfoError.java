package myEmailApp.exceptions;

public class WrongInfoError extends Throwable {
    public WrongInfoError(String error) {
        super(error);
    }
}
