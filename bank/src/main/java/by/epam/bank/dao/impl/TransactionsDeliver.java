package by.epam.bank.dao.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

/**
 * transactions from data base supplier class
 */
public class TransactionsDeliver implements ITransactionsDeliver {

    /**
     * logger
     */
    private Logger logger = LogManager.getLogger(TransactionsDeliver.class);

    /**
     * {@link IConnectionPool} instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * get all transactions request
     */
    private static final String SQL_GET_TRANSACTIONS =
            "SELECT t.id, money, date_time, o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, o2.id, o2.name, o2.role, orr2.role, o2.is_blocked, o2.info " +
                    "FROM transactions as t join organisations as o1 on t.from=o1.id join organisations as o2 on t.to=o2.id " +
                    "join organisation_roles as orr1 on o1.role=orr1.id join organisation_roles as orr2 on o2.role=orr2.id";

    /**
     * sql get count of fields
     */
    private static final String SQL_FIELDS_COUNT = " SELECT count(id) FROM transactions as t ";

    /**
     * sql additional request order by
     */
    private static final String SQL_ORDER_BY_ID = " order by t.id DESC";

    /**
     * sql additional request find by id
     */
    private static final String SQL_WHERE_ID = " where t.id=?";

    /**
     * sql additional request find by organisation sender id
     */
    private static final String SQL_WHERE_ID_FROM = " where t.from=?";


    /**
     * sql additional request find by organisation sender id and organisation payee id
     */
    private static final String SQL_WHERE_ID_FROM_ID_TO = " where t.from=? AND t.to=?";

    /**
     * sql additional request find by date between parameters
     */
    private static final String SQL_WHERE_ID_FROM_DATE = "  WHERE t.from=? " +
            "AND date_time BETWEEN STR_TO_DATE(?, '%Y.%m.%d %H:%i:%s') AND STR_TO_DATE(?, '%Y.%m.%d %H:%i:%s') ";


    /**
     * sql additional request limit of out strings with start position
     */
    private static final String SQL_LIMIT = " LIMIT ?,? ";

