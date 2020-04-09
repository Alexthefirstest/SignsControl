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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.regex.Pattern;

public class SQLFinanceOperationsManager implements IFinanceOperationsManager {

    private static Logger logger = LogManager.getLogger(SQLFinanceOperationsManager.class);

    private static final String SQL_ADD_MONEY = "UPDATE bank_accounts SET balance = balance+? WHERE (`organisation_id` = ?);";

    private static final String SQL_UPDATE_TRANSACTION_TAKE_FROM =
            " UPDATE bank_accounts SET balance = if(balance-?>min_allowed_balance, balance-?, null) " +
                    "WHERE (organisation_id = ?);";

    private static final String SQL_UPDATE_TRANSACTION_SEND_TO = "UPDATE bank_accounts SET balance = balance+? WHERE (organisation_id = ?);";

    private static final IConnectionPool connectionPool = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();


    @Override
    public boolean transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException {

        Connection connection = connectionPool.retrieveConnection();

        try (PreparedStatement psFrom = connection.prepareStatement(SQL_UPDATE_TRANSACTION_TAKE_FROM);
             PreparedStatement psTo = connection.prepareStatement(SQL_UPDATE_TRANSACTION_SEND_TO)) {

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

            connection.commit();

        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("wrong balance from " + ex);
                throw new DAOValidationException("user from haven't enough money");
            }
            logger.warn("transaction fail " + ex);
            throw new DAOException("transaction fail" + ex);

        } catch (SQLException ex) {
            logger.warn("transaction fail " + ex);
            throw new DAOException("transaction fail" + ex);

        } catch (DAOException ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollback fail" + e);
            }

            throw ex;

        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                logger.warn("setAutoCommit fail" + ex);
            }
            connectionPool.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public boolean addMoney(int organisationID, double money) throws DAOException {

        Connection connection = connectionPool.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_ADD_MONEY)) {

            if (ps.executeUpdate() == 0) {
                logger.info("addMoney fail, wrong user");
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("addMoney fail " + ex);
            throw new DAOException("transaction fail" + ex);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return true;
    }

}
