package by.epam.bank.dao;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.exceptions.DAOException;

public interface ITransactionsDeliver {


    Transaction[] getTransactions() throws DAOException;

    Transaction[] findTransaction(int id) throws DAOException;

    Transaction[] findTransactionsByFrom(int idFrom) throws DAOException;

    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws DAOException;

    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws DAOException;


    Transaction[] getTransactions(int startPosition, int count) throws DAOException;

    Transaction[] findTransactionsByFrom(int idFrom, int startPosition, int count) throws DAOException;

    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo, int startPosition, int count) throws DAOException;

    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws DAOException;

    int getFieldsCount() throws DAOException;

    int getFieldsCountByFrom(int idFrom) throws DAOException;

    int getFieldsCountByFromAndTo(int idFrom, int idTo) throws DAOException;

    int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws DAOException;
}
