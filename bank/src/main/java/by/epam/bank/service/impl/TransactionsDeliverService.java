package by.epam.bank.service.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.ITransactionsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;

/**
 * service to supply {@link Transaction} with data validate
 */
public class TransactionsDeliverService implements ITransactionsDeliverService {

    /**
     * {@link ITransactionsDeliver instance}
     */
    private static ITransactionsDeliver td = DaoFactory.getINSTANCE().getTransactionsDeliver();


    /**
     * get all transactions order by id
     *
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] getTransactions() throws ServiceException {

        try {
            return td.getTransactions();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * find transaction by id
     *
     * @param id to find
     * @return {@link Transaction} or null if can't find
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction findTransaction(int id) throws ServiceException {
        try {
            return td.findTransaction(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * find transaction by organisation sender id order by id
     *
     * @param idFrom sender id
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByFrom(int idFrom) throws ServiceException {
        try {
            return td.findTransactionsByFrom(idFrom);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transaction by organisation sender id and organisation payee order by id
     *
     * @param idFrom sender id
     * @param idTo   payee id
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo) throws ServiceException {
        try {
            return td.findTransactionsByFromAndTo(idFrom, idTo);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transaction by execution date between datefrom and dateto order by
     *
     * @param idFrom   transaction sender
     * @param dateFrom start date to search
     * @param dateTo   finish date to search
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException {

        InputValidation.nullAndDateCheck(dateFrom);
        InputValidation.nullAndDateCheck(dateTo);

        try {
            return td.findTransactionsByDate(idFrom, dateFrom, dateTo);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transactions order by id from position and quantity count
     *
     * @param startPosition start position in sql to output
     * @param count         count of transactions
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] getTransactions(int startPosition, int count) throws ServiceException {
        try {
            return td.getTransactions(startPosition, count);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transaction by organisation sender id from position param count param order by id
     *
     * @param idFrom        sender id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByFrom(int idFrom, int startPosition, int count) throws ServiceException {
        try {
            return td.findTransactionsByFrom(idFrom, startPosition, count);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transaction by organisation sender id and organisation payee from position param count param order by id
     *
     * @param idFrom        sender id
     * @param idTo          payee id
     * @param startPosition start position to show from table
     * @param count         count to show
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByFromAndTo(int idFrom, int idTo, int startPosition, int count) throws ServiceException {
        try {
            return td.findTransactionsByFromAndTo(idFrom, idTo, startPosition, count);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * show transaction with sender id and date of execution between dateFrom dateTo start from start position and get count quantity
     *
     * @param idFrom        sender
     * @param dateFrom      start date
     * @param dateTo        finish date
     * @param startPosition start position to return result
     * @param count         of return transaction
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws ServiceException {

        InputValidation.nullAndDateCheck(dateTo);
        InputValidation.nullAndDateCheck(dateFrom);

        try {
            return td.findTransactionsByDate(idFrom, dateFrom, dateTo, startPosition, count);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get all transactions count
     *
     * @return fields count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getFieldsCount() throws ServiceException {


        try {
            return td.getFieldsCount();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transactions count with sender id
     *
     * @param idFrom sender id
     * @return fields count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getFieldsCountByFrom(int idFrom) throws ServiceException {
        try {
            return td.getFieldsCountByFrom(idFrom);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * * get transactions count with sender id and payee id
     *
     * @param idFrom sender id
     * @param idTo   payed id
     * @return fields count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getFieldsCountByFromAndTo(int idFrom, int idTo) throws ServiceException {
        try {
            return td.getFieldsCountByFromAndTo(idFrom, idTo);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transactions count with sender and date of execution between dateFrom and dateTo
     *
     * @param idFrom   sender id
     * @param dateFrom date to start search
     * @param dateTo   finish date for search
     * @return fields count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException {

        InputValidation.nullAndDateCheck(dateFrom);
        InputValidation.nullAndDateCheck(dateTo);

        try {
            return td.getFieldsCountByDate(idFrom, dateFrom, dateTo);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get all transactions pages count count
     *
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getPagesQuantity(int countOnPage) throws ServiceException {


        try {
            return (int) Math.ceil((double) td.getFieldsCount() / countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

    }

    /**
     * get transactions pages count with sender id
     *
     * @param idFrom      sender id
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getPagesQuantityByFrom(int idFrom, int countOnPage) throws ServiceException {
        try {
            return (int) Math.ceil((double) td.getFieldsCountByFrom(idFrom) / countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transactions pages count with sender id and payee id
     *
     * @param idFrom      sender id
     * @param idTo        payed id
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getPagesQuantityByFromAndTo(int idFrom, int idTo, int countOnPage) throws ServiceException {
        try {
            return (int) Math.ceil((double) td.getFieldsCountByFromAndTo(idFrom, idTo) / countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get transactions pages count with sender and date of execution between dateFrom and dateTo
     *
     * @param idFrom      sender id
     * @param dateFrom    date to start search
     * @param dateTo      finish date for search
     * @param countOnPage count of field on page
     * @return pages count
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public int getPagesQuantityByDate(int idFrom, String dateFrom, String dateTo, int countOnPage) throws ServiceException {

        InputValidation.nullAndDateCheck(dateFrom);
        InputValidation.nullAndDateCheck(dateTo);

        try {
            return (int) Math.ceil((double) td.getFieldsCountByDate(idFrom, dateFrom, dateTo) / countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * get one of page with all transactions order by id
     *
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] getTransactionsPage(int countOnPage, int page) throws ServiceException {
        try {
            return td.getTransactions((page - 1) * countOnPage, countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

    }


    /**
     * get page of transactions find  by organisation sender id order by id
     *
     * @param idFrom      sender id
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByFromPage(int idFrom, int countOnPage, int page) throws ServiceException {
        try {
            return td.findTransactionsByFrom(idFrom, (page - 1) * countOnPage, countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get page of transactions by organisation sender id and organisation payee order by id
     *
     * @param idFrom      sender id
     * @param idTo        payee id
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByFromAndToPage(int idFrom, int idTo, int countOnPage, int page) throws ServiceException {
        try {
            return td.findTransactionsByFromAndTo(idFrom, idTo, (page - 1) * countOnPage, countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get page of transactions by execution date between datefrom and dateto order by
     *
     * @param idFrom      transaction sender
     * @param dateFrom    start date to search
     * @param dateTo      finish date to search
     * @param countOnPage count of transaction on one page
     * @param page        number of page to return (start from 1)
     * @return {@link Transaction} array
     * @throws ServiceValidationException when {@link ITransactionsDeliver} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ITransactionsDeliver} throw {@link DAOException}
     */
    @Override
    public Transaction[] findTransactionsByDatePage(int idFrom, String dateFrom, String dateTo, int countOnPage, int page) throws ServiceException {

        InputValidation.nullAndDateCheck(dateFrom);
        InputValidation.nullAndDateCheck(dateTo);

        try {
            return td.findTransactionsByDate(idFrom, dateFrom, dateTo, (page - 1) * countOnPage, countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
