package by.epam.bank.dao.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsDeliver;
import by.epam.bank.dao.IRequest;
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
 * class for supply bank accounts
 * <p>
 * have a method to execute {@link IRequest}
 */
public class BankAccountsDeliver implements IBankAccountsDeliver {

    /**
     * empty constructor
     */
    public BankAccountsDeliver() {

    }

    /**
     * logger
     */
    private Logger logger = LogManager.getLogger(BankAccountsDeliver.class);

    /**
     * {@link IConnectionPool} instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * execute {@link IRequest}
     *
     * @param selectRequestHolder {@link IRequest}
     * @return {@link BankAccount} array
     * @throws DAOValidationException when wrong {@link IRequest}
     * @throws DAOException           when catch {@link SQLException} from {@link ResultSet} or {@link PreparedStatement}
     * @throws DAOException           when {@link IConnectionPool} throw exception
     */
    public BankAccount[] executeRequest(IRequest selectRequestHolder) throws DAOException {


        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }


        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        ResultSet rs = null;

        String selectRequest = selectRequestHolder.getRequest();

        logger.info(selectRequest);


        try (PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)) {

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bankAccounts.add(new BankAccount(
                        new Organisation(rs.getInt(5), rs.getString(6), new Role(rs.getInt(7),
                                rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                        rs.getDouble(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4)));
            }


            return bankAccounts.toArray(new BankAccount[0]);

        } catch (SQLSyntaxErrorException ex) {
            ex.printStackTrace();
            logger.info("wrong request" + selectRequest);
            throw new DAOValidationException("wrong request: " + selectRequest);

        } catch (SQLException ex) {

            logger.warn("executeRequest fail ", ex);
            throw new DAOException(ex);

        } finally {

            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(selectRequest, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }


    }


}
