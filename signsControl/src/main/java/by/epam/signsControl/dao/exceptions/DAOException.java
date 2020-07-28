package by.epam.signsControl.dao.exceptions;

/**
 * exception for dao layer
 *
 * @see Exception
 */
public class DAOException extends Exception {

    /**
     * empty constructor
     */
    public DAOException() {
    }

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * call super(message, cause)
     *
     * @param message exception message
     * @param cause   message cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * call super( cause)
     *
     * @param cause exception cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

}
