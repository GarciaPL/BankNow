package pl.garciapl.banknow.service.exceptions;

/**
 * GenericBankNowException
 */
public class GenericBankNowException extends Exception {

    public GenericBankNowException() {
    }

    public GenericBankNowException(String message) {
        super(message);
    }

    public GenericBankNowException(String message, Throwable cause) {
        super(message, cause);
    }
}
