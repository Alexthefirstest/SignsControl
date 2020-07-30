package by.epam.bank.service;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IRequest;
import by.epam.bank.service.exceptions.ServiceException;

/**
 * execute {@link IRequest} that build by {@link by.epam.bank.service.IRequestBuilderService}  to supply bank accounts
 */
public interface IBankAccountsDeliverService {


    /**
     * execute {@link IRequest} to supply bank accounts
     *
     * @param request {@link IRequest}
     * @return {@link BankAccount} array
     * @throws ServiceException when get ad exception during execution
     */
    BankAccount[] executeRequest(IRequest request) throws ServiceException;


}
