package by.epam.bank.dao.impl;

import by.epam.bank.dao.IFinanceOperationsManager;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.regex.Pattern;

public class SQLFinanceOperationsManager implements IFinanceOperationsManager {

    private static Logger logger = LogManager.getLogger(SQLFinanceOperationsManager.class);

    private static final String SQL_UPDATE_TRANSACTION_TAKE_FROM =
            " UPDATE bank_accounts SET balance = if(balance-?>=min_allowed_balance AND is_blocked=false, balance-?, null) " +
                    "WHERE (organisation_id = ?);";
    private static final String SQL_UPDATE_TRANSACTION_SEND_TO =
            "UPDATE bank_accounts SET balance = if(is_blocked=false, balance+?, null) WHERE (organisation_id = ?);";

    private static final String SQL_ADD_TRANSACTION = "INSERT INTO `transactions` (`from`, `to`, `money`) VALUES (?, ?, ?);";
    private static final String SQL_SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() FROM transactions where id=LAST_INSERT_ID();";

    private static final IConnectionPool connectionPool = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();


    //return transaction id
    @Override
    public int transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException {

        Connection connection = connectionPool.retrieveConnection();

        ResultSet rs = null;

        try (PreparedStatement psFrom = connection.prepareStatement(SQL_UPDATE_TRANSACTION_TAKE_FROM);
             PreparedStatement psTo = connection.prepareStatement(SQL_UPDATE_TRANSACTION_SEND_TO);
             PreparedStatement addTransaction = connection.prepareStatement(SQL_ADD_TRANSACTION);
             PreparedStatement transaction = connection.prepareStatement(SQL_SELECT_LAST_INSERT_ID)) {

            connection.setAutoCommit(false);

            psFrom.setDouble(1, money);
            psFrom.setDouble(2, money);
            psFrom.setInt(3, organisationIDFrom);

            if (psFrom.executeUpdate() == 0) {
                logger.info("wrong organisation from: " + organisationIDFrom);
                throw new DAOValidationException("organisation from do not exist");
            }

            psTo.setDouble(1, money);
            psTo.setInt(2, organisationIDTo);

            if (psTo.executeUpdate() == 0) {
                logger.info("wrong organisation to " + organisationIDTo);
                throw new DAOValidationException("organisation to do not exist");
            }

            addTransaction.setInt(1, organisationIDFrom);
            addTransaction.setInt(2, organisationIDTo);
            addTransaction.setDouble(3, money);
            addTransaction.executeUpdate();


            rs = transaction.executeQuery();

            connection.commit();

            int lastID = -1;

            if (!rs.next() || (lastID = rs.getInt(1)) == 0) {
                logger.warn("last id wasn't found " + lastID);
                throw new DAOException("transaction add fail - last id wasn't found: last id " + lastID);
            }

            return lastID;

        } catch (SQLIntegrityConstraintViolationException ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollback fail", e);
            }

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("operation fail cause bank account blocked or not enough money ", ex);
                throw new DAOValidationException("operation fail cause bank account blocked or not enough money");
            } else {
                logger.warn("transaction fail ", ex);
                throw new DAOException("transaction fail", ex);
            }


        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollback fail", e);
            }

            logger.warn("transaction fail ", ex);
            throw new DAOException("transaction fail", ex);

        } catch (DAOException ex) {

            logger.warn("transaction fail ", ex);

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollback fail", e);
            }

            throw ex;

        } finally {

            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn("rs.close fail", ex);
                }

            }

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                logger.warn("setAutoCommit fail", ex);
            }
            connectionPool.releaseConnection(connection);
        }


    }

    @Override
    public boolean addMoney(int organisationID, double money) throws DAOException {

        Connection connection = connectionPool.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_TRANSACTION_SEND_TO)) {

            ps.setDouble(1, money);
            ps.setInt(2,organisationID);

            if (ps.executeUpdate() == 0) {
                logger.info("addMoney fail, wrong user");
                return false;
            }

        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("operation fail cause bank account blocked", ex);
                throw new DAOValidationException("operation fail cause bank account blocked");
            } else {
                logger.warn("addMoney fail ", ex);
                throw new DAOException("addMoney fail", ex);
            }

        } catch (SQLException ex) {
            logger.warn("addMoney fail " + ex);
            throw new DAOException("add money fail", ex);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

}
