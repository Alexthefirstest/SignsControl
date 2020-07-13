package by.epam.bank.dao.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
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

public class TransactionsDeliver implements ITransactionsDeliver {

    private Logger logger = LogManager.getLogger(TransactionsDeliver.class);

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    private static final String SQL_GET_TRANSACTIONS =
            "SELECT t.id, money, date_time, o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, o2.id, o2.name, o2.role, orr2.role, o2.is_blocked, o2.info " +
                    "FROM transactions as t join organisations as o1 on t.from=o1.id join organisations as o2 on t.to=o2.id " +
                    "join organisation_roles as orr1 on o1.role=orr1.id join organisation_roles as orr2 on o2.role=orr2.id";

    private static final String SQL_ORDER_BY_ID = " order by t.id DESC";
    private static final String SQL_WHERE_ID = " where t.id=?";
    private static final String SQL_WHERE_ID_FROM = " where t.from=?";
    private static final String SQL_WHERE_ID_FROM_ID_TO = " where t.from=? AND t.to=?";
    private static final String SQL_WHERE_ID_FROM_DATE = "  WHERE t.from=? " +
            "AND date_time BETWEEN STR_TO_DATE(?, '%Y.%m.%d %H:%i:%s') AND STR_TO_DATE(?, '%Y.%m.%d %H:%i:%s') ";

    @Override
    public Transaction[] getTransactions() throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_ORDER_BY_ID);
    }

    @Override
    public Transaction[] findTransaction(int id) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID, id);
    }

    @Override
    public Transaction[] findTransactionsByFrom(int idFrom) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM + SQL_ORDER_BY_ID, idFrom);
    }


    @Override
    public Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws DAOException {
        return executeRequest(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM_ID_TO + SQL_ORDER_BY_ID, idFrom, idTo);
    }

    @Override
    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(SQL_GET_TRANSACTIONS + SQL_WHERE_ID_FROM_DATE + SQL_ORDER_BY_ID)) {

            ps.setInt(1, idFrom);
            ps.setString(2, dateFrom);
            ps.setString(3, dateTo);

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

    private Transaction[] executeRequest(String request, int... parameters) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

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

    //return [0] if nothing
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
}
