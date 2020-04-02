package by.epam.connectionPoolForDataBase.connectionPool;

import java.sql.Connection;

public interface IConnectionPool {

    Connection retrieveConnection();

    boolean releaseConnection(Connection connection);

    void destroyConnectionPool();
}
