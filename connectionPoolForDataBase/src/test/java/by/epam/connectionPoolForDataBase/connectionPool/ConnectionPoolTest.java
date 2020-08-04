package by.epam.connectionPoolForDataBase.connectionPool;

import by.epam.connectionPoolForDataBase.config.DBConfiguration;
import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.impl.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConnectionPoolTest {

    private static ConnectionPool connectionPool;

    private static DBConfiguration dbConfigs;

    private static Connection connection;

    private static Connection[] connections;

    private static final Logger logger = LogManager.getLogger(ConnectionPoolTest.class);

    @BeforeClass
    public static void beforeClass() {

        System.out.println("connection pool test before class start");
        logger.info("connection pool test before class start");

        dbConfigs = DBConfiguration.getInstance();
        connectionPool = ConnectionPool.getConnectionPoolInstance();

        System.out.println("before class finish");

    }

    @AfterClass
    public static void AfterClassDestroyConnectionPoolTest() {

        System.out.println("after class start");

        Assert.assertEquals(connectionPool.getTakenConnectionsQuantity(), 0);

        dbConfigs = null;
        connection = null;
        connections = null;
        connectionPool = null;

        System.out.println("connection pool test after class finish");
        logger.info("connection pool test after class finish");

    }

    @After
    public void afterTest() {

        System.out.println("after test start");

        connectionPool.releaseConnection(connection);
        connection = null;

        if (connections != null) {

            for (Connection connection : connections) {
                connectionPool.releaseConnection(connection);
            }

        }

        connections = null;

        System.out.println("after test finish");
    }

    @Test
    public void retrieveConnectionTest() throws ConnectionPoolException {

        System.out.println("retrieve connect start");

        connection = connectionPool.retrieveConnection();
        assertNotNull(connection);

        System.out.println("retrieve connect finish");
    }

    @Test
    public void releaseConnectionTest() throws ConnectionPoolException {

        System.out.println(" release connect start");

        connection = connectionPool.retrieveConnection();


        assertTrue(connectionPool.releaseConnection(connection));

        System.out.println(" release finish start");
    }

    @Test
    public void releaseConnectionNullTest() {


        connectionPool.releaseConnection(null);

    }

    @Ignore
    @Test(timeout = 60_000)
    public void connectionPoolSizeTest() throws ConnectionPoolException {

        System.out.println("pull size check start");

        int size = dbConfigs.getMaxPoolSize();

        connections = new Connection[size];

        for (int i = 0; i < size; i++) {
            connections[i] = connectionPool.retrieveConnection();
        }

        System.out.println("pull size check finish");

    }

}