package by.epam.bank.service.impl;

import by.epam.bank.bean.Transaction;
import by.epam.bank.dao.IBankAccountsManager;
import by.epam.bank.dao.IFinanceOperationsManager;
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


import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinanceOperationsManagerServiceTest {

    private FinanceOperationsManagerService fms;
    private IFinanceOperationsManager fmsDaoMock;

    @Before
    public void setUp() throws Exception {

        fmsDaoMock = mock(IFinanceOperationsManager.class);
        fms = new FinanceOperationsManagerService(fmsDaoMock);

    }

    @After
    public void tearDown() throws Exception {

        fms = null;
        fmsDaoMock = null;

    }

    @Test
    public void transferMoneyTest() throws DAOException, ServiceException {

        Date date = new Date();

        when(fmsDaoMock.transferMoney(1, 2, 24)).thenReturn(new Transaction(2,

                new Organisation(21, "name", new Role(2, "role"), true, "info"),
                new Organisation(25, "name1", new Role(22, "role2"), true, "infof"),
                54.43, new Timestamp(date.getTime()))
        );

        Assert.assertEquals(fms.transferMoney(1, 2, 24),
                new Transaction(2,

                        new Organisation(21, "name", new Role(2, "role"), true, "info"),
                        new Organisation(25, "name1", new Role(22, "role2"), true, "infof"),
                        54.43, new Timestamp(date.getTime()))
        );
    }


    @Test(expected = ServiceValidationException.class)
    public void transferMoneyValidationExceptionTest() throws DAOException, ServiceException {



        when(fmsDaoMock.transferMoney(1, 2, 24)).thenThrow(DAOValidationException.class);

        fms.transferMoney(1, 2, 24);
    }

    @Test(expected = ServiceException.class)
    public void transferMoneyExceptionTest() throws DAOException, ServiceException {


        when(fmsDaoMock.transferMoney(1, 2, 24)).thenThrow(DAOException.class);

        fms.transferMoney(1, 2, 24);
    }

    @Test(expected = ServiceValidationException.class)
    public void transferMoneyNegativeAmountTest() throws DAOException, ServiceException {


        fms.transferMoney(1, 2, -24);

    }

    @Test(expected = ServiceValidationException.class)
    public void transferMoneyEqualsSenderAndPayeeTest() throws DAOException, ServiceException {


        fms.transferMoney(1, 1, 24);

    }

    @Test
    public void addMoney() {
    }
}