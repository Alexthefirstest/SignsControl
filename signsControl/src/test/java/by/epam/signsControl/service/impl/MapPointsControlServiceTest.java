package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.service.IMapPointsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapPointsControlServiceTest {

    private IMapPointsControl mapPointsControlDaoMock;
    private IMapPointsControlService mapPointsControlService;

    @Before
    public void setUp() {

        mapPointsControlDaoMock = mock(IMapPointsControl.class);

        mapPointsControlService = new MapPointsControlService(mapPointsControlDaoMock);

    }

    @After
    public void tearDown() {
        mapPointsControlDaoMock = null;
        mapPointsControlService = null;
    }

    @Test
    public void getMapPoint() throws DAOException, ServiceException {

        when(mapPointsControlDaoMock.getMapPoint(anyString())).thenReturn(new MapPoint(
                "coords", new ArrayList<String>(List.of("addresse")), new ArrayList<Integer>(List.of(2)),
                new ArrayList<Character>(List.of('a')), new ArrayList<String>(List.of("annot"))));


        Assert.assertEquals(mapPointsControlService.getMapPoint("43.34,54.65"), new MapPoint(
                "coords", new ArrayList<String>(List.of("addresse")), new ArrayList<Integer>(List.of(2)),
                new ArrayList<Character>(List.of('a')), new ArrayList<String>(List.of("annot"))));

    }

    @Test(expected = ServiceException.class)
    public void getMapPointException() throws DAOException, ServiceException {

        when(mapPointsControlDaoMock.getMapPoint(anyString())).thenThrow(DAOException.class);

        mapPointsControlService.getMapPoint("43.34,54.65");

    }

    @Test(expected = ServiceValidationException.class)
    public void getMapPointValidationException() throws DAOException, ServiceException {

        when(mapPointsControlDaoMock.getMapPoint(anyString())).thenThrow(DAOValidationException.class);

        mapPointsControlService.getMapPoint("43.34,54.65");

    }

    @Test(expected = ServiceValidationException.class)
    public void getMapPointNullDate() throws DAOException, ServiceException {

        mapPointsControlService.getMapPoint(null);

    }

    @Test(expected = ServiceValidationException.class)
    public void getMapPointInvalidDate() throws DAOException, ServiceException {

        mapPointsControlService.getMapPoint("43.34,  54.65");

    }


    @Test
    public void addMapPoint() throws DAOException, ServiceException {

        when(mapPointsControlDaoMock.addMapPoint(anyString(), anyString(), anyInt())).thenReturn(new MapPoint(
                "coords", new ArrayList<String>(List.of("addresse")), new ArrayList<Integer>(List.of(2)),
                new ArrayList<Character>(List.of('a')), new ArrayList<String>(List.of("annot"))));

        Assert.assertEquals(mapPointsControlService.addMapPoint("POINT(43.34 54.65)", "info", 43), new MapPoint(
                "coords", new ArrayList<String>(List.of("addresse")), new ArrayList<Integer>(List.of(2)),
                new ArrayList<Character>(List.of('a')), new ArrayList<String>(List.of("annot"))));

        Assert.assertEquals(mapPointsControlService.addMapPoint("43.34,54.65", "info", 43), new MapPoint(
                "coords", new ArrayList<String>(List.of("addresse")), new ArrayList<Integer>(List.of(2)),
                new ArrayList<Character>(List.of('a')), new ArrayList<String>(List.of("annot"))));

    }

    @Test(expected = ServiceValidationException.class)
    public void addMapPointNullCoordinates() throws DAOException, ServiceException {

        mapPointsControlService.addMapPoint(null, "info", 43);

    }

    @Test(expected = ServiceValidationException.class)
    public void addMapPointWrongFormatCoordinates() throws DAOException, ServiceException {

        mapPointsControlService.addMapPoint("43.34,  54.65", "info", 43);

    }
}