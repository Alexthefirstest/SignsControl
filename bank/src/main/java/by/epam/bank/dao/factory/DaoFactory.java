package by.epam.bank.dao.factory;

import by.epam.bank.dao.IBankAccountsDeliver;
import by.epam.bank.dao.IBankAccountsManager;
import by.epam.bank.dao.IFinanceOperationsManager;
import by.epam.bank.dao.IRequestBuilder;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.impl.BankAccountsDeliver;
import by.epam.bank.dao.impl.SQLBankAccountsManager;
import by.epam.bank.dao.impl.SQLFinanceOperationsManager;
import by.epam.bank.dao.impl.TransactionsDeliver;
import by.epam.bank.dao.impl.bankAccountsDeliverRequest.RequestBuilder;

public class DaoFactory {

    private DaoFactory() {

    }

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final IBankAccountsDeliver bankAccountsDeliver = new BankAccountsDeliver();

    private final IBankAccountsManager bankAccountsManager = new SQLBankAccountsManager();
    private final IFinanceOperationsManager financeOperationsManager = new SQLFinanceOperationsManager();
    private final ITransactionsDeliver transactionsDeliver = new TransactionsDeliver();
    private final IRequestBuilder requestBuilder =  new RequestBuilder();

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public IBankAccountsDeliver getBankAccountsDeliver() {
        return bankAccountsDeliver;
    }

    public IBankAccountsManager getBankAccountsManager() {
        return bankAccountsManager;
    }

    public IFinanceOperationsManager getFinanceOperationsManager() {
        return financeOperationsManager;
    }

    public ITransactionsDeliver getTransactionsDeliver() {
        return transactionsDeliver;
    }

    public IRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
