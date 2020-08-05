package by.epam.bank.service.factory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceFactoryTest {

    @Test
    public void getInstances() {

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();

        Assert.assertNotNull(serviceFactory.getRequestBuilder());
        Assert.assertNotNull(serviceFactory.getBankAccountsDeliver());
        Assert.assertNotNull(serviceFactory.getBankAccountsManager());
        Assert.assertNotNull(serviceFactory.getFinanceOperationsManager());
        Assert.assertNotNull(serviceFactory.getOrganisationsDeliver());
        Assert.assertNotNull(serviceFactory.getTransactionsDeliver());

    }
}