package pl.garciapl.banknow.service.exceptions;

/**
 * Created by lukasz on 05.07.15.
 */
public class AccountExistsException extends Exception {

    public AccountExistsException() {
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
