package by.epam.bank.dao.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsManager;
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

public class SQLBankAccountsManager implements IBankAccountsManager {

    private static Logger logger = LogManager.getLogger(SQLBankAccountsManager.class);

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    private static final String SQL_INSERT
            = "INSERT INTO bank_accounts (organisation_id) VALUES (?)";
    private static final String SQL_SET_BLOCK =
            "UPDATE bank_accounts SET is_blocked = ? WHERE (organisation_id = ?)";
    private static final String SQL_SELECT_IS_BLOCKED =
            "SELECT is_blocked FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SET_INFO =
            "UPDATE bank_accounts SET info = ? WHERE (organisation_id = ?)";
    private static final String SQL_SELECT_INFO =
            "SELECT info FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SELECT_BALANCE =
            "SELECT balance FROM bank_accounts WHERE organisation_id = ?";
    private static final String SQL_SELECT_ACCOUNT =
            "SELECT * FROM bank_accounts WHERE organisation_id = ?";

    /*
     *
     * true - if created successfully
     * false - if already exist
     *
     */
    @Override
    public boolean createBankAccount(int organizationID) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, organizationID);
            ps.execute();

        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("create bank account fail: organization ID: " + organizationID, ex);
                return false;
            }

            if ((Pattern.matches(".*a foreign key constraint fails.*", ex.getMessage()))) {
                logger.info("create bank account fail: organization ID: " + organizationID, ex);
                throw new DAOValidationException("organization with this ID do not exist");
            }

            logger.warn("create bank account fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {
            logger.warn("create bank account fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }

    /*
     *
     *return false - if user don't exist
     *
     */
    private boolean setBlock(int organizationID, boolean blockToSet) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SET_BLOCK)) {
            ps.setBoolean(1, blockToSet);
            ps.setInt(2, organizationID);

            if (ps.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("set block fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public boolean blockBankAccounts(int organizationID) throws DAOException {

        return setBlock(organizationID, true);
    }

    @Override
    public boolean unblockBankAccounts(int organizationID) throws DAOException {
        return setBlock(organizationID, false);
    }


    @Override
    public boolean isBlock(int organizationID) throws DAOException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_IS_BLOCKED)) {
            ps.setInt(1, organizationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("isBlock fail: organization ID(organisation do not exist): " + organizationID);
                throw new DAOValidationException("organisation with this ID do not exist");
            }

            return rs.getBoolean(1);


        } catch (SQLException ex) {
            logger.warn("isBlock fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organizationID, ex);
                    CONNECTION_POOL.releaseConnection(connection);
                    throw new DAOException("isBlock fail " + ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public boolean isExist(int organizationID) throws DAOException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_IS_BLOCKED)) {

            ps.setInt(1, organizationID);

            rs = ps.executeQuery();

            return rs.next();


        } catch (SQLException ex) {
            logger.warn("isExist fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {

            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organizationID, ex);
                    CONNECTION_POOL.releaseConnection(connection);
                    throw new DAOException("isExist fail", ex);
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
    public boolean setInfo(int organizationID, String info) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SET_INFO)) {
            ps.setString(1, info);
            ps.setInt(2, organizationID);

            if (ps.executeUpdate() == 0) {
                logger.info("setInfo fail: organization ID(organisation do not exist): " + organizationID);
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("setInfo fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }


    @Override
    public String getInfo(int organizationID) throws DAOException {
        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_INFO)) {
            ps.setInt(1, organizationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("getInfo fail: organization ID(organisation do not exist): " + organizationID);
                throw new DAOValidationException("organization with this ID do not exist");
            }

            return rs.getString(1);


        } catch (SQLException ex) {
            logger.warn("getInfo fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organizationID, ex);
                    CONNECTION_POOL.releaseConnection(connection);
                    throw new DAOException(ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    @Override
    public long getBalance(int organizationID) throws DAOException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BALANCE)) {
            ps.setInt(1, organizationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("getBalance fail: organization ID(organisation do not exist): " + organizationID);
                throw new DAOValidationException("organization with this ID do not exist");
            }

            return rs.getLong(1);


        } catch (SQLException ex) {
            logger.warn("getBalance fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organizationID, ex);
                    CONNECTION_POOL.releaseConnection(connection);
                    throw new DAOException(ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /*
     *
     *return null - if organization don't exist
     *
     */
    @Override
    public BankAccount getBankAccount(int organizationID) throws DAOException {
        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ACCOUNT)) {
            ps.setInt(1, organizationID);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("getBankAccount fail: organization ID(organisation do not exist): " + organizationID);
                return null;
            }

            return new BankAccount(rs.getInt(1), rs.getLong(2),
                    rs.getBoolean(3), rs.getString(4));


        } catch (SQLException ex) {
            logger.warn("getBankAccount fail: organization ID: " + organizationID, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(organizationID, ex);
                    CONNECTION_POOL.releaseConnection(connection);
                    throw new DAOException(ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
