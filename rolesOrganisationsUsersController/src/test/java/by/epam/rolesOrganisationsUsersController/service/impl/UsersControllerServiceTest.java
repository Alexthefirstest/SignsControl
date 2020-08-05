package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.dao.IUsersController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersControllerServiceTest {

    private IUsersControllerService ocService;
    private IUsersController mockedOCDao;

    @Before
    public void setUp() {

        mockedOCDao = mock(IUsersController.class);
        ocService = new UsersControllerService(mockedOCDao);

    }

    @After
    public void tearDown() {

        mockedOCDao = null;
        ocService = null;

    }


    @Test
    public void addOrganisationTest() throws DAOException, ServiceException {

        when(mockedOCDao.addUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new User(23, "login", new Role(3, "role"),
                        new Organisation(21, "name", new Role(2, "role"), true, "info"),
                        true, "name", "surname", "info"));

        Assert.assertEquals(ocService.addUser("login", "pass", 3, 3, "name", "surname")
                , new User(23, "login", new Role(3, "role"),
                        new Organisation(21, "name", new Role(2, "role"), true, "info"),
                        true, "name", "surname", "info"));

    }

    @Test(expected = ServiceException.class)
    public void addOrganisationExceptionTest() throws DAOException, ServiceException {

        when(mockedOCDao.addUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(DAOException.class);

        ocService.addUser("login", "pass", 3, 3, "name", "surname");

    }

    @Test(expected = ServiceValidationException.class)
    public void addOrganisationValidationExceptionTest() throws DAOException, ServiceException {

        when(mockedOCDao.addUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(DAOValidationException.class);

        ocService.addUser("login", "pass", 3, 3, "name", "surname");

    }


    @Test(expected = ServiceValidationException.class)
    public void addOrganisationInvalidDataTest() throws DAOException, ServiceException {

        ocService.addUser("ошибка", "pass", 3, 3, "name", "surname");

    }

    @Test(expected = ServiceValidationException.class)
    public void addOrganisationInvalidDataTestPassword() throws DAOException, ServiceException {

        ocService.addUser("login", "ошибка", 3, 3, "name", "surname");

    }

    @Test(expected = ServiceValidationException.class)
    public void addOrganisationInvalidDataTestNull() throws DAOException, ServiceException {

        ocService.addUser(null, "pass", 3, 3, "name", "surname");

    }

    @Test
    public void checkLoginPassword() throws DAOException, ServiceException {

        when(mockedOCDao.getPassword("login")).thenReturn(BCrypt.hashpw("newPassword", BCrypt.gensalt(8)));
        when(mockedOCDao.getUser("login")).thenReturn(new User(23, "login", new Role(3, "role"),
                new Organisation(21, "name", new Role(2, "role"), true, "info"),
                true, "name", "surname", "info"));


        Assert.assertEquals(ocService.checkLoginPassword("login", "newPassword"),
                new User(23, "login", new Role(3, "role"),
                        new Organisation(21, "name", new Role(2, "role"), true, "info"),
                        true, "name", "surname", "info"));
    }

    @Test
    public void checkLoginPasswordWrongPassword() throws DAOException, ServiceException {

        when(mockedOCDao.getPassword("login")).thenReturn(BCrypt.hashpw("newPassword", BCrypt.gensalt(8)));
        when(mockedOCDao.getUser("login")).thenReturn(new User(23, "login", new Role(3, "role"),
                new Organisation(21, "name", new Role(2, "role"), true, "info"),
                true, "name", "surname", "info"));


        Assert.assertNull(ocService.checkLoginPassword("login", "wrongPassword"));
    }

    @Test
    public void checkLoginPasswordWrongLogin() throws DAOException, ServiceException {

        when(mockedOCDao.getPassword("login")).thenReturn(null);

        Assert.assertNull(ocService.checkLoginPassword("login", "wrongPassword"));
    }


    @Test
    public void setLoginTest() throws DAOException, ServiceException {

        int id = 21;
        String login = "login";
        String newLogin = "newLogin";
        String password = "goodPassword";

        when(mockedOCDao.getPassword(login)).thenReturn(BCrypt.hashpw(password, BCrypt.gensalt(8)));
        when(mockedOCDao.setLogin(id, newLogin)).thenReturn(true);


        Assert.assertTrue(ocService.setLogin(id, login, password, newLogin));
    }

    @Test
    public void setLoginTestWrongPassword() throws DAOException, ServiceException {

        int id = 21;
        String login = "login";
        String newLogin = "newLogin";
        String password = "goodPassword";

        when(mockedOCDao.getPassword(login)).thenReturn(BCrypt.hashpw(password, BCrypt.gensalt(8)));
        when(mockedOCDao.setLogin(id, newLogin)).thenReturn(true);


        Assert.assertFalse(ocService.setLogin(id, login, "wrongPassword", newLogin));
    }

    @Test
    public void setLoginTestWrongLogin() throws DAOException, ServiceException {

        int id = 21;
        String login = "login";
        String newLogin = "newLogin";
        String password = "goodPassword";

        when(mockedOCDao.getPassword(login)).thenReturn(null);

        Assert.assertFalse(ocService.setLogin(id, login, "pass", newLogin));
    }

    @Test
    public void setLoginTestLoginWasNotSet() throws DAOException, ServiceException {

        int id = 21;
        String login = "login";
        String newLogin = "newLogin";
        String password = "goodPassword";

        when(mockedOCDao.getPassword(login)).thenReturn(BCrypt.hashpw(password, BCrypt.gensalt(8)));
        when(mockedOCDao.setLogin(id, newLogin)).thenReturn(false);


        Assert.assertFalse(ocService.setLogin(id, login, "wrongPassword", newLogin));
    }

    @Test
    public void getUsersTest() throws DAOException, ServiceException {

        when(mockedOCDao.getUsers()).thenReturn(new User[]{new User(23, "login", new Role(3, "role"),
                new Organisation(21, "name", new Role(2, "role"), true, "info"),
                true, "name", "surname", "info")});

        Assert.assertArrayEquals(new User[]{new User(23, "login", new Role(3, "role"),
                        new Organisation(21, "name", new Role(2, "role"), true, "info"),
                        true, "name", "surname", "info")},
                ocService.getUsers());
    }


}