package by.epam.bank.service;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;

/**
 * service to supply {@link Organisation}
 */
public interface IOrganisationsDeliverService {


    /**
     * get organisations with no bank accounts
     *
     * @return {@link Organisation}
     * @throws ServiceException when get ad exception during execution
     */
    Organisation[] showOrganisationsWithoutBankAccounts() throws ServiceException;


    /**
     * get organisation with bank account or without in case account don't exist by id
     *
     * @param id organisation id
     * @return {@link Organisation}
     * @throws ServiceException when get ad exception during execution
     */

    public BankAccount findOrganisationByID(int id) throws ServiceException;
}
