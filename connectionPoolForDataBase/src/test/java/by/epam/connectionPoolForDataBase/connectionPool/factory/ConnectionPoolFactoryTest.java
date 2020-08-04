package by.epam.connectionPoolForDataBase.connectionPool.factory;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionPoolFactoryTest {

    private static ConnectionPoolFactory connectionPoolFactory;

    @BeforeClass
    public static void beforeClass() {
        connectionPoolFactory = ConnectionPoolFactory.getINSTANCE();
    }

    @Test
    public void getConnectionPoolInstance() {
        Assert.assertNotNull(connectionPoolFactory.getConnectionPoolInstance());
    }

}