package myEmailApp.dtos.util;

public class WrongInfoError extends Throwable {
    public WrongInfoError(String error) {
        super(error);
    }
}
