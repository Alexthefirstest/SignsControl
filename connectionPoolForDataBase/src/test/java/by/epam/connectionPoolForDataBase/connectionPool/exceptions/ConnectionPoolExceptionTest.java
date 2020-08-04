package by.epam.connectionPoolForDataBase.connectionPool.exceptions;

import org.junit.Test;

public class ConnectionPoolExceptionTest {

    @Test(expected = ConnectionPoolException.class)
    public void exceptionTestWithMessageConstructor() throws ConnectionPoolException {
        throw new ConnectionPoolException("hi");
    }

    @Test(expected = ConnectionPoolException.class)
    public void exceptionTestWithMessageExceptionConstructor() throws ConnectionPoolException {
        throw new ConnectionPoolException("hi", new NullPointerException());
    }


}