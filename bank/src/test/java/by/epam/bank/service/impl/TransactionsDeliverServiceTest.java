package by.epam.bank.service.impl;

import by.epam.bank.dao.IOrganisationsDeliver;
import by.epam.bank.dao.ITransactionsDeliver;
import by.epam.bank.dao.exceptions.DAOException;
import by.epam.bank.dao.exceptions.DAOValidationException;
import by.epam.bank.dao.impl.TransactionsDeliver;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionsDeliverServiceTest {

    private TransactionsDeliverService tds;
    private ITransactionsDeliver tdsDaoMock;

    @Before
    public void setUp() {

        tdsDaoMock = mock(ITransactionsDeliver.class);
        tds = new TransactionsDeliverService(tdsDaoMock);

    }

    @After
    public void tearDown() {

        tdsDaoMock = null;
        tds = null;

    }


    @Test
    public void getPagesQuantityByDate() throws DAOException, ServiceException {

        int countOnPage = 20;

        when(tdsDaoMock.getFieldsCountByDate(1, "1999.21.07", "2020.08.05"))
                .thenReturn(19).thenReturn(20).thenReturn(21);


        Assert.assertEquals(
                tds.getPagesQuantityByDate(1, "1999.21.07", "2020.08.05", countOnPage), 1);

        Assert.assertEquals(
                tds.getPagesQuantityByDate(1, "1999.21.07", "2020.08.05", countOnPage), 1);

        Assert.assertEquals(
                tds.getPagesQuantityByDate(1, "1999.21.07", "2020.08.05", countOnPage), 2);

    }

    @Test(expected = ServiceException.class)
    public void getPagesQuantityByDateException() throws DAOException, ServiceException {

        int countOnPage = 20;

        when(tdsDaoMock.getFieldsCountByDate(1, "1999.21.07", "2020.08.05"))
                .thenThrow(DAOException.class);


        tds.getPagesQuantityByDate(1, "1999.21.07", "2020.08.05", countOnPage);

    }

    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateValidationException() throws DAOException, ServiceException {

        int countOnPage = 20;

        when(tdsDaoMock.getFieldsCountByDate(1, "1999.21.07", "2020.08.05"))
                .thenThrow(DAOValidationException.class);


        tds.getPagesQuantityByDate(1, "1999.21.07", "2020.08.05", countOnPage);

    }

    @Test
    public void getPagesQuantityByDateDatePatterns() throws DAOException, ServiceException {

        int countOnPage = 20;

        when(tdsDaoMock.getFieldsCountByDate(1, "1999.21.07", "2020.08.05"))
                .thenReturn(19);


        tds.getPagesQuantityByDate(1, "1999.21.07", "2020.08", countOnPage);

        tds.getPagesQuantityByDate(1, "1999.21.07", "2020", countOnPage);

    }


    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateInvalidDateFormatTest1() throws DAOException, ServiceException {

        int countOnPage = 20;

        tds.getPagesQuantityByDate(1, "1999.21.", "2020.08.05", countOnPage);

    }

    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateInvalidDateFormatTest2() throws DAOException, ServiceException {

        int countOnPage = 20;

        tds.getPagesQuantityByDate(1, "1999.", "2020.08.05", countOnPage);

    }

    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateInvalidDateFormatTest3() throws DAOException, ServiceException {

        int countOnPage = 20;

        tds.getPagesQuantityByDate(1, "", "2020.08.05", countOnPage);

    }

    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateInvalidDateFormatTest4() throws DAOException, ServiceException {

        int countOnPage = 20;

        tds.getPagesQuantityByDate(1, null, "2020.08.05", countOnPage);

    }

    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateInvalidDateFormatTest5() throws DAOException, ServiceException {

        int countOnPage = 20;

        tds.getPagesQuantityByDate(1, "05.08.2020", "2020.08.05", countOnPage);

    }

    @Test(expected = ServiceValidationException.class)
    public void getPagesQuantityByDateInvalidDateFormatTest6() throws DAOException, ServiceException {

        int countOnPage = 20;

        tds.getPagesQuantityByDate(1, "08.2020", "2020.08.05", countOnPage);

    }

}