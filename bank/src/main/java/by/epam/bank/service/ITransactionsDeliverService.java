package by.epam.bank.service;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.exceptions.ServiceException;

public interface ITransactionsDeliverService {


    Transaction[] getTransactions() throws ServiceException;

    Transaction[] findTransaction(int id) throws ServiceException;

    Transaction[] findTransactionsByFrom(int idFrom) throws ServiceException;

    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws ServiceException;

    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException;

    Transaction[] getTransactions(int startPosition, int count) throws ServiceException;

    Transaction[] findTransactionsByFrom(int idFrom, int startPosition, int count) throws ServiceException;

    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo, int startPosition, int count) throws ServiceException;

    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws ServiceException;


    int getFieldsCount() throws ServiceException, ServiceException;

    int getFieldsCountByFrom(int idFrom) throws ServiceException;

    int getFieldsCountByFromAndTo(int idFrom, int idTo) throws ServiceException;

    int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException;


    int getPagesQuantity(int countOnPage) throws ServiceException;

    int getPagesQuantityByFrom(int idFrom, int countOnPage) throws ServiceException;

    int getPagesQuantityByFromAndTo(int idFrom, int idTo, int countOnPage) throws ServiceException;

    int getPagesQuantityByDate(int idFrom, String dateFrom, String dateTo, int countOnPage) throws ServiceException;


    Transaction[] getTransactionsPage(int countOnPage, int page) throws ServiceException;

    Transaction[] findTransactionsByFromPage(int idFrom, int countOnPage, int page) throws ServiceException;

    Transaction[] findTransactionsByFromAndToPage(int idFrom, int idTo, int countOnPage, int page) throws ServiceException;

    Transaction[] findTransactionsByDatePage(int idFrom, String dateFrom, String dateTo,
                                             int countOnPage, int page) throws ServiceException;

}
