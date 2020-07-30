package by.epam.bank.dao;

import by.epam.bank.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;

/**
 * supply organisations from data base
 */
public interface IOrganisationsDeliver {


    /**
     * get organisations with no bank accounts
     *
     * @return {@link Organisation}
     * @throws DAOException when get an exception during execution
     */
    Organisation[] showOrganisationsWithoutBankAccounts() throws DAOException;

}
