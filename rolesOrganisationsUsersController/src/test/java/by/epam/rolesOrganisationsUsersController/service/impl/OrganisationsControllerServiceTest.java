package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrganisationsControllerServiceTest {

    private IOrganisationsControllerService ocService;
    private IOrganisationsController mockedOCDao;

    @Before
    public void setUp() {

        mockedOCDao = mock(IOrganisationsController.class);
        ocService = new OrganisationsControllerService(mockedOCDao);

    }

    @After
    public void tearDown() {

        mockedOCDao = null;
        ocService = null;

    }

    @Test
    public void addOrganisationTest() throws DAOException, ServiceException {

        when(mockedOCDao.addOrganisation(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(new Organisation(21, "name", new Role(2, "role"), true, "info"));

        Assert.assertEquals(ocService.addOrganisation("name", 2)
                , new Organisation(21, "name", new Role(2, "role"), true, "info"));

    }

    @Test(expected = ServiceException.class)
    public void addOrganisationExceptionTest() throws DAOException, ServiceException {

        when(mockedOCDao.addOrganisation(Mockito.anyString(), Mockito.anyInt()))
                .thenThrow(DAOException.class);

        ocService.addOrganisation("name", 2);

    }

    @Test(expected = ServiceValidationException.class)
    public void addOrganisationValidationExceptionTest() throws DAOException, ServiceException {

        when(mockedOCDao.addOrganisation(Mockito.anyString(), Mockito.anyInt()))
                .thenThrow(DAOValidationException.class);

        ocService.addOrganisation("name", 2);

    }


    @Test(expected = ServiceValidationException.class)
    public void addOrganisationInvalidDataTest() throws DAOException, ServiceException {

        ocService.addOrganisation(null, 2);

    }

}