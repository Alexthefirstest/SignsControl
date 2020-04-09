package by.epam.bank.dao.impl;

import by.epam.bank.bean.BankAccount;
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

public class BankAccountsDeliver {

    private Logger logger = LogManager.getLogger(BankAccountsDeliver.class);

    private String selectRequest = "SELECT name, organisation_id, balance, min_allowed_balance, ba.is_blocked, ba.info " +
            "FROM bank_accounts as ba join organisations on id=organisation_id";

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    public BankAccount[] executeRequest() throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        ResultSet rs = null;

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

            logger.warn("executeRequest fail " + ex);
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


    public static class RequestBuilder {

        private String where = "";
        private String orderBy = "";

        public RequestBuilder() {
        }

        public RequestBuilder withSortByOrganisationName() {
            orderBy += " name,";
            return this;
        }

        public RequestBuilder withSortByBalance() {
            orderBy += " balance,";
            return this;
        }

        public RequestBuilder withSortByMinAllowedBalance() {
            orderBy += " min_allowed_balance,";
            return this;
        }

        public RequestBuilder withSortByBlocked() {
            orderBy += " ba.is_blocked,";
            return this;
        }

        /*
        *
        *call after withSortBy.*
        *
        */
        public RequestBuilder setDESC() {
            orderBy = orderBy.substring(0, orderBy.length() - 1);
            orderBy += " DESC,";
            return this;
        }


        public RequestBuilder showOnlyWithNegativeBalance() {
            where += " balance<0,";
            return this;
        }

        public RequestBuilder showOnlyWithPositiveBalance() {
            where += " balance>=0,";
            return this;
        }

        public RequestBuilder whereBalanceMoreThen(int number) {
            where += " balance>=" + number + ",";
            return this;
        }

        public RequestBuilder whereBalanceLessThen(int number) {
            where += " balance<" + number + ",";
            return this;
        }

        public RequestBuilder showOnlyBlocked() {
            where += " ba.is_blocked=true,";
            return this;
        }


        public RequestBuilder showOnlyUnblocked() {
            where += " ba.is_blocked=false,";
            return this;
        }


        public BankAccountsDeliver build() {

            BankAccountsDeliver bankAccountsDeliver = new BankAccountsDeliver();

            if (!where.isEmpty()) {
                bankAccountsDeliver.selectRequest += " where" + where.substring(0, where.length() - 1);
            }

            if (!orderBy.isEmpty()) {
                bankAccountsDeliver.selectRequest += " order by" + orderBy.substring(0, orderBy.length() - 1);
            }

            return bankAccountsDeliver;
        }


    }


}
