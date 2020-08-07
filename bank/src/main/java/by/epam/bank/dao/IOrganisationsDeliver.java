package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
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

    /**
     * get organisation with bank account or without in case account don't exist by id
     *
     * @param id organisation id
     * @return {@link Organisation}
     * @throws DAOException when get an exception during execution
     */

    public BankAccount findOrganisationByID(int id) throws DAOException;
}
