package by.epam.connectionPoolForDataBase.connectionPool.exceptions;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Exception ex) {
        super(message, ex);
    }

}
