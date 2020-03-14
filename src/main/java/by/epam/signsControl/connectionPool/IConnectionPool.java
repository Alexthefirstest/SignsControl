package by.epam.signsControl.connectionPool;

import java.sql.Connection;

public interface IConnectionPool {

    Connection retrieveConnection();

    boolean releaseConnection(Connection connection);

    void destroyConnectionPool();
}
