package by.epam.bank.service.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsManager;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IBankAccountsManagerService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;

/**
 * service for working with {@link BankAccount} and {@link by.epam.bank.dao.IBankAccountsManager},
 * get, add, set parameters to sql table
 */
public class BankAccountsManagerService implements IBankAccountsManagerService {

    /**
     * {@link IBankAccountsManager} instance
     */
    private static IBankAccountsManager bam = DaoFactory.getINSTANCE().getBankAccountsManager();

    /**
     * @param organisationID to create account
     * @return {@link BankAccount} if success or null if wrong organisation id or account already exist
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public BankAccount createBankAccount(int organisationID) throws ServiceException {

        try {
            return bam.createBankAccount(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

    }

    /**
     * set block is true for bank account
     *
     * @param organisationID where set
     * @return true if success or false is not
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public boolean blockBankAccounts(int organisationID) throws ServiceException {
        try {
            return bam.blockBankAccounts(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set block is false for bank account
     *
     * @param organisationID where set
     * @return true if success or false is not
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public boolean unblockBankAccounts(int organisationID) throws ServiceException {
        try {
            return bam.unblockBankAccounts(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param organisationID where need to check
     * @return block condition
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public boolean isBlock(int organisationID) throws ServiceException {
        try {
            return bam.isBlock(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param organisationID to check
     * @return true if exist or false if not
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public boolean isExist(int organisationID) throws ServiceException {
        try {
            return bam.isExist(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param organisationID where need to check
     * @param info           to set
     * @return true if success
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public boolean setInfo(int organisationID, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {
            return bam.setInfo(organisationID, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get info
     *
     * @param organisationID where need to find
     * @return info
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public String getInfo(int organisationID) throws ServiceException {
        try {
            return bam.getInfo(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * return balance from data base
     *
     * @param organisationID where need to find
     * @return balance
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public double getBalance(int organisationID) throws ServiceException {
        try {
            return bam.getBalance(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * return min allowed balance from data base
     *
     * @param organisationID where need to find
     * @return min allowed balance
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public double getMinAllowedBalance(int organisationID) throws ServiceException {
        try {
            return bam.getMinAllowedBalance(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param organisationID    where need to set
     * @param minAllowedBalance to set
     * @return true if success
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public boolean setMinAllowedBalance(int organisationID, double minAllowedBalance) throws ServiceException {
        try {
            return bam.setMinAllowedBalance(organisationID, minAllowedBalance);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * return bank account with organisation id param
     *
     * @param organisationID to find
     * @return {@link BankAccount} or null if do not exist
     * @throws ServiceValidationException when {@link IBankAccountsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IBankAccountsManager} throw {@link DAOException}
     */
    @Override
    public BankAccount getBankAccount(int organisationID) throws ServiceException {
        try {
            return bam.getBankAccount(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
