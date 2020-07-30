package by.epam.bank.dao;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.exceptions.DAOException;

public interface IFinanceOperationsManager {

    /**
     * transact money, create transaction to the history
     *
     * @param organisationIDFrom sender
     * @param organisationIDTo   payee
     * @param money              summ of money to transact
     * @return {@link Transaction} if success
     * @throws DAOException when get an exception during execution
     */
    Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException;

    /**
     * add money to account with organisation id param
     *
     * @param bankID         id of bank for transactions history sender - do not withdraw money from it's account
     * @param organisationID to add money
     * @param money          sum of money to add
     * @return {@link Transaction} if success
     * @throws DAOException when get an exception during execution
     */
    Transaction addMoney(int bankID, int organisationID, double money) throws DAOException;
}
