package by.epam.bank.service.impl;

import by.epam.bank.dao.factory.DaoFactory;
import by.epam.bank.dao.impl.bankAccountsDeliverRequest.Request;
import by.epam.bank.service.IBankAccountsDeliverService;
import by.epam.bank.service.IRequestBuilderService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.factory.ServiceFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountsDeliverServiceTest {

    private IBankAccountsDeliverService bankAccountsDeliverService;

    @Before
    public void setUp() {
        bankAccountsDeliverService = new BankAccountsDeliverService();
    }

    @After
    public void tearDown() {
        bankAccountsDeliverService = null;
    }

    @Test(timeout = 20_000)
    public void executeRequestSQLValidationTest() throws ServiceException {

        System.out.println("executeRequestTest");

        bankAccountsDeliverService.executeRequest(ServiceFactory.getINSTANCE().getRequestBuilder().build());

        System.out.println("executeRequestTest2");

        bankAccountsDeliverService.executeRequest(ServiceFactory.getINSTANCE()
                .getRequestBuilder().getBasicRequest());

        System.out.println("executeRequestTest3");

        bankAccountsDeliverService.executeRequest(ServiceFactory.getINSTANCE()
                .getRequestBuilder().showOnlyBlocked().showOnlyWithNegativeBalance().showOnlyUnblocked().showOnlyWithPositiveBalance()
                .whereBalanceLessThen(5).whereBalanceMoreThen(3).findById(3)
                .withSortByBalance().withSortByOrganisationName().setDESC().withSortByBlocked().withSortByMinAllowedBalance().setDESC()
                .build());

        System.out.println("executeRequestTest  Finish");
    }

    @Test
    public void sameDaoAndServiceBuilderResults() throws ServiceException {


        Request requestUseBuild = (Request) ServiceFactory.getINSTANCE().getRequestBuilder().build();


        Request baseRequest = (Request) (ServiceFactory.getINSTANCE()
                .getRequestBuilder().getBasicRequest());


        Request allParametersRequest = (Request) (ServiceFactory.getINSTANCE()
                .getRequestBuilder().showOnlyBlocked().showOnlyWithNegativeBalance().showOnlyUnblocked().showOnlyWithPositiveBalance()
                .whereBalanceLessThen(5).whereBalanceMoreThen(3).findById(3)
                .withSortByBalance().withSortByOrganisationName().setDESC().withSortByBlocked().withSortByMinAllowedBalance().setDESC()
                .build());


        Request requestUseBuildDao = (Request) DaoFactory.getINSTANCE().getRequestBuilder().build();


        Request baseRequestDao = (Request) (DaoFactory.getINSTANCE().getRequestBuilder().getBasicRequest());


        Request allParametersRequestDao = (Request) (DaoFactory.getINSTANCE().getRequestBuilder()
                .showOnlyBlocked().showOnlyWithNegativeBalance().showOnlyUnblocked().showOnlyWithPositiveBalance()
                .whereBalanceLessThen(5).whereBalanceMoreThen(3).findById(3)
                .withSortByBalance().withSortByOrganisationName().setDESC().withSortByBlocked().withSortByMinAllowedBalance().setDESC()
                .build());

        Assert.assertEquals(requestUseBuild, requestUseBuildDao);
        Assert.assertEquals(baseRequest, baseRequestDao);
        Assert.assertEquals(allParametersRequest, allParametersRequestDao);

    }

    @Test
    public void factoryReturnDifferentEssencesCheck() throws ServiceException {

        System.out.println("factoryReturnDifferentParametersTest");

        IBankAccountsDeliverService bankAccountsDeliverService2 = ServiceFactory.getINSTANCE().getBankAccountsDeliver();

        assertNotEquals(bankAccountsDeliverService, bankAccountsDeliverService2);

        System.out.println("factoryReturnDifferentParametersTest Finish");
    }

}