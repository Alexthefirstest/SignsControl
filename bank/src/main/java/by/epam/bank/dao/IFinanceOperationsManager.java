package by.epam.bank.dao;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.exceptions.DAOException;

public interface IFinanceOperationsManager {

    Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException;

    Transaction addMoney(int bankID, int organisationID, double money) throws DAOException;
}
