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
}
