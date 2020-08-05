package by.epam.bank.service.impl;

import by.epam.bank.dao.IFinanceOperationsManager;
import by.epam.bank.dao.IOrganisationsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrganisationsDeliverServiceTest {

    private OrganisationsDeliverService ods;
    private IOrganisationsDeliver odsDaoMock;

    @Before
    public void setUp() {

        odsDaoMock = mock(IOrganisationsDeliver.class);
        ods = new OrganisationsDeliverService(odsDaoMock);

    }

    @After
    public void tearDown()   {

        odsDaoMock = null;
        ods = null;

    }

    @Test(expected = ServiceException.class)
    public void showOrganisationsWithoutBankAccountsExceptionTest() throws ServiceException, DAOException {

        when(odsDaoMock.showOrganisationsWithoutBankAccounts()).thenThrow(DAOException.class);

        ods.showOrganisationsWithoutBankAccounts();
    }

    @Test(expected = ServiceValidationException.class)
    public void showOrganisationsWithoutBankAccountsValidationExceptionTest() throws ServiceException, DAOException {

        when(odsDaoMock.showOrganisationsWithoutBankAccounts()).thenThrow(DAOValidationException.class);

        ods.showOrganisationsWithoutBankAccounts();
    }

    @Test
    public void showOrganisationsWithoutBankAccountsTest() throws ServiceException, DAOException {

        when(odsDaoMock.showOrganisationsWithoutBankAccounts()).thenReturn(new Organisation[]{
                new Organisation(21, "name", new Role(2, "role"), true, "info"),
                new Organisation(25, "name1", new Role(22, "role2"), true, "infof")}
        );

        Assert.assertArrayEquals(ods.showOrganisationsWithoutBankAccounts(),
                new Organisation[]{
                        new Organisation(21, "name", new Role(2, "role"), true, "info"),
                        new Organisation(25, "name1", new Role(22, "role2"), true, "infof")}
        );
    }

}