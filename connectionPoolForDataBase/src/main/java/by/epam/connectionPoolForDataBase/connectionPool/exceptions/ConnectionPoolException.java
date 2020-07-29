package by.epam.connectionPoolForDataBase.connectionPool.exceptions;

/**
 * Exception for {@link by.epam.connectionPoolForDataBase.connectionPool.impl.ConnectionPool}
 *
 * @author Bulgak Alexander
 */
public class ConnectionPoolException extends Exception {

    /**
     * call {@link Exception} constructor
     *
     * @param message {@link String} message
     * @param ex      {@link Exception} exception
     */
    public ConnectionPoolException(String message, Exception ex) {
        super(message, ex);
    }

    /**
     * call {@link Exception} constructor
     *
     * @param message {@link String} message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

}
