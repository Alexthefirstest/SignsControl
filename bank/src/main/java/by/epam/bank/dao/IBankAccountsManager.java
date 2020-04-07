package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.exceptions.DAOException;

public interface IBankAccountsManager {

    boolean createBankAccount(int organisationID) throws DAOException;

    boolean blockBankAccounts(int organisationID) throws DAOException;

    boolean unblockBankAccounts(int organisationID) throws DAOException;

    boolean isBlock(int organisationID) throws DAOException;

    boolean isExist(int organisationID) throws DAOException;

    boolean setInfo(int organisationID, String info) throws DAOException;

    String getInfo(int organisationID) throws DAOException;

    double getBalance(int organisationID) throws DAOException;

    double getMinAllowedBalance(int organisationID) throws DAOException;

    boolean setMinAllowedBalance(int organisationID, double minAllowedBalance) throws DAOException;

    BankAccount getBankAccount(int organisationID) throws DAOException;
}
