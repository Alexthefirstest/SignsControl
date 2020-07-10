package by.epam.bank.service.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsManager;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IBankAccountsManagerService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;


public class BankAccountsManagerService implements IBankAccountsManagerService {

    private static IBankAccountsManager bam = DaoFactory.getINSTANCE().getBankAccountsManager();

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
