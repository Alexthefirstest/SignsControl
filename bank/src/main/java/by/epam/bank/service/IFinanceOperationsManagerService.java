package by.epam.bank.service;

import by.epam.bank.service.exceptions.ServiceException;

public interface IFinanceOperationsManagerService {

    int transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws ServiceException;

    boolean addMoney(int organisationID, double money) throws ServiceException;
}
