package pl.garciapl.banknow.service.exceptions;

/**
 * Created by lukasz on 05.07.15.
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
