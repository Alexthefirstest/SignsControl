package by.epam.connectionPoolForDataBase.connectionPool;

import java.sql.Connection;

/**
 * Interface for jdbc connection pool
 *
 * @author Alexander Bulgak
 */
public interface IConnectionPool {

    /**
     * @return {@link Connection} for using
     */
    Connection retrieveConnection();

    /**
     * @param connection return param to the connections storage
     * @return boolean result of operation
     */
    boolean releaseConnection(Connection connection);

    /**
     * shutdown all connections in storage of connection pool and do not allow create new
     */
    void destroyConnectionPool();
}