    /**
     * get all transactions order by id
     *
     * @return {@link Transaction} array
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] getTransactions() throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_ORDER_BY_ID);
    }

    /**
     * get transactions order by id from position and quantity count
     *
     * @param startPosition start position in sql to output
     * @param count         count of transactions
     * @return {@link Transaction} array
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] getTransactions(int startPosition, int count) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_ORDER_BY_ID + SQL_LIMIT, startPosition, count);
    }

    /**
     * find transaction by id
     *
     * @param id to find
     * @return {@link Transaction} or null if can't find
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction findTransaction(int id) throws DAOException {

        Transaction[] transactions = executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID, id);

        return transactions.length > 0 ? transactions[0] : null;
    }

    /**
     * find transaction by organisation sender id order by id
     *
     * @param idFrom sender id
     * @return {@link Transaction} array
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] findTransactionsByFrom(int idFrom) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM + SQL_ORDER_BY_ID, idFrom);
    }

    /**
     * get transaction by organisation sender id from position param count param order by id
     *
     * @param idFrom        sender id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] findTransactionsByFrom(int idFrom, int startPosition, int count) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM + SQL_ORDER_BY_ID + SQL_LIMIT,
                idFrom, startPosition, count);
    }

    /**
     * get transaction by organisation sender id and organisation payee order by id
     *
     * @param idFrom sender id
     * @param idTo   payee id
     * @return {@link Transaction} array
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM_ID_TO + SQL_ORDER_BY_ID, idFrom, idTo);
    }

    /**
     * get transaction by organisation sender id and organisation payee from position param count param order by id
     *
     * @param idFrom        sender id
     * @param idTo          payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo, int startPosition, int count) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM_ID_TO + SQL_ORDER_BY_ID + SQL_LIMIT,
                idFrom, idTo, startPosition, count);
    }

    /**
     * get transaction by execution date between datefrom and dateto order by
     *
     * @param idFrom   transaction sender
     * @param dateFrom start date to search
     * @param dateTo   finish date to search
     * @return {@link Transaction} array
     * @throws DAOValidationException when wrong date syntax
     * @throws DAOException           when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException           when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws DAOException {
        return findTransactionsByDateSQL(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM_DATE + SQL_ORDER_BY_ID,
                idFrom, dateFrom, dateTo, -1, -1);
    }

    /**
     * get all transactions count
     *
     * @return fields count
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public int getFieldsCount() throws DAOException {

        return getFieldsCount(SQL_FIELDS_COUNT);
    }

    /**
     * get transactions count with sender id
     *
     * @param idFrom sender id
     * @return fields count
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public int getFieldsCountByFrom(int idFrom) throws DAOException {
        return getFieldsCount(SQL_FIELDS_COUNT + SQL_WHERE_ID_FROM, idFrom);
    }

    /**
     * get transactions count with sender id and payee id
     *
     * @param idFrom sender id
     * @param idTo   payed id
     * @return fields count
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public int getFieldsCountByFromAndTo(int idFrom, int idTo) throws DAOException {
        return getFieldsCount(SQL_FIELDS_COUNT + SQL_WHERE_ID_FROM_ID_TO, idFrom, idTo);
    }

    /**
     * get transactions count with sender and date of execution between dateFrom and dateTo
     *
     * @param idFrom   sender id
     * @param dateFrom date to start search
     * @param dateTo   finish date for search
     * @return fields count
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws DAOException {
        return getFieldsCount(SQL_FIELDS_COUNT + SQL_WHERE_ID_FROM_DATE,
                idFrom, dateFrom, dateTo);
    }

    /**
     * show transaction with sender id and date of execution between dateFrom dateTo start from start position and get count quantity
     *
     * @param idFrom        sender
     * @param dateFrom      start date
     * @param dateTo        finish date
     * @param startPosition start position to return result
     * @param count         of return transaction
     * @return {@link Transaction} array
     * @throws DAOValidationException when wrong date syntax
     * @throws DAOException           when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException           when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws DAOException {
        return findTransactionsByDateSQL(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM_DATE + SQL_ORDER_BY_ID + SQL_LIMIT,
                idFrom, dateFrom, dateTo, startPosition, count);
    }

    /*
     *
     *find transaction by date
     */
    private Transaction[] findTransactionsByDateSQL(String sql, int idFrom, String dateFrom, String dateTo,
                                                    int startPosition, int count) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idFrom);
            ps.setString(2, dateFrom);
            ps.setString(3, dateTo);

            if (startPosition > -1) {
                ps.setInt(4, startPosition);
                ps.setInt(5, count);
            }

            rs = ps.executeQuery();

            return resultSetToArray(rs);

        } catch (SQLSyntaxErrorException ex) {
            logger.warn("wrong syntax " + ex);
            throw new DAOValidationException("wrong syntax");

        } catch (SQLException ex) {
            logger.warn("find by date method " + ex);
            throw new DAOException(ex);
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.warn("ResultStatement close exception ", e);
                }
            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    /*
     *
     * execute request with parameters add
     *
     */
    private Transaction[] executeRequest(String request, int... parameters) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            for (int i = 0; i < parameters.length; i++) {
                ps.setInt(i + 1, parameters[i]);
            }

            rs = ps.executeQuery();


            return resultSetToArray(rs);

        } catch (SQLException ex) {
            logger.warn(ex);
            throw new DAOException(ex);
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.warn("ResultStatement close exception ", e);
                }
            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /**
     * result set to array, return [0] if result set is empty
     */
    private Transaction[] resultSetToArray(ResultSet rs) throws SQLException {

        ArrayList<Transaction> transactions = new ArrayList<>();

        while (rs.next()) {

            transactions.add(new Transaction(rs.getInt(1), new Organisation(rs.getInt(4), rs.getString(5),
                    new Role(rs.getInt(6), rs.getString(7)), rs.getBoolean(8), rs.getString(9)),
                    new Organisation(rs.getInt(10), rs.getString(11),
                            new Role(rs.getInt(12), rs.getString(13)), rs.getBoolean(14), rs.getString(15)),
                    rs.getDouble(2), rs.getTimestamp(3)));

        }

        return transactions.toArray(new Transaction[0]);
    }

    /*
     * return field count for request, add parameters to request
     */
    private int getFieldsCount(String request, Object... parameters) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) parameters[i]);
                }
                if (parameters[i] instanceof String) {
                    ps.setString(i + 1, parameters[i].toString());
                }
            }

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException ex) {
            logger.warn("find coordinates method " + ex);
            throw new DAOException(ex);


        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.warn("ResultStatement close exception ", e);
                }
            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
