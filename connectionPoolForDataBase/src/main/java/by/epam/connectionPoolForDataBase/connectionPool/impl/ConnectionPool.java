package by.epam.connectionPoolForDataBase.connectionPool.impl;


import by.epam.connectionPoolForDataBase.config.DBConfiguration;
import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool class
 * <p>
 * Connections storage with floating size
 * Thread safe
 * get and take connections
 * Use {@link DBConfiguration} initial parameters
 *
 * @author Bulgak Alexander
 */
public class ConnectionPool implements IConnectionPool {

    /**
     * destroyConnectionPoolCondition
     */
    private static boolean destroy = false;

    /**
     * field logger log4j2
     */
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    /**
     * this class instance
     */
    private static final ConnectionPool CONNECTION_POOL_INSTANCE = new ConnectionPool();

    /**
     * {@link DBConfiguration} instance
     */
    private final DBConfiguration dbConfigs = DBConfiguration.getInstance();

    /**
     * connections available for usage
     *
     * @see BlockingQueue
     */
    private final BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<>(dbConfigs.getMaxPoolSize());

    /**
     * taken connections, unavailable for usage at the moment
     *
     * @see Queue
     */
    private final Queue<Connection> takenConnections = new PriorityQueue<>(dbConfigs.getMaxPoolSize());

    /**
     * lock for thread safe {@link ConnectionPool#retrieveConnection()}
     * completely locking in {@link ConnectionPool#destroyConnectionPool()}
     *
     * @see ReentrantLock
     */
    private Lock retrieveLocker = new ReentrantLock();

    /**
     * lock for thread safe {@link ConnectionPool#releaseConnection(Connection)} ()}
     * completely locking in {@link ConnectionPool#destroyConnectionPool()}
     *
     * @see ReentrantLock
     */
    private Lock releaseLocker = new ReentrantLock();

    /*
     * private constructor
     * start {@link ConnectionPool@initConnectionPool()}
     */

    /**
     * get retrieve timeout from config
     */
    private final int retrieveTimeout = dbConfigs.getRetrieveTimeout();

    private ConnectionPool() {

        initConnectionPool();
        logger.info("constructor done successfully");

    }

    /*
     * shutdown connection
     *
     * @see Connection$Proxy#shutDown()
     */
    private void shutDownConnection(Connection connection) {

        try {

            if (connection != null) {
                ((Connection$Proxy) connection).shutDown();
                logger.warn("shut down connection successfully ");
            }

        } catch (SQLException ex) {
            logger.warn("can't shut down connection: " + ex);
        }

    }

    /*
     * init connection pool use {@link DBConfiguration} parameters
     *
     * throw Error when can't create connection
     */
    private void initConnectionPool() {

        logger.info("connection init started");

        try {

            Class.forName("com.mysql.jdbc.Driver");

            for (int i = 0; i < dbConfigs.getInitPoolSize(); i++) {
                availableConnections.offer(createConnection());
            }

        } catch (ClassNotFoundException e) {

            logger.error("jdbc driver problem", e);

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

    /**
     * create connection using jdbc
     *
     * @throws ConnectionPoolException when {@link SQLException} occurred
     */
    private Connection createConnection() throws ConnectionPoolException {

        logger.info("started create new connection");

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

    /**
     * take connection from {@link ConnectionPool#availableConnections} and get it for user,
     * put this connection into {@link ConnectionPool#takenConnections}
     * thread safe, use {@link ConnectionPool#retrieveLocker}
     * in case of haven't {@link ConnectionPool#availableConnections}:
     * expand common connections quantity to {@link DBConfiguration#getMaxPoolSize()}
     * (use {@link ConnectionPool#releaseLocker} during this)
     * or wait for connection
     *
     * @return {@link Connection}
     */
    @Override
    public Connection retrieveConnection() throws ConnectionPoolException {

        try {

            if (isDestroy()) {
                throw new ConnectionPoolException("connection pool was destroyed");
            }

            if (!retrieveLocker.tryLock(retrieveTimeout, TimeUnit.SECONDS)) {
                logger.warn("Connection pool timeout on retrieve is up: " + retrieveTimeout + " seconds");
                throw destroy ? new ConnectionPoolException("connection pool was destroyed")
                        : new ConnectionPoolException("Connection pool timeout on retrieve is up" + retrieveTimeout + " seconds");
            }

        } catch (InterruptedException ex) {
            throw new ConnectionPoolException("thread interrupted");
        }

        Connection connection;

        while ((connection = availableConnections.poll()) == null && !releaseLocker.tryLock()) ;

        if (connection == null) {

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

            releaseLocker.unlock();

            try {
                connection = availableConnections.take();
            } catch (InterruptedException ex) {
                logger.info("waiting for connection force interrupted " + ex);
                return null;
            }

        }//if

        logger.info("add connection to takenConnections: " + takenConnections.offer(connection) + " " + toString());

        retrieveLocker.unlock();

        return connection;

    }

    /**
     * take connection from user, take this connection from{@link ConnectionPool#takenConnections}
     * put this connection into  {@link ConnectionPool#availableConnections} ,
     * in case of {@link ConnectionPool#takenConnections} do not contain it - shut down connection
     * thread safe, use {@link ConnectionPool#releaseLocker}
     * set {@link Connection#setAutoCommit(boolean)} true
     *
     * @param connection connection, that user took before
     * @return true always
     */
    @Override
    public boolean releaseConnection(Connection connection) {

        if (connection == null || destroy) {
            return false;
        }

        try {

            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            logger.warn("setAutoCommitWarn" + ex);
        }

        releaseLocker.lock();

        logger.info("try to release connection");

        boolean isRemoved = takenConnections.remove(connection);

        if (isRemoved) {

            if (availableConnections.offer(connection)) {
                logger.info("release connection successfully");

            } else {

                logger.error("can't release connection, can't add it to available connections " + toString());
                if (!availableConnections.contains(connection)) {
                    shutDownConnection(connection);
                }
            }

        } else {

            logger.warn("can't release connection, takenConnections do not contain it" + toString());
            if (!availableConnections.contains(connection)) {
                shutDownConnection(connection);
            }

        }

        releaseLocker.unlock();

        return true;
    }

    /**
     * destroy connection pool
     * <p>
     * fully lock {@link ConnectionPool#releaseLocker} and {@link ConnectionPool#retrieveLocker}
     * shutdown all connection from {@link ConnectionPool#availableConnections} and {@link ConnectionPool#takenConnections}
     * using {@link ConnectionPool#shutDownConnection(Connection)}
     */
    @Override
    public void destroyConnectionPool() {

        logger.info("destroying connection pool started");

        releaseLocker.lock();
        retrieveLocker.lock();

        while (availableConnections.size() > 0 || takenConnections.size() > 0) {
            shutDownConnection(availableConnections.poll());
            shutDownConnection(takenConnections.poll());
        }

        destroy = true;

        logger.info("destroying connection pool finished");
    }

    /**
     * get this class instance
     *
     * @return {@link ConnectionPool#CONNECTION_POOL_INSTANCE}
     */
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

    @Override
    public boolean isDestroy() {
        return destroy;
    }

    public int getAvailableQuantity() {
        return availableConnections.size();
    }

    public int getTakenConnectionsQuantity() {
        return takenConnections.size();
    }
}
