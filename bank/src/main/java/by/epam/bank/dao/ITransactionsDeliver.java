package by.epam.bank.dao;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.exceptions.DAOException;

import java.sql.Date;

public interface ITransactionsDeliver {


    public Transaction[] getTransactions() throws DAOException;

    public Transaction[] findTransaction(int id) throws DAOException;

    public Transaction[] findTransactionsByFrom(int idFrom) throws DAOException;

    public Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws DAOException;

    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws DAOException;
}
