package by.epam.bank.dao;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.exceptions.DAOException;

public interface ITransactionsDeliver {


    /**
     * get all transactions order by id
     *
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] getTransactions() throws DAOException;

    /**
     * get transactions order by id from position and quantity count
     *
     * @param startPosition start position in sql to output
     * @param count         count of transactions
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] getTransactions(int startPosition, int count) throws DAOException;

    /**
     * find transaction by id
     *
     * @param id to find
     * @return {@link Transaction}
     * @throws DAOException when get an exception during execution
     */
    Transaction findTransaction(int id) throws DAOException;

    /**
     * find transaction by organisation sender id order by id
     *
     * @param idFrom sender id
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByFrom(int idFrom) throws DAOException;

    /**
     * get transaction by organisation sender id from position param count param order by id
     *
     * @param idFrom        sender id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByFrom(int idFrom, int startPosition, int count) throws DAOException;

    /**
     * find transaction by organisation payee id order by id
     *
     * @param idTo payee id
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByTo(int idTo) throws DAOException;

    /**
     * get transaction by organisation payee id from position param count param order by id
     *
     * @param idTo        payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByTo(int idTo, int startPosition, int count) throws DAOException;

    /**
     * get transaction by organisation sender id and organisation payee order by id
     *
     * @param idFrom sender id
     * @param idTo   payee id
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws DAOException;

    /**
     * get transaction by organisation sender id and organisation payee from position param count param order by id
     *
     * @param idFrom        sender id
     * @param idTo          payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo, int startPosition, int count) throws DAOException;

    /**
     * get transaction by organisation id where it sender or payee order by id
     *
     * @param id sender or payee id
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByFromOrTo(int id) throws DAOException;

    /**
     * get transaction by orby organisation id where it sender or payee from position param count param order by id
     *
     * @param id sender or payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByFromOrTo(int id, int startPosition, int count) throws DAOException;

    /**
     * get transaction by execution date between datefrom and dateto order by
     *
     * @param idFrom   transaction sender
     * @param dateFrom start date to search
     * @param dateTo   finish date to search
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws DAOException;

    /**
     * show transaction with sender id and date of execution between dateFrom dateTo start from start position and get count quantity
     *
     * @param idFrom        sender
     * @param dateFrom      start date
     * @param dateTo        finish date
     * @param startPosition start position to return result
     * @param count         of return transaction
     * @return {@link Transaction} array
     * @throws DAOException when get an exception during execution
     */
    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws DAOException;

    /**
     * get all transactions count
     *
     * @return fields count
     * @throws DAOException when get an exception during execution
     */
    int getFieldsCount() throws DAOException;

    /**
     * get transactions count with sender id
     *
     * @param idFrom sender id
     * @return fields count
     * @throws DAOException when get an exception during execution
     */
    int getFieldsCountByFrom(int idFrom) throws DAOException;

    /**
     * get transactions count with payee id
     *
     * @param idTo payee id
     * @return fields count
     * @throws DAOException when get an exception during execution
     */
    int getFieldsCountByTo(int idTo) throws DAOException;

    /**
     *  get transactions count with sender id and payee id
     *
     * @param idFrom sender id
     * @param idTo   payed id
     * @return fields count
     * @throws DAOException when get an exception during execution
     */
    int getFieldsCountByFromAndTo(int idFrom, int idTo) throws DAOException;

    /**
     *  get transactions count by organisation id where it sender or payee
     *
     * @param id sander or payee id
     * @return fields count
     * @throws DAOException when get an exception during execution
     */
    int getFieldsCountByFromOrTo(int id) throws DAOException;

    /**
     * get transactions count with sender and date of execution between dateFrom and dateTo
     *
     * @param idFrom   sender id
     * @param dateFrom date to start search
     * @param dateTo   finish date for search
     * @return fields count
     * @throws DAOException when get an exception during execution
     */
    int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws DAOException;
}
