package by.epam.bank.dao.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.IFinanceOperationsManager;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

/**
 * class for transact money
 */
public class SQLFinanceOperationsManager implements IFinanceOperationsManager {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(SQLFinanceOperationsManager.class);

    /**
     * {@link IConnectionPool} instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * get money from bank account in case it't enough to do it  and not blocked
     */
    private static final String SQL_UPDATE_TRANSACTION_TAKE_FROM =
            " UPDATE bank_accounts SET balance = if(balance-?>=min_allowed_balance AND is_blocked=false, balance-?, null) " +
                    "WHERE (organisation_id = ?);";

    /**
     * add money to account if it's wasn't blocked
     */
    private static final String SQL_UPDATE_TRANSACTION_SEND_TO =
            "UPDATE bank_accounts SET balance = if(is_blocked=false, balance+?, null) WHERE (organisation_id = ?);";

    /**
     * create transaction table row
     */
    private static final String SQL_ADD_TRANSACTION = "INSERT INTO `transactions` (`from`, `to`, `money`) VALUES (?, ?, ?);";

    /**
     * select last inserted transaction
     */
    private static final String SQL_SELECT_LAST_INSERT_ID =
            "SELECT t.id, money, date_time, o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, o2.id, o2.name, o2.role, orr2.role, o2.is_blocked, o2.info " +
                    "FROM transactions as t join organisations as o1 on t.from=o1.id join organisations as o2 on t.to=o2.id " +
                    "join organisation_roles as orr1 on o1.role=orr1.id join organisation_roles as orr2 on o2.role=orr2.id where t.id=LAST_INSERT_ID()";



    /**
     *
     * transact money, create transaction to the history
     *
     * @param organisationIDFrom sender
     * @param organisationIDTo  payee
     * @param money summ of money to transact
     * @return {@link Transaction} if success
     * @throws DAOValidationException when organisation from or to do not exist, bank account blocked or haven't enough money
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        }catch (ConnectionPoolException ex){
            throw new DAOException(ex.getMessage());
        }

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
                throw new DAOValidationException("organisation sender has not bank account");
            }

            psTo.setDouble(1, money);
            psTo.setInt(2, organisationIDTo);

            if (psTo.executeUpdate() == 0) {
                logger.info("wrong organisation to " + organisationIDTo);
                throw new DAOValidationException("organisation payee has not bank account");
            }

            addTransaction.setInt(1, organisationIDFrom);
            addTransaction.setInt(2, organisationIDTo);
            addTransaction.setDouble(3, money);
            addTransaction.executeUpdate();


            rs = transaction.executeQuery();

            connection.commit();

            int lastID = -1;

            if (!rs.next() || (lastID = rs.getInt(1)) < 1) {
                logger.warn("last id wasn't found " + lastID);
                throw new DAOException("transaction add fail - last id wasn't found: last id " + lastID);
            }

            return resultSetToTransaction(rs);

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
            CONNECTION_POOL.releaseConnection(connection);
        }


    }

    /**
     *  add money to account with organisation id param
     *
     * @param bankID id of bank for transactions history sender - do not withdraw money from it's account
     * @param organisationID to add money
     * @param money sum of money to add
     * @return {@link Transaction} if success
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Transaction addMoney(int bankID, int organisationID, double money) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        }catch (ConnectionPoolException ex){
            throw new DAOException(ex.getMessage());
        }

        ResultSet rs = null;

        try (PreparedStatement sendTo = connection.prepareStatement(SQL_UPDATE_TRANSACTION_SEND_TO);
             PreparedStatement addTransaction = connection.prepareStatement(SQL_ADD_TRANSACTION);
             PreparedStatement transaction = connection.prepareStatement(SQL_SELECT_LAST_INSERT_ID)) {

            connection.setAutoCommit(false);

            sendTo.setDouble(1, money);
            sendTo.setInt(2, organisationID);

            if (sendTo.executeUpdate() == 0) {
                throw new DAOValidationException("wrong user " + organisationID);
            }

            addTransaction.setInt(1, bankID);
            addTransaction.setInt(2, organisationID);
            addTransaction.setDouble(3, money);
            addTransaction.executeUpdate();

            rs = transaction.executeQuery();

            connection.commit();

            int lastID = -1;

            if (!rs.next() || (lastID = rs.getInt(1)) == 0) {
                logger.warn("last id wasn't found " + lastID);
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    logger.warn("rollback fail", e);
                }
                throw new DAOValidationException("transaction add fail - last id wasn't found: last id " + lastID);
            }

            return resultSetToTransaction(rs);

        } catch (SQLIntegrityConstraintViolationException ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollback fail", e);
            }

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("operation fail cause bank account blocked", ex);
                throw new DAOValidationException("operation fail cause bank account blocked");
            } else {
                logger.warn("addMoney fail ", ex);
                throw new DAOException("addMoney fail", ex);
            }

        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollback fail", e);
            }

            logger.warn("addMoney fail " + ex);
            throw new DAOException("add money fail", ex);
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
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    private Transaction resultSetToTransaction(ResultSet rs) throws SQLException {


        return new Transaction(rs.getInt(1), new Organisation(rs.getInt(4), rs.getString(5),
                new Role(rs.getInt(6), rs.getString(7)), rs.getBoolean(8), rs.getString(9)),
                new Organisation(rs.getInt(10), rs.getString(11),
                        new Role(rs.getInt(12), rs.getString(13)), rs.getBoolean(14), rs.getString(15)),
                rs.getDouble(2), rs.getTimestamp(3));


    }

}
