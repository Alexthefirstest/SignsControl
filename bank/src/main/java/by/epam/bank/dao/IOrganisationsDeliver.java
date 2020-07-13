package by.epam.bank.dao;

import by.epam.bank.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;

public interface IOrganisationsDeliver{

    Organisation[] showOrganisationsWithoutBankAccounts() throws DAOException;

}
