package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.impl.bankAccountsDeliverRequest.Request;

public interface IBankAccountsDeliver {

    BankAccount[] executeRequest(Request request) throws DAOException;


}
