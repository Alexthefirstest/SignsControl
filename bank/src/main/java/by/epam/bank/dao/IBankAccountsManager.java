package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.exceptions.DAOException;

public interface IBankAccountsManager {

    /**
     * @param organisationID to create account
     * @return {@link BankAccount} if success or null if wrong organisation id or account already exist
     * @throws DAOException when get an exception during execution
     */
    BankAccount createBankAccount(int organisationID) throws DAOException;

    /**
     * set block is true for bank account
     *
     * @param organisationID where set
     * @return true if success or false is not
     * @throws DAOException when get an exception during execution
     */
    boolean blockBankAccounts(int organisationID) throws DAOException;

    /**
     * set block is false for bank account
     *
     * @param organisationID where set
     * @return true if success or false is not
     * @throws DAOException when get an exception during execution
     */
    boolean unblockBankAccounts(int organisationID) throws DAOException;

    /**
     * @param organisationID where need to check
     * @return block condition
     * @throws DAOException when get an exception during execution
     */
    boolean isBlock(int organisationID) throws DAOException;

    /**
     * @param organisationID to check
     * @return true if exist or false if not
     * @throws by.epam.bank.dao.exceptions.DAOValidationException if organisation with this id to not exist
     * @throws DAOException when get an exception during execution
     */
    boolean isExist(int organisationID) throws DAOException;

    /**
     * @param organisationID where need to check
     * @param info           to set
     * @return true if success
     * @throws DAOException when get an exception during execution
     * @throws DAOException when get an exception during execution
     */
    boolean setInfo(int organisationID, String info) throws DAOException;

    /**
     * get info
     *
     * @param organisationID where need to find
     * @return info
     * @throws DAOException when get an exception during execution
     */
    String getInfo(int organisationID) throws DAOException;

    /**
     * return balance from data base
     *
     * @param organisationID where need to find
     * @return balance
     * @throws DAOException when get an exception during execution
     */
    double getBalance(int organisationID) throws DAOException;

    /**
     * return min allowed balance from data base
     *
     * @param organisationID where need to find
     * @return min allowed balance
     * @throws DAOException when get an exception during execution
     */
    double getMinAllowedBalance(int organisationID) throws DAOException;

    /**
     * @param organisationID    where need to set
     * @param minAllowedBalance to set
     * @return true if success
     * @throws DAOException when get an exception during execution
     */
    boolean setMinAllowedBalance(int organisationID, double minAllowedBalance) throws DAOException;


    /**
     * return bank account with organisation id param
     *
     * @param organisationID to find
     * @return {@link BankAccount} or null if do not exist
     * @throws DAOException when get an exception during execution
     */
    BankAccount getBankAccount(int organisationID) throws DAOException;
}
