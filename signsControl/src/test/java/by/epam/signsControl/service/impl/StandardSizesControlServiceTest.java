package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StandardSizesControlServiceTest {

    private IStandardSizesControl standardSizesControlDaoMock;
    private StandardSizesControlService standardSizesControlService;

    @Before
    public void setUp() {

        standardSizesControlDaoMock = mock(IStandardSizesControl.class);

        standardSizesControlService = new StandardSizesControlService(standardSizesControlDaoMock);

    }

    @After
    public void tearDown() {
        standardSizesControlDaoMock = null;

        standardSizesControlService = null;
    }

    @Test
    public void addStandardSize() throws DAOException, ServiceException {

        when(standardSizesControlDaoMock.addStandardSize(anyInt(), anyString()))
                .thenReturn(new StandardSize(2, "info"));

        Assert.assertEquals(standardSizesControlService.addStandardSize(2, "info"),
                new StandardSize(2, "info"));
    }


    @Test(expected = ServiceException.class)
    public void addStandardSizeException() throws DAOException, ServiceException {

        when(standardSizesControlDaoMock.addStandardSize(anyInt(), anyString()))
                .thenThrow(DAOException.class);

        standardSizesControlService.addStandardSize(2, "info");
    }

    @Test(expected = ServiceValidationException.class)
    public void addStandardSizeValidationException() throws DAOException, ServiceException {

        when(standardSizesControlDaoMock.addStandardSize(anyInt(), anyString()))
                .thenThrow(DAOValidationException.class);

        standardSizesControlService.addStandardSize(2, "info");
    }

    @Test(expected = ServiceValidationException.class)
    public void addStandardSizeNullInfo() throws DAOException, ServiceException {

        standardSizesControlService.addStandardSize(2, null);
    }

}