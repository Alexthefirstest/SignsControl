package by.epam.bank.service.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IRequest;

import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IBankAccountsDeliverService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;

public class BankAccountsDeliverService implements IBankAccountsDeliverService {
    @Override
    public BankAccount[] executeRequest(IRequest request) throws ServiceException {


        try {
            return DaoFactory.getINSTANCE().getBankAccountsDeliver().executeRequest(request);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

    }
}
