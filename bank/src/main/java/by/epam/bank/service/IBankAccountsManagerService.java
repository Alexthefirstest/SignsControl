package by.epam.bank.service;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.service.exceptions.ServiceException;

public interface IBankAccountsManagerService {

    BankAccount createBankAccount(int organisationID) throws  ServiceException;

    boolean blockBankAccounts(int organisationID) throws  ServiceException;

    boolean unblockBankAccounts(int organisationID) throws ServiceException;

    boolean isBlock(int organisationID) throws ServiceException;

    boolean isExist(int organisationID) throws ServiceException;

    boolean setInfo(int organisationID, String info) throws ServiceException;

    String getInfo(int organisationID) throws ServiceException;

    double getBalance(int organisationID) throws ServiceException;

    double getMinAllowedBalance(int organisationID) throws ServiceException;

    boolean setMinAllowedBalance(int organisationID, double minAllowedBalance) throws ServiceException;

    BankAccount getBankAccount(int organisationID) throws ServiceException;
}
