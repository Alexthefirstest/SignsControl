package by.epam.bank.dao;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.exceptions.DAOException;

/**
 * class for supply bank accounts
 * <p>
 * have a method to execute {@link IRequest}
 */
public interface IBankAccountsDeliver {


    /**
     * execute {@link IRequest}
     *
     * @param request {@link IRequest}
     * @return {@link BankAccount} array
     * @throws DAOException when get an exception during execution
     */
    BankAccount[] executeRequest(IRequest request) throws DAOException;


}
