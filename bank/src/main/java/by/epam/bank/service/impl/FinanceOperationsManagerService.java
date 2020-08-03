package by.epam.bank.service.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.IFinanceOperationsManager;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.service.IFinanceOperationsManagerService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;

/**
 * service for working with {@link by.epam.bank.dao.IFinanceOperationsManager}
 * get, add, set parameters to sql table
 */
public class FinanceOperationsManagerService implements IFinanceOperationsManagerService {

    /**
     * {@link IFinanceOperationsManager}
     */
    private static IFinanceOperationsManager fom = DaoFactory.getINSTANCE().getFinanceOperationsManager();

    /**
     * transact money, create transaction to the history
     *
     * @param organisationIDFrom sender
     * @param organisationIDTo   payee
     * @param money              summ of money to transact
     * @return {@link Transaction} if success
     * @throws ServiceValidationException when {@link IFinanceOperationsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IFinanceOperationsManager} throw {@link DAOException}
     */
    @Override
    public Transaction transferMoney(int organisationIDFrom, int organisationIDTo, double money) throws ServiceException {

        if (money <= 0) {
            throw new ServiceValidationException("negative amount");
        }

        try {
            return fom.transferMoney(organisationIDFrom, organisationIDTo, money);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add money to account with organisation id param
     *
     * @param bankID         id of bank for transactions history sender - do not withdraw money from it's account
     * @param organisationID to add money
     * @param money          sum of money to add
     * @return {@link Transaction} if success
     * @throws ServiceValidationException when {@link IFinanceOperationsManager} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IFinanceOperationsManager} throw {@link DAOException}
     */
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
