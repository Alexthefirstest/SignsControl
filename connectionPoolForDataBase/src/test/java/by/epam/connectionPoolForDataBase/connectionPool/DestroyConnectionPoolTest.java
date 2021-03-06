package by.epam.connectionPoolForDataBase.connectionPool;

import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.impl.ConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;

public class DestroyConnectionPoolTest {

    private static ConnectionPool connectionPool;


    @BeforeClass
    public static void beforeClass() {

        connectionPool = ConnectionPool.getConnectionPoolInstance();
        System.out.println("Destroy connection pool start is destroy: "+connectionPool.isDestroy());
        connectionPool.destroyConnectionPool();
        System.out.println("is destroy: "+connectionPool.isDestroy());
    }


    @AfterClass
    public static void afterClass() {
        System.out.println("is destroy: "+connectionPool.isDestroy());
        connectionPool = null;
    }



    @Test(expected = ConnectionPoolException.class)
    public void destroyTest() throws ConnectionPoolException {

        System.out.println("destroy test start");

        Connection connection=connectionPool.retrieveConnection();

        System.out.println(connection);

        System.out.println("destroy test finish");
    }


    @Test
    public void twoDestroyTest() {
        System.out.println("two destroy start");

        connectionPool.destroyConnectionPool();

        System.out.println("two destroy finish");
    }

}
