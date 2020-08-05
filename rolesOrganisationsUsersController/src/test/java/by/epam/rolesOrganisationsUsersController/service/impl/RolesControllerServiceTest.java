package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RolesControllerServiceTest {


    private RolesControllerService rolesControllerService;
    private IRolesController rolesControllerDao;

    @Before
    public void setUp() {

        rolesControllerDao = mock(IRolesController.class);

        rolesControllerService = new RolesControllerService(rolesControllerDao);


    }

    @After
    public void tearDown() {

        rolesControllerDao = null;

        rolesControllerService = null;


    }

    @Test
    public void getOrganisationsRolesTest() throws ServiceException, DAOException {

        when(rolesControllerDao.getOrganisationsRoles()).thenReturn(new Role[]{new Role(2, "role2")});

        Assert.assertArrayEquals(rolesControllerService.getOrganisationsRoles(), new Role[]{new Role(2, "role2")});

    }

    @Test(expected = ServiceException.class)
    public void getOrganisationsRolesExceptionTest() throws ServiceException, DAOException {


        when(rolesControllerDao.getOrganisationsRoles()).thenThrow(DAOException.class);


        rolesControllerService.getOrganisationsRoles();

    }

    @Test(expected = ServiceValidationException.class)
    public void getOrganisationsRolesValidationExceptionTest() throws ServiceException, DAOException {

        when(rolesControllerDao.getOrganisationsRoles()).thenThrow(DAOValidationException.class);

        rolesControllerService.getOrganisationsRoles();

    }


    @Test
    public void getUserRole() throws ServiceException, DAOException {

        when(rolesControllerDao.getUsersRole(Mockito.anyInt())).thenReturn(new Role[]{new Role(2, "role2")});

        Assert.assertArrayEquals(rolesControllerService.getUsersRole(2), new Role[]{new Role(2, "role2")});

    }

    @Test(expected = ServiceException.class)
    public void getUserRoleExceptionTest() throws ServiceException, DAOException {


        when(rolesControllerDao.getUsersRole(Mockito.anyInt())).thenThrow(DAOException.class);


        rolesControllerService.getUsersRole(2);

    }

    @Test(expected = ServiceValidationException.class)
    public void getUserRoleValidationExceptionTest() throws ServiceException, DAOException {

        when(rolesControllerDao.getUsersRole(Mockito.anyInt())).thenThrow(DAOValidationException.class);

        rolesControllerService.getUsersRole(2);

    }





    @Test
    public void setOrganisationRoleNameDataValidationTest() throws ServiceException, DAOException {

        when(rolesControllerDao.setOrganisationRoleName(anyInt(), anyString())).thenReturn(true);


        Assert.assertTrue(rolesControllerService.setOrganisationRoleName(23, "name"));
    }

    @Test(expected = ServiceValidationException.class)
    public void setOrganisationRoleNameDataInvalidExceptionTest() throws ServiceException, DAOException {

        when(rolesControllerDao.setOrganisationRoleName(anyInt(), anyString())).thenReturn(true);

        rolesControllerService.setOrganisationRoleName(23, "прив");

    }

    @Test
    public void setUserRoleNameDataValidationTest() throws ServiceException, DAOException {

        when(rolesControllerDao.setUserRoleName(anyInt(), anyString())).thenReturn(true);


        Assert.assertTrue(rolesControllerService.setUserRoleName(23, "name"));
    }

    @Test(expected = ServiceValidationException.class)
    public void setUserRoleNameDataInvalidExceptionTest() throws ServiceException, DAOException {

        when(rolesControllerDao.setUserRoleName(anyInt(), anyString())).thenReturn(true);

        rolesControllerService.setUserRoleName(23, "прив");

    }


}