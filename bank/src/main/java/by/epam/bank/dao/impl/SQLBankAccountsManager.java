package by.epam.bank.dao.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsManager;
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

public class SQLBankAccountsManager implements IBankAccountsManager {

    private static Logger logger = LogManager.getLogger(SQLBankAccountsManager.class);

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    private static final String SQL_INSERT = "INSERT INTO bank_accounts (organisation_id) VALUES (?)";
    private static final String SQL_SET_BLOCK = "UPDATE bank_accounts SET is_blocked = ? WHERE (organisation_id = ?)";
    private static final String SQL_SELECT_IS_BLOCKED = "SELECT is_blocked FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SET_INFO = "UPDATE bank_accounts SET info = ? WHERE (organisation_id = ?)";
    private static final String SQL_SELECT_INFO = "SELECT info FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SELECT_BALANCE = "SELECT balance FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SELECT_MIN_ALLOWED_BALANCE = "SELECT balance FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SET_MIN_ALLOWED_BALANCE = "UPDATE bank_accounts SET min_allowed_balance = ? WHERE (organisation_id = ?)";
    private static final String SQL_SELECT_ACCOUNT =
            "SELECT ba.balance, ba.min_allowed_balance, ba.is_blocked, ba.info, o.id, o.name, o.role, orr.role, o.is_blocked, o.info " +
                    "FROM bank_accounts as ba join organisations as o on id=organisation_id join organisation_roles as orr on o.role=orr.id where organisation_id=?";

    /*
     *
     * true - if created successfully
     * false - if already exist
     *
     */
    @Override
    public BankAccount createBankAccount(int organisationID) throws DAOException {


        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }


        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT); PreparedStatement psS = connection.prepareStatement(SQL_SELECT_ACCOUNT)) {
            ps.setInt(1, organisationID);
            ps.execute();

            psS.setInt(1, organisationID);

            rs = psS.executeQuery();

            if (!rs.next()) {

                logger.info("createBankAccount fail: organization ID(organisation do not exist): " + organisationID);
                return null;
            }

            return new BankAccount(
                    new Organisation(rs.getInt(5), rs.getString(6), new Role(rs.getInt(7),
                            rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                    rs.getDouble(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4));


        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("create bank account fail, duplicate entry: organization ID: " + organisationID, ex);
                return null;
            }

            if ((Pattern.matches(".*a foreign key constraint fails.*", ex.getMessage()))) {
                logger.info("create bank account fail: organization ID: " + organisationID, ex);
                throw new DAOValidationException("organization with this ID do not exist");
            }

            logger.warn("create bank account fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {
            logger.warn("create bank account fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {

            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organisationID, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /*
     *
     *return false - if user don't exist
     *
     */
    private boolean setBlock(int organisationID, boolean blockToSet) throws DAOException {


        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }


        try (PreparedStatement ps = connection.prepareStatement(SQL_SET_BLOCK)) {
            ps.setBoolean(1, blockToSet);
            ps.setInt(2, organisationID);

            if (ps.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("set block fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public boolean blockBankAccounts(int organisationID) throws DAOException {

        return setBlock(organisationID, true);
    }

    @Override
    public boolean unblockBankAccounts(int organisationID) throws DAOException {
        return setBlock(organisationID, false);
    }


    @Override
    public boolean isBlock(int organisationID) throws DAOException {

        ResultSet rs = null;


        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }


        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_IS_BLOCKED)) {
            ps.setInt(1, organisationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("isBlock fail: organization ID(organisation do not exist): " + organisationID);
                throw new DAOValidationException("organisation with this ID do not exist");
            }

            return rs.getBoolean(1);


        } catch (SQLException ex) {
            logger.warn("isBlock fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organisationID, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public boolean isExist(int organisationID) throws DAOException {

        ResultSet rs = null;

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_IS_BLOCKED)) {

            ps.setInt(1, organisationID);

            rs = ps.executeQuery();

            return rs.next();


        } catch (SQLException ex) {
            logger.warn("isExist fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {

            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organisationID, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    /*
     *
     *return false - if user don't exist
     *
     */
    @Override
    public boolean setInfo(int organisationID, String info) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_SET_INFO)) {
            ps.setString(1, info);
            ps.setInt(2, organisationID);

            if (ps.executeUpdate() == 0) {
                logger.info("setInfo fail: organization ID(organisation do not exist): " + organisationID);
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("setInfo fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }


    @Override
    public String getInfo(int organisationID) throws DAOException {
        ResultSet rs = null;

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_INFO)) {
            ps.setInt(1, organisationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("getInfo fail: organization ID(organisation do not exist): " + organisationID);
                throw new DAOValidationException("organization with this ID do not exist");
            }

            return rs.getString(1);


        } catch (SQLException ex) {
            logger.warn("getInfo fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organisationID, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    private double selectBalanceOrMinAllowedBalance(int organisationID, String select) throws DAOException {
        ResultSet rs = null;

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(select)) {
            ps.setInt(1, organisationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info(select + " fail: organization ID(organisation do not exist): " + organisationID);
                throw new DAOValidationException("organization with this ID do not exist");
            }

            return rs.getDouble(1);


        } catch (SQLException ex) {
            logger.warn("getBalance fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organisationID, ex);

                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public double getBalance(int organisationID) throws DAOException {

        return selectBalanceOrMinAllowedBalance(organisationID, SQL_SELECT_BALANCE);

    }

    @Override
    public double getMinAllowedBalance(int organisationID) throws DAOException {
        return selectBalanceOrMinAllowedBalance(organisationID, SQL_SELECT_MIN_ALLOWED_BALANCE);
    }


    @Override
    public boolean setMinAllowedBalance(int organisationID, double minAllowedBalance) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_SET_MIN_ALLOWED_BALANCE)) {
            ps.setDouble(1, minAllowedBalance);
            ps.setInt(2, organisationID);

            if (ps.executeUpdate() == 0) {
                logger.info("setMinAllowedBalance fail: organization ID(organisation do not exist): " + organisationID);
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("setMinAllowedBalance fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }


    /*
     *
     *return null - if organization don't exist
     *
     */
    @Override
    public BankAccount getBankAccount(int organisationID) throws DAOException {
        ResultSet rs = null;

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ACCOUNT)) {
            ps.setInt(1, organisationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("getBankAccount fail: organization ID(organisation do not exist): " + organisationID);
                return null;
            }

            return new BankAccount(
                    new Organisation(rs.getInt(5), rs.getString(6), new Role(rs.getInt(7),
                            rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                    rs.getDouble(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4));


        } catch (SQLException ex) {
            logger.warn("getBankAccount fail: organization ID: " + organisationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organisationID, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
