package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.dao.IDirectionsControl;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.service.IDirectionsControlService;
import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalSignsControlServiceTest {

    private ILocalSignsControl localSignsControlDaoMock;
    private LocalSignsControlService localSignsControlService;

    @Before
    public void setUp() {

        localSignsControlDaoMock = mock(ILocalSignsControl.class);

        localSignsControlService = new LocalSignsControlService(localSignsControlDaoMock);

    }

    @After
    public void tearDown() {
        localSignsControlDaoMock = null;
        localSignsControlService = null;
    }

    @Test
    public void addSign() throws DAOException, ServiceException {

        Date date = new Date();

        when(localSignsControlDaoMock.addSign(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new LocalSign(1, 1, 1, 1, 1,
                        2, null, 3, date, date, "annot", 'a', "name", "descr"));


        Assert.assertEquals(localSignsControlService.addSign(1, 1, 1, "2017.01.23", "annot")
                , new LocalSign(1, 1, 1, 1, 1,
                        2, null, 3, date, date, "annot", 'a', "name", "descr"));
    }

    @Test(expected = ServiceException.class)
    public void addSignExceptionTest() throws DAOException, ServiceException {


        when(localSignsControlDaoMock.addSign(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(DAOException.class);


        localSignsControlService.addSign(1, 1, 1, "2017.01.23", "annot");
    }

    @Test(expected = ServiceValidationException.class)
    public void addSignValidationExceptionTest() throws DAOException, ServiceException {


        when(localSignsControlDaoMock.addSign(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(DAOValidationException.class);


        localSignsControlService.addSign(1, 1, 1, "2017.01.23", "annot");
    }

    @Test(expected = ServiceValidationException.class)
    public void addSignTestWrongDateFormat() throws DAOException, ServiceException {


        localSignsControlService.addSign(1, 1, 1, "01.23", "annot");
    }

    @Test(expected = ServiceValidationException.class)
    public void addSignTestDateNull() throws DAOException, ServiceException {


        localSignsControlService.addSign(1, 1, 1, null, "annot");
    }

    @Test
    public void getSignsTest() throws DAOException, ServiceException {

        Date date = new Date();

        when(localSignsControlDaoMock.getSigns(Mockito.anyString()))
                .thenReturn(new LocalSign[]{new LocalSign(1, 1, 1, 1, 1,
                        2, null, 3, date, date, "annot", 'a', "name", "descr")});


        Assert.assertEquals(localSignsControlService.getSigns("POINT(45 23)"),
                new LocalSign[]{new LocalSign(1, 1, 1, 1, 1,
                        2, null, 3, date, date, "annot", 'a', "name", "descr")});
    }


    @Test(expected = ServiceValidationException.class)
    public void getSignsWrongPointFormat() throws DAOException, ServiceException {

        localSignsControlService.getSigns("43 35");
    }

}