package by.epam.bank.service.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.IFinanceOperationsManager;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IFinanceOperationsManagerService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;

public class FinanceOperationsManagerService implements IFinanceOperationsManagerService {

    private static IFinanceOperationsManager fom = DaoFactory.getINSTANCE().getFinanceOperationsManager();

    @Override
    public Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws ServiceException {
        try {
            return fom.transferMoney(organisationIDFrom, organisationIDTo, money);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Transaction addMoney(int bankID, int organisationID, double money) throws ServiceException {
        try {
            return fom.addMoney(bankID, organisationID, money);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
