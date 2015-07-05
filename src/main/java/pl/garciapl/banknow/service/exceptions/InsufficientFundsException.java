package pl.garciapl.banknow.service.exceptions;

/**
 * Created by lukasz on 05.07.15.
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
