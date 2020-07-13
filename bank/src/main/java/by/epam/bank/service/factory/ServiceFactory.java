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

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    private final IBankAccountsDeliverService bankAccountsDeliver = new BankAccountsDeliverService();

    private final IBankAccountsManagerService bankAccountsManager = new BankAccountsManagerService();
    private final IFinanceOperationsManagerService financeOperationsManager = new FinanceOperationsManagerService();
    private final ITransactionsDeliverService transactionsDeliver = new TransactionsDeliverService();
    private final IOrganisationsDeliverService organisationsDeliver = new OrganisationsDeliverService();


    public IBankAccountsDeliverService getBankAccountsDeliver() {
        return bankAccountsDeliver;
    }

    public IBankAccountsManagerService getBankAccountsManager() {
        return bankAccountsManager;
    }

    public IFinanceOperationsManagerService getFinanceOperationsManager() {
        return financeOperationsManager;
    }

    public ITransactionsDeliverService getTransactionsDeliver() {
        return transactionsDeliver;
    }

    public IRequestBuilderService getRequestBuilder() {
        return new RequestBuilderService();
    }

    public IOrganisationsDeliverService getOrganisationsDeliver() {
        return organisationsDeliver;
    }


}
