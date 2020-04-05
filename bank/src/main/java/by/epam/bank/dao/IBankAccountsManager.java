package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.exceptions.DAOException;

public interface IBankAccountsManager {

    boolean createBankAccount(int organizationID) throws DAOException;

    boolean blockBankAccounts(int organizationID) throws DAOException;

    boolean unblockBankAccounts(int organizationID) throws DAOException;

    boolean isBlock(int organizationID) throws DAOException;

    boolean isExist(int organizationID) throws DAOException;

    boolean setInfo(int organizationID, String info) throws DAOException;

    String getInfo(int organizationID) throws DAOException;

    long getBalance(int organizationID) throws DAOException;

    BankAccount getBankAccount(int organizationID) throws DAOException;
}
