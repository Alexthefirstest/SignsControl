package by.epam.connectionPoolForDataBase.connectionPool;

import by.epam.connectionPoolForDataBase.config.DBConfiguration;
import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.impl.ConnectionPool;
import org.junit.*;

import static org.junit.Assert.*;


import java.sql.Connection;

public class ConnectionPoolTest {

    private static ConnectionPool connectionPool;

    private static DBConfiguration dbConfigs;

    private static Connection connection;

    private static Connection[] connections;


    @BeforeClass
    public static void beforeClass() {

        System.out.println("before class start");

        dbConfigs = DBConfiguration.getInstance();
        connectionPool = ConnectionPool.getConnectionPoolInstance();

        System.out.println("before class finish");
        System.out.println("isDestroy: "+connectionPool.isDestroy());
    }

    @AfterClass
    public static void AfterClassDestroyConnectionPoolTest() {

        System.out.println("after class start");

        connectionPool.destroyConnectionPool();
        connectionPool = null;
        dbConfigs = null;
        connection = null;
        connections = null;

        System.out.println("after class finish");
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