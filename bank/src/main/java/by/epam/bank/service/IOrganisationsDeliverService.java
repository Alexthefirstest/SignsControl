package by.epam.bank.service;

import by.epam.bank.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;

public interface IOrganisationsDeliverService {

    Organisation[] showOrganisationsWithoutBankAccounts() throws ServiceException;

}
