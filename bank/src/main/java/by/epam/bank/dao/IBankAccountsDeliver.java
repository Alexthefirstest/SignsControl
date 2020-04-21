package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.exceptions.DAOException;

public interface IBankAccountsDeliver {

    BankAccount[] executeRequest(IRequest request) throws DAOException;


}
