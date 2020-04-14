package by.epam.bank.dao;

import by.epam.bank.dao.exceptions.DAOException;

public interface IFinanceOperationsManager {

    int transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException;

    boolean addMoney(int organisationID, double money) throws DAOException;
}
