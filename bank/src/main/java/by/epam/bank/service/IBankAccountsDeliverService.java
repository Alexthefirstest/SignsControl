package by.epam.bank.service;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IRequest;
import by.epam.bank.service.exceptions.ServiceException;

public interface IBankAccountsDeliverService {

    BankAccount[] executeRequest(IRequest request) throws ServiceException;


}
