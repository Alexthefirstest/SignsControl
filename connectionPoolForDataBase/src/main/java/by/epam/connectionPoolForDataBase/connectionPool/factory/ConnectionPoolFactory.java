package by.epam.connectionPoolForDataBase.connectionPool.factory;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.impl.ConnectionPool;

public class ConnectionPoolFactory {

    private ConnectionPoolFactory() {
    }

    static final ConnectionPoolFactory INSTANCE = new ConnectionPoolFactory();

    private final IConnectionPool connectionPoolInstance= ConnectionPool.getConnectionPoolInstance();

    public static ConnectionPoolFactory getINSTANCE() {
        return INSTANCE;
    }

    public IConnectionPool getConnectionPoolInstance() {
        return connectionPoolInstance;
    }
}
