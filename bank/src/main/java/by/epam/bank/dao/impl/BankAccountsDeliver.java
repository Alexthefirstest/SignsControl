package by.epam.bank.dao.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsDeliver;
import by.epam.bank.dao.IRequest;
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
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

public class BankAccountsDeliver implements IBankAccountsDeliver {

    public BankAccountsDeliver() {

    }

    private Logger logger = LogManager.getLogger(BankAccountsDeliver.class);

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();


    public BankAccount[] executeRequest(IRequest selectRequestHolder) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        ResultSet rs = null;

        String selectRequest = selectRequestHolder.getRequest();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectRequest)) {

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bankAccounts.add(new BankAccount(rs.getString(1), rs.getInt(2), rs.getDouble(3),
                        rs.getDouble(4), rs.getBoolean(5), rs.getString(6)));
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