package by.epam.bank.dao.factory;

import by.epam.bank.dao.IBankAccountsDeliver;
import by.epam.bank.dao.IBankAccountsManager;
import by.epam.bank.dao.IFinanceOperationsManager;
import by.epam.bank.dao.IOrganisationsDeliver;
import by.epam.bank.dao.IRequestBuilder;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.impl.BankAccountsDeliver;
import by.epam.bank.dao.impl.OrganisationDeliver;
import by.epam.bank.dao.impl.SQLBankAccountsManager;
import by.epam.bank.dao.impl.SQLFinanceOperationsManager;
import by.epam.bank.dao.impl.TransactionsDeliver;
import by.epam.bank.dao.impl.bankAccountsDeliverRequest.RequestBuilder;

/**
 * dao layer factory
 */
public class DaoFactory {

    /**
     * private constructor
     */
    private DaoFactory() {

    }

    /**
     * this class instance
     */
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * {@link IBankAccountsDeliver} instance
     */
    private final IBankAccountsDeliver bankAccountsDeliver = new BankAccountsDeliver();

    /**
     * {@link IBankAccountsManager} instance
     */
    private final IBankAccountsManager bankAccountsManager = new SQLBankAccountsManager();

    /**
     * {@link IFinanceOperationsManager} instance
     */
    private final IFinanceOperationsManager financeOperationsManager = new SQLFinanceOperationsManager();

    /**
     * {@link ITransactionsDeliver} instance
     */
    private final ITransactionsDeliver transactionsDeliver = new TransactionsDeliver();

    /**
     * {@link IOrganisationsDeliver} instance
     */
    private final IOrganisationsDeliver organisationsDeliver = new OrganisationDeliver();


    /**
     * @return {@link DaoFactory#INSTANCE}
     */
    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link DaoFactory#bankAccountsDeliver}
     */
    public IBankAccountsDeliver getBankAccountsDeliver() {
        return bankAccountsDeliver;
    }

    /**
     * @return {@link DaoFactory#bankAccountsManager}
     */
    public IBankAccountsManager getBankAccountsManager() {
        return bankAccountsManager;
    }

    /**
     * @return {@link DaoFactory#financeOperationsManager}
     */
    public IFinanceOperationsManager getFinanceOperationsManager() {
        return financeOperationsManager;
    }

    /**
     * @return {@link DaoFactory#transactionsDeliver}
     */
    public ITransactionsDeliver getTransactionsDeliver() {
        return transactionsDeliver;
    }

    /**
     * @return new {@link IRequestBuilder} instance
     */
    public IRequestBuilder getRequestBuilder() {
        return new RequestBuilder();
    }

    /**
     * @return {@link DaoFactory#organisationsDeliver}
     */
    public IOrganisationsDeliver getOrganisationsDeliver() {
        return organisationsDeliver;
    }

//    public IRequestBuilder getRequestBuilder() {
//        return requestBuilder;
//    }
//
// private final IRequestBuilder requestBuilder =  new RequestBuilder();

}
