package by.epam.bank.service;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.exceptions.ServiceException;

/**
 * service for working with {@link by.epam.bank.dao.IFinanceOperationsManager}
 * get, add, set parameters to sql table and validate
 */
public interface IFinanceOperationsManagerService {


    /**
     *
     * transact money, create transaction to the history
     *
     * @param organisationIDFrom sender
     * @param organisationIDTo  payee
     * @param money summ of money to transact
     * @return {@link Transaction} if success
     * @throws ServiceException when get ad exception during execution
     */
    Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws ServiceException;

    /**
     *  add money to account with organisation id param
     *
     * @param bankID id of bank for transactions history sender - do not withdraw money from it's account
     * @param organisationID to add money
     * @param money sum of money to add
     * @return {@link Transaction} if success
     * @throws ServiceException when get ad exception during execution
     */
    Transaction addMoney(int bankID,int organisationID, double money) throws ServiceException;
}
