package by.epam.bank.service.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.ITransactionsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;

public class TransactionsDeliverService implements ITransactionsDeliverService {

    private static ITransactionsDeliver td = DaoFactory.getINSTANCE().getTransactionsDeliver();

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

    @Override
    public Transaction[] findTransaction(int id) throws ServiceException {
        try {
            return td.findTransaction(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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

    @Override
    public Transaction[] findTransactionsByDate(int idFrom, String dateFrom, String dateTo, int startPosition, int count) throws ServiceException {
        try {
            return td.findTransactionsByDate(idFrom, dateFrom, dateTo, startPosition, count);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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

    @Override
    public int getFieldsCountByDate(int idFrom, String dateFrom, String dateTo) throws ServiceException {
        try {
            return td.getFieldsCountByDate(idFrom, dateFrom, dateTo);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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

    @Override
    public int getPagesQuantityByDate(int idFrom, String dateFrom, String dateTo, int countOnPage) throws ServiceException {
        try {
            return (int) Math.ceil((double) td.getFieldsCountByDate(idFrom, dateFrom, dateTo) / countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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

    @Override
    public Transaction[] findTransactionsByDatePage(int idFrom, String dateFrom, String dateTo, int countOnPage, int page) throws ServiceException {
        try {
            return td.findTransactionsByDate(idFrom, dateFrom, dateTo, (page - 1) * countOnPage, countOnPage);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
