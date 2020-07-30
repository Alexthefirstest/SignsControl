package by.epam.bank.service.factory;

import by.epam.bank.service.IBankAccountsDeliverService;
import by.epam.bank.service.IBankAccountsManagerService;
import by.epam.bank.service.IFinanceOperationsManagerService;
import by.epam.bank.service.IOrganisationsDeliverService;
import by.epam.bank.service.IRequestBuilderService;
import by.epam.bank.service.ITransactionsDeliverService;
import by.epam.bank.service.impl.BankAccountsDeliverService;
import by.epam.bank.service.impl.BankAccountsManagerService;
import by.epam.bank.service.impl.FinanceOperationsManagerService;
import by.epam.bank.service.impl.OrganisationsDeliverService;
import by.epam.bank.service.impl.RequestBuilderService;
import by.epam.bank.service.impl.TransactionsDeliverService;

/**
 * Service layer factory
 */
public class ServiceFactory {

    /**
     * this class instance
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * {@link IBankAccountsDeliverService} instance
     */
    private final IBankAccountsDeliverService bankAccountsDeliver = new BankAccountsDeliverService();

    /**
     * {@link IBankAccountsManagerService instance
     */
    private final IBankAccountsManagerService bankAccountsManager = new BankAccountsManagerService();


    /**
     * {@link IFinanceOperationsManagerService} instance
     */
    private final IFinanceOperationsManagerService financeOperationsManager = new FinanceOperationsManagerService();


    /**
     * {@link ITransactionsDeliverService} instance
     */
    private final ITransactionsDeliverService transactionsDeliver = new TransactionsDeliverService();


    /**
     * {@link IOrganisationsDeliverService} instance
     */
    private final IOrganisationsDeliverService organisationsDeliver = new OrganisationsDeliverService();

    /**
     * private constructor
     */
    private ServiceFactory() {
    }

    /**
     * @return {@link ServiceFactory#INSTANCE}
     */
    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link ServiceFactory#bankAccountsDeliver}
     */
    public IBankAccountsDeliverService getBankAccountsDeliver() {
        return bankAccountsDeliver;
    }

    /**
     * @return {@link ServiceFactory#bankAccountsManager}
     */
    public IBankAccountsManagerService getBankAccountsManager() {
        return bankAccountsManager;
    }

    /**
     * @return {@link ServiceFactory#financeOperationsManager}
     */
    public IFinanceOperationsManagerService getFinanceOperationsManager() {
        return financeOperationsManager;
    }

    /**
     * @return {@link ServiceFactory#transactionsDeliver}
     */
    public ITransactionsDeliverService getTransactionsDeliver() {
        return transactionsDeliver;
    }

    /**
     * @return new {@link IRequestBuilderService} instance
     */
    public IRequestBuilderService getRequestBuilder() {
        return new RequestBuilderService();
    }

    /**
     * @return {@link ServiceFactory#organisationsDeliver}
     */
    public IOrganisationsDeliverService getOrganisationsDeliver() {
        return organisationsDeliver;
    }


}
