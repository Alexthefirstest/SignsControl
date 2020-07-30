package by.epam.bank.service;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.service.exceptions.ServiceException;

/**
 * service for working with {@link BankAccount} and {@link by.epam.bank.dao.IBankAccountsManager},
 * get, add, set parameters to sql table
 */
public interface IBankAccountsManagerService {


    /**
     * @param organisationID to create account
     * @return {@link BankAccount} if success or null if wrong organisation id or account already exist
     * @throws ServiceException when get ad exception during execution
     */
    BankAccount createBankAccount(int organisationID) throws ServiceException;

    /**
     * set block is true for bank account
     *
     * @param organisationID where set
     * @return true if success or false is not
     * @throws ServiceException when get ad exception during execution
     */
    boolean blockBankAccounts(int organisationID) throws ServiceException;

    /**
     * set block is false for bank account
     *
     * @param organisationID where set
     * @return true if success or false is not
     * @throws ServiceException when get ad exception during execution
     */
    boolean unblockBankAccounts(int organisationID) throws ServiceException;

    /**
     * @param organisationID where need to check
     * @return block condition
     * @throws ServiceException when get ad exception during execution
     */
    boolean isBlock(int organisationID) throws ServiceException;

    /**
     * @param organisationID to check
     * @return true if exist or false if not
     * @throws ServiceException when get ad exception during execution
     */
    boolean isExist(int organisationID) throws ServiceException;

    /**
     * @param organisationID where need to check
     * @param info           to set
     * @return true if success
     * @throws ServiceException when get ad exception during execution
     */
    boolean setInfo(int organisationID, String info) throws ServiceException;

    /**
     * get info
     *
     * @param organisationID where need to find
     * @return info
     * @throws ServiceException when get ad exception during execution
     */
    String getInfo(int organisationID) throws ServiceException;

    /**
     * return balance from data base
     *
     * @param organisationID where need to find
     * @return balance
     * @throws ServiceException when get ad exception during execution
     */
    double getBalance(int organisationID) throws ServiceException;

    /**
     * return min allowed balance from data base
     *
     * @param organisationID where need to find
     * @return min allowed balance
     * @throws ServiceException when get ad exception during execution
     */
    double getMinAllowedBalance(int organisationID) throws ServiceException;

    /**
     * @param organisationID    where need to set
     * @param minAllowedBalance to set
     * @return true if success
     * @throws ServiceException when get ad exception during execution
     */
    boolean setMinAllowedBalance(int organisationID, double minAllowedBalance) throws ServiceException;

    /**
     * return bank account with organisation id param
     *
     * @param organisationID to find
     * @return {@link BankAccount} or null if do not exist
     * @throws ServiceException when get ad exception during execution
     */
    BankAccount getBankAccount(int organisationID) throws ServiceException;
}
