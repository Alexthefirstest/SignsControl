package by.epam.bank.dao;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.exceptions.DAOException;

import java.sql.Date;

public interface ITransactionsDeliver {


    Transaction[] getTransactions() throws DAOException;

    Transaction[] findTransaction(int id) throws DAOException;

    Transaction[] findTransactionsByFrom(int idFrom) throws DAOException;

    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws DAOException;

    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws DAOException;
}
