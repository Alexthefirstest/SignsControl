package by.epam.bank.dao.exceptions;


/**
 * Dao layer exception
 *
 * @author Bulgak Alexander
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
     * @param cause   {@link Throwable}
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * call super(cause)
     *
     * @param cause {@link Throwable}
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

}
