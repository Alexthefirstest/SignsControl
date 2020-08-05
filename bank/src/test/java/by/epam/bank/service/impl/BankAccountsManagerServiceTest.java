package by.epam.bank.service.impl;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.dao.IBankAccountsManager;
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
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankAccountsManagerServiceTest {

    private BankAccountsManagerService bam;
    private IBankAccountsManager bamDaoMock;

    @Before
    public void setUp() throws Exception {

        bamDaoMock = mock(IBankAccountsManager.class);
        bam = new BankAccountsManagerService(bamDaoMock);

    }

    @After
    public void tearDown() throws Exception {

        bam = null;
        bamDaoMock = null;

    }

    @Test
    public void createBankAccount() throws DAOException, ServiceException {

        when(bamDaoMock.createBankAccount(Mockito.anyInt())).thenReturn(new BankAccount(
                new Organisation(2, "name", new Role(1, "role"), true, "info"),
                43, 54, true, "info"));

        Assert.assertEquals(bam.createBankAccount(2), new BankAccount(
                new Organisation(2, "name", new Role(1, "role"), true, "info"),
                43, 54, true, "info"));

    }


    @Test
    public void setInfoTest() throws DAOException, ServiceException {

        when(bamDaoMock.setInfo(Mockito.anyInt(), Mockito.anyString())).thenReturn(true);

        Assert.assertTrue(bam.setInfo(2, "hi"));
    }

    @Test(expected = ServiceValidationException.class)
    public void setInfoValidationExceptionTest() throws DAOException, ServiceException {

        when(bamDaoMock.setInfo(Mockito.anyInt(), Mockito.anyString())).thenThrow(DAOValidationException.class);

        bam.setInfo(2, "hi");
    }

    @Test(expected = ServiceException.class)
    public void setInfoExceptionTest() throws DAOException, ServiceException {

        when(bamDaoMock.setInfo(Mockito.anyInt(), Mockito.anyString())).thenThrow(DAOException.class);

        bam.setInfo(2, "hi");
    }

    @Test(expected = ServiceException.class)
    public void setInfoInputNullTest() throws DAOException, ServiceException {

        bam.setInfo(2, null);
    }

    @Test
    public void getInfoTest() throws ServiceException, DAOException {

        when(bamDaoMock.getInfo(45)).thenReturn("info");

        Assert.assertEquals("info", bam.getInfo(45));

    }


}