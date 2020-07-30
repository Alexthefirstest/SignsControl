package by.epam.bank.service;

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

}
