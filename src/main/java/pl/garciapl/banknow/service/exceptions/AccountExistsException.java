package pl.garciapl.banknow.service.exceptions;

/**
 * AccountExistsException
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
