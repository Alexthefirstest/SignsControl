package by.epam.bank.service;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.exceptions.ServiceException;

public interface IFinanceOperationsManagerService {

    Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws ServiceException;

    Transaction addMoney(int bankID,int organisationID, double money) throws ServiceException;
}
