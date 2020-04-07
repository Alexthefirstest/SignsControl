package by.epam.bank.dao;

import by.epam.bank.dao.exceptions.DAOException;

public interface IFinanceOperationsManager {

    boolean transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws DAOException;

    boolean addMoney(int organisationID, double money);
}
