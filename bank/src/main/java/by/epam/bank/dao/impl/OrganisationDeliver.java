package by.epam.bank.dao.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IOrganisationsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
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
import java.util.ArrayList;

/**
 * class for supply organisations from data base
 */
public class OrganisationDeliver implements IOrganisationsDeliver {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(OrganisationDeliver.class);

    /**
     * get organisation with no bank accounts request
     */
    private static final String GET_ORGANISATIONS_WITHOUT_BANK_ACCOUNTS = " SELECT org.id, org.name, org.role, orgR.role, org.is_blocked, " +
            " org.info FROM organisations as org left join bank_accounts on bank_accounts.organisation_id=org.id join organisation_roles as orgR on org.role=orgR.id " +
            " where bank_accounts.balance is null order by org.name ";

    /**
     * get organisation by id with or without bank account
     */
    private static final String GET_ORGANISATION_WITH_BANK_ACCOUNT_BY_ID = " SELECT ba.balance, ba.min_allowed_balance, ba.is_blocked, ba.info, o.id, o.name, o.role, orr.role, o.is_blocked, o.info FROM bank_accounts as ba right join organisations as o on id=organisation_id join organisation_roles as orr on o.role=orr.id where o.id=? ";

    /**
     * {@link IConnectionPool} instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * get organisations with no bank accounts
     *
     * @return {@link Organisation}
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Organisation[] showOrganisationsWithoutBankAccounts() throws DAOException {
        ResultSet rs = null;


        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }


        try (PreparedStatement ps = connection.prepareStatement(GET_ORGANISATIONS_WITHOUT_BANK_ACCOUNTS)) {

            rs = ps.executeQuery();

            ArrayList<Organisation> organisations = new ArrayList<>();

            while (rs.next()) {

                organisations.add
                        (new Organisation(rs.getInt(1), rs.getString(2),
                                new Role(rs.getInt(3), rs.getString(4)), rs.getBoolean(5), rs.getString(6)));
            }

            return organisations.toArray(new Organisation[0]);


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn("closeResultSetEx", ex);
                }

            }

            ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance().releaseConnection(connection);
        }
    }

    /**
     * get organisation with bank account or without in case account don't exist by id
     *
     * @param id organisation id
     * @return {@link Organisation}
     * @throws DAOException when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public BankAccount findOrganisationByID(int id) throws DAOException {
        ResultSet rs = null;


        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }


        try (PreparedStatement ps = connection.prepareStatement(GET_ORGANISATION_WITH_BANK_ACCOUNT_BY_ID)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            return rs.next() ? new BankAccount(
                    new Organisation(rs.getInt(5), rs.getString(6), new Role(rs.getInt(7),
                            rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                    rs.getDouble(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4))

                    : null;


        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn("closeResultSetEx", ex);
                }

            }

            ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance().releaseConnection(connection);
        }
    }
}
