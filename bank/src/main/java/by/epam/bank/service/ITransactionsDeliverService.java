package by.epam.bank.service;

import by.epam.bank.bean.Transaction;
import by.epam.bank.service.exceptions.ServiceException;

/**
 * service to supply {@link Transaction} with data validate
 */
public interface ITransactionsDeliverService {


    /**
     * get all transactions order by id
     *
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] getTransactions() throws ServiceException;

    /**
     * find transaction by id
     *
     * @param id to find
     * @return {@link Transaction} or null if can't find
     * @throws ServiceException when get ad exception during execution
     */
    Transaction findTransaction(int id) throws ServiceException;

    /**
     * find transaction by organisation sender id order by id
     *
     * @param idFrom sender id
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFrom(int idFrom) throws ServiceException;

    /**
     * find transaction by organisation payee id order by id
     *
     * @param idTo payee id
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByTo(int idTo) throws ServiceException;

    /**
     * get transaction by organisation sender id and organisation payee order by id
     *
     * @param idFrom sender id
     * @param idTo   payee id
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws ServiceException;

    /**
     * get transaction by organisation id where it sender or payee order by id
     *
     * @param id organisation id
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromOrTo(int id) throws ServiceException;

    /**
     * get transaction by execution date between datefrom and dateto order by
     *
     * @param idFrom   transaction sender
     * @param dateFrom start date to search
     * @param dateTo   finish date to search
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException;

    /**
     * get transactions order by id from position and quantity count
     *
     * @param startPosition start position in sql to output
     * @param count         count of transactions
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] getTransactions(int startPosition, int count) throws ServiceException;

    /**
     * get transaction by organisation sender id from position param count param order by id
     *
     * @param idFrom        sender id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFrom(int idFrom, int startPosition, int count) throws ServiceException;

    /**
     * get transaction by organisation payee id from position param count param order by id
     *
     * @param idTo          payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByTo(int idTo, int startPosition, int count) throws ServiceException;

    /**
     * get transaction by organisation sender id and organisation payee from position param count param order by id
     *
     * @param idFrom        sender id
     * @param idTo          payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo, int startPosition, int count) throws ServiceException;

    /**
     * get transaction by organisation id where it sender or payee order by id
     *
     * @param id            organisation id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromOrTo(int id, int startPosition, int count) throws ServiceException;

    /**
     * show transaction with sender id and date of execution between dateFrom dateTo start from start position and get count quantity
     *
     * @param idFrom        sender
     * @param dateFrom      start date
     * @param dateTo        finish date
     * @param startPosition start position to return result
     * @param count         of return transaction
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws ServiceException;


    /**
     * get all transactions count
     *
     * @return fields count
     * @throws ServiceException when get ad exception during execution
     */
    int getFieldsCount() throws ServiceException, ServiceException;

    /**
     * get transactions count with sender id
     *
     * @param idFrom sender id
     * @return fields count
     * @throws ServiceException when get ad exception during execution
     */
    int getFieldsCountByFrom(int idFrom) throws ServiceException;

    /**
     * get transactions count with payee id
     *
     * @param idTo payee id
     * @return fields count
     * @throws ServiceException when get ad exception during execution
     */
    int getFieldsCountByTo(int idTo) throws ServiceException;

    /**
     * get transactions count with sender id and payee id
     *
     * @param idFrom sender id
     * @param idTo   payed id
     * @return fields count
     * @throws ServiceException when get ad exception during execution
     */
    int getFieldsCountByFromAndTo(int idFrom, int idTo) throws ServiceException;

    /**
     * get transaction by organisation id where it sender or payee order by id
     *
     * @param id organisation id
     * @return fields count
     * @throws ServiceException when get ad exception during execution
     */
    int getFieldsCountByFromOrTo(int id) throws ServiceException;

    /**
     * get transactions count with sender and date of execution between dateFrom and dateTo
     *
     * @param idFrom   sender id
     * @param dateFrom date to start search
     * @param dateTo   finish date for search
     * @return fields count
     * @throws ServiceException when get ad exception during execution
     */
    int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException;

    /**
     * get all transactions pages count count
     *
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceException when get ad exception during execution
     */
    int getPagesQuantity(int countOnPage) throws ServiceException;

    /**
     * get transactions pages count with sender id
     *
     * @param idFrom      sender id
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceException when get ad exception during execution
     */
    int getPagesQuantityByFrom(int idFrom, int countOnPage) throws ServiceException;

    /**
     * get transactions pages count with payee id
     *
     * @param idTo        payee id
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceException when get ad exception during execution
     */
    int getPagesQuantityByTo(int idTo, int countOnPage) throws ServiceException;

    /**
     * get transactions pages count with sender id and payee id
     *
     * @param idFrom      sender id
     * @param idTo        payed id
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceException when get ad exception during execution
     */
    int getPagesQuantityByFromAndTo(int idFrom, int idTo, int countOnPage) throws ServiceException;

    /**
     * get transaction by organisation id where it sender or payee order by id
     *
     * @param id          organisation id
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceException when get ad exception during execution
     */
    int getPagesQuantityByFromOrTo(int id, int countOnPage) throws ServiceException;

    /**
     * get transactions pages count with sender and date of execution between dateFrom and dateTo
     *
     * @param idFrom      sender id
     * @param dateFrom    date to start search
     * @param dateTo      finish date for search
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceException when get ad exception during execution
     */
    int getPagesQuantityByDate(int idFrom, String dateFrom, String dateTo, int countOnPage) throws ServiceException;

    /**
     * get one of page with all transactions order by id
     *
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] getTransactionsPage(int countOnPage, int page) throws ServiceException;

    /**
     * get page of transactions find  by organisation sender id order by id
     *
     * @param idFrom      sender id
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromPage(int idFrom, int countOnPage, int page) throws ServiceException;

    /**
     * get page of transactions find  by organisation payee id order by id
     *
     * @param idTo        payee id
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByToPage(int idTo, int countOnPage, int page) throws ServiceException;

    /**
     * get page of transactions by organisation sender id and organisation payee order by id
     *
     * @param idFrom      sender id
     * @param idTo        payee id
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromAndToPage(int idFrom, int idTo, int countOnPage, int page) throws ServiceException;

    /**
     * get transaction by organisation id where it sender or payee order by id
     *
     * @param id          organisation
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByFromOrToPage(int id, int countOnPage, int page) throws ServiceException;

    /**
     * get page of transactions by execution date between datefrom and dateto order by
     *
     * @param idFrom      transaction sender
     * @param dateFrom    start date to search
     * @param dateTo      finish date to search
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceException when get ad exception during execution
     */
    Transaction[] findTransactionsByDatePage(int idFrom, String dateFrom, String dateTo,
                                             int countOnPage, int page) throws ServiceException;

}
