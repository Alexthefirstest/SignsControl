package by.epam.signsControl.dao.exceptions;

public class DAOValidationException extends DAOException {

    public DAOValidationException() {
    }

    public DAOValidationException(String message) {
        super(message);
    }

    public DAOValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
