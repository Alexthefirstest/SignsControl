package by.epam.connectionPoolForDataBase.connectionPool.factory;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.impl.ConnectionPool;

/**
 * Factory for {@link IConnectionPool}
 *
 * @author Bulgak Alexander
 */
public class ConnectionPoolFactory {

    /**
     * this class instance
     */
    private static final ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();

    /**
     * {@link IConnectionPool} realisation
     */
    private final IConnectionPool connectionPoolInstance = ConnectionPool.getConnectionPoolInstance();

    private ConnectionPoolFactory() {
    }

    /**
     * @return {@link ConnectionPoolFactory#INSTANCE}
     */
    public static ConnectionPoolFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link ConnectionPoolFactory#connectionPoolInstance}
     */
    public IConnectionPool getConnectionPoolInstance() {
        return connectionPoolInstance;
    }
}
