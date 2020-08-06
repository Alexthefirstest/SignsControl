package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.dao.IDirectionsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.service.IDirectionsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirectionsControlServiceTest {

    private IDirectionsControl directionsControlDaoMock;
    private IDirectionsControlService directionsControlService;

    @Before
    public void setUp() {

        directionsControlDaoMock = mock(IDirectionsControl.class);

        directionsControlService = new DirectionsControlService(directionsControlDaoMock);

    }

    @After
    public void tearDown() {
        directionsControlDaoMock = null;
        directionsControlService = null;
    }

    @Test
    public void getPointDirectionsSignListID() throws DAOException, ServiceException {

        when(directionsControlDaoMock.getPointDirectionsSignListID(Mockito.anyString())).thenReturn(
                new Direction[]{new Direction(1, 'q')}
        );


        Assert.assertEquals(directionsControlService.getPointDirectionsSignListID("POINT(21.54 54)"),
                new Direction[]{new Direction(1, 'q')});

    }

    @Test(expected = ServiceValidationException.class)
    public void getPointDirectionsSignListIDInvalidData() throws DAOException, ServiceException {


        directionsControlService.getPointDirectionsSignListID("24,43");


    }

    @Test(expected = ServiceValidationException.class)
    public void getPointDirectionsSignListIDNullData() throws DAOException, ServiceException {


        directionsControlService.getPointDirectionsSignListID(null);


    }

    @Test(expected = ServiceValidationException.class)
    public void getPointDirectionsSignListIDValidationException() throws DAOException, ServiceException {

        when(directionsControlDaoMock.getPointDirectionsSignListID(Mockito.anyString())).thenThrow(DAOValidationException.class);


        directionsControlService.getPointDirectionsSignListID("POINT(21.54 54)");

    }

    @Test(expected = ServiceException.class)
    public void getPointDirectionsSignListIDException() throws DAOException, ServiceException {

        when(directionsControlDaoMock.getPointDirectionsSignListID(Mockito.anyString())).thenThrow(DAOException.class);

        directionsControlService.getPointDirectionsSignListID("POINT(21.54 54)");


    }


}