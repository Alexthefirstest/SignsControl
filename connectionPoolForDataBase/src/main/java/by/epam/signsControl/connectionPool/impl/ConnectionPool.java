package by.epam.signsControl.connectionPool.impl;


import by.epam.signsControl.config.DBConfiguration;
import by.epam.signsControl.connectionPool.exceptions.ConnectionPoolException;
import by.epam.signsControl.connectionPool.IConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements IConnectionPool {

    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final ConnectionPool CONNECTION_POOL_INSTANCE = new ConnectionPool();

    private final DBConfiguration dbConfigs = DBConfiguration.getInstance();
    private final BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<>(dbConfigs.getMaxPoolSize());
    private final Queue<Connection> takenConnections = new PriorityQueue<>(dbConfigs.getMaxPoolSize());

    private boolean isDestroyed = false;

    private ConnectionPool() {

        initConnectionPool();
        logger.info("constructor done successfully");

    }

    private void shutDownConnection(Connection connection) {

        try {

            if (connection != null) {
                ((Connection$Proxy) connection).shutDown();
            }

        } catch (SQLException ex) {
            logger.warn("can't shut down connection: " + ex);
        }

    }

    private void initConnectionPool() {

        logger.info("connection init started");

        try {

            for (int i = 0; i < dbConfigs.getInitPoolSize(); i++) {
                availableConnections.offer(createConnection());
            }

        } catch (ConnectionPoolException ex) {

            logger.error("can't create connections for initialisation connection pool", ex);
            throw new Error("can't create connections for initialisation connection pool", ex);
        }

        logger.info("connection init finished");

        if (availableConnections.size() != dbConfigs.getInitPoolSize()) {
            logger.warn("quantity of connections != init pool size (connect quantity, init pool size, max pool size): " +
                    availableConnections.size() + "," + dbConfigs.getInitPoolSize() + "," + dbConfigs.getMaxPoolSize());
        }

    }

    private Connection createConnection() throws ConnectionPoolException {

        logger.info("started create new connection");

        if (isDestroyed) {
            throw new ConnectionPoolException("connection pool destroyed");
        }

        try {

            Connection connection = new Connection$Proxy
                    (DriverManager.getConnection(dbConfigs.getDbUrl(), dbConfigs.getDbUser(), dbConfigs.getDbPassword()));

            logger.info("connection created successfully");

            return connection;

        } catch (SQLException ex) {
            logger.warn("can't create connection");
            throw new ConnectionPoolException("can't create connection", ex);
        }

    }

    @Override
    public Connection retrieveConnection() {

        Connection connection;

        while ((connection = availableConnections.poll()) == null) {

            logger.debug("need more connections for retrieve");

            int increaseStep = dbConfigs.getPoolIncreaseStep();
            int i = 0;

            while ((availableConnections.remainingCapacity() - takenConnections.size()) > 0 && i < increaseStep) {

                logger.info("try to create more connections");

                try {
                    availableConnections.offer(createConnection());
                } catch (ConnectionPoolException ex) {
                    logger.warn("cant create connection for retrieve " + ex.getMessage());
                    break;
                }

                i++;
            }

            logger.debug("try to get connection again");

        }

        if (takenConnections.offer(connection) == false) {
            logger.error("can't add connection to takenConnections  " + toString());
            shutDownConnection(connection);
            return retrieveConnection();

        } else {
            logger.info("add connection to takenConnections successfully");
            return connection;
        }


    }

//    static boolean isTakenConnectionContainConnection(Connection connection){  //один положил, другой взял - true, должно быть false, ИСПРАВИТЬ
//        return ConnectionPool.getConnectionPoolInstance().takenConnections.contains(connection);
//    }


    @Override
    public boolean releaseConnection(Connection connection) {

        logger.info("try to release connection");


        boolean isRemoved = takenConnections.remove(connection);

        if (isRemoved) {

            if (availableConnections.offer(connection)) {
                logger.info("release connection successfully");
                return true;
            }

            logger.error("can't release connection, can't add it to available connections " + toString());
            shutDownConnection(connection);

            return true;

        } else {
            logger.warn("can't release connection, takenConnections do not contain it" + toString());
            shutDownConnection(connection);
            return true;
        }

    }


    @Override
    public void destroyConnectionPool() {
        finalize();
    }

    protected void finalize() {

        logger.info("destroying connection pool started");

        isDestroyed = true;

        while (availableConnections.size() > 0 || takenConnections.size() > 0) {
            shutDownConnection(availableConnections.poll());
            shutDownConnection(takenConnections.poll());
        }

        logger.info("destroying connection pool finished");
    }


    public static ConnectionPool getConnectionPoolInstance() {
        return CONNECTION_POOL_INSTANCE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Connection pool: ");
        sb.append(" {available connections quantity: ").append(availableConnections.size());
        sb.append(", taken connections quantity=").append(takenConnections.size());
        sb.append(", quantity of free cells to add new connections =")
                .append((availableConnections.remainingCapacity() - takenConnections.size()));
        sb.append('}');
        return sb.toString();
    }


}
