package by.epam.orders.service.impl;

import by.epam.orders.bean.TypeOfWork;

import by.epam.orders.dao.ITypeOfWorkControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TypeOfWorkControlServiceTest {

    private ITypeOfWorkControl typeOfWorkControl;
    private TypeOfWorkControlService typeOfWorkControlService;

    @Before
    public void setUp() throws Exception {

        typeOfWorkControl = mock(ITypeOfWorkControl.class);
        typeOfWorkControlService = new TypeOfWorkControlService(typeOfWorkControl);

    }

    @After
    public void tearDown() throws Exception {

        typeOfWorkControlService = null;
        typeOfWorkControl = null;

    }

    @Test
    public void addTypeOfWork() throws DAOException, ServiceException {

        when(typeOfWorkControl.addTypeOfWork("name", 32)).thenReturn(new TypeOfWork(1, "name", 32, false));

        Assert.assertEquals(typeOfWorkControlService.addTypeOfWork("name", 32),
                (new TypeOfWork(1, "name", 32, false)));

    }

    @Test(expected = ServiceException.class)
    public void addTypeOfWorkException() throws DAOException, ServiceException {

        when(typeOfWorkControl.addTypeOfWork("name", 32)).thenThrow(DAOException.class);

        typeOfWorkControlService.addTypeOfWork("name", 32);

    }

    @Test(expected = ServiceValidationException.class)
    public void addTypeOfWorkValidationException() throws DAOException, ServiceException {

        when(typeOfWorkControl.addTypeOfWork("name", 32)).thenThrow(DAOValidationException.class);

        typeOfWorkControlService.addTypeOfWork("name", 32);

    }

    @Test(expected = ServiceValidationException.class)
    public void addTypeOfWorkNullParameter() throws DAOException, ServiceException {

        typeOfWorkControlService.addTypeOfWork(null, 32);

    }


}