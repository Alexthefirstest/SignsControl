package by.epam.bank.service;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.exceptions.ServiceException;

public interface ITransactionsDeliverService {


    Transaction[] getTransactions() throws ServiceException;

    Transaction[] findTransaction(int id) throws ServiceException;

    Transaction[] findTransactionsByFrom(int idFrom) throws ServiceException;

    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws ServiceException;

    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException;
}
