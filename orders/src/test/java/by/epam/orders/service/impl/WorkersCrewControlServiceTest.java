package by.epam.orders.service.impl;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkersCrewControlServiceTest {

    private IWorkersCrewControl workersCrewControlMockedDao;
    private IUsersControllerService usersControllerServiceMocked;
    private WorkersCrewControlService workersCrewControlService;

    @Before
    public void setUp() throws Exception {

        usersControllerServiceMocked = mock(IUsersControllerService.class);
        workersCrewControlMockedDao = mock(IWorkersCrewControl.class);

        workersCrewControlService = new WorkersCrewControlService(workersCrewControlMockedDao, usersControllerServiceMocked);

    }

    @After
    public void tearDown() throws Exception {

        workersCrewControlMockedDao = null;
        usersControllerServiceMocked = null;
        workersCrewControlService = null;

    }


    @Test
    public void addWorkersCrew() throws DAOException, ServiceException {

        Date date = new Date();

        ArrayList<User> users = new ArrayList<>(List.of(new User(1, "login",
                new Role(3, "role"),
                new Organisation(2, "name", new Role(3, "role"), true, "info"),
                false, "name", "surname", "info")));

        when(workersCrewControlMockedDao.addWorkersCrew(anyString(), anyString(), anyInt()))
                .thenReturn(new WorkersCrew(1, users, date, date, "Info",
                        new Organisation(2, "name", new Role(3, "role"), true, "info")));

        Assert.assertEquals(workersCrewControlService.addWorkersCrew("2020", "l", 2),
                new WorkersCrew(1, users, date, date, "Info",
                        new Organisation(2, "name", new Role(3, "role"), true, "info")));

    }

    @Test(expected = ServiceException.class)
    public void addWorkersCrewException() throws DAOException, ServiceException {

        when(workersCrewControlMockedDao.addWorkersCrew(anyString(), anyString(), anyInt()))
                .thenThrow(DAOException.class);

        workersCrewControlService.addWorkersCrew("2020", "l", 2);

    }

    @Test(expected = ServiceValidationException.class)
    public void addWorkersCrewValidationException() throws DAOException, ServiceException {

        when(workersCrewControlMockedDao.addWorkersCrew(anyString(), anyString(), anyInt()))
                .thenThrow(DAOValidationException.class);

        workersCrewControlService.addWorkersCrew("2020", "l", 2);

    }

    @Test(expected = ServiceValidationException.class)
    public void addWorkersCrewNullDate() throws DAOException, ServiceException {

        workersCrewControlService.addWorkersCrew(null, "l", 2);

    }

    @Test(expected = ServiceValidationException.class)
    public void addWorkersCrewWrongDateFormat() throws DAOException, ServiceException {

        workersCrewControlService.addWorkersCrew("08.2020", "l", 2);

    }

    @Test
    public void addWorkerTest() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {

        Date date = new Date();

        int workersCrewOrganisationToAdd = 15;

        ArrayList<User> users = new ArrayList<>(List.of(new User(1, "login",
                new Role(3, "role"),
                new Organisation(workersCrewOrganisationToAdd, "name", new Role(3, "role"), true, "info"),
                false, "name", "surname", "info")));


        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);

        when(usersControllerServiceMocked.getUser(Mockito.anyInt())).thenReturn(
                new User(1, "login",
                        new Role(3, "role"),
                        new Organisation(workersCrewOrganisationToAdd, "name", new Role(3, "role"), true, "info"),
                        false, "name", "surname", "info")
        );

        when(workersCrewControlMockedDao.getWorkersCrewRemoveDate(Mockito.anyInt())).thenReturn(null);

        when(workersCrewControlMockedDao.addWorker(Mockito.anyInt(), Mockito.anyInt())).thenReturn(
                new WorkersCrew(1, users, date, date, "Info",
                        new Organisation(2, "name", new Role(3, "role"), true, "info"))
        );


        Assert.assertEquals(workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd),
                new WorkersCrew(1, users, date, date, "Info",
                        new Organisation(2, "name", new Role(3, "role"), true, "info")));

    }

    @Test(expected = ServiceValidationException.class)
    public void addWorkerTestAddingUserOrganisationNotEqualsWorkerCrewsOrganisation() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {


        int workersCrewOrganisationToAdd = 15;


        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);


        workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd + 1);

    }

    @Test(expected = ServiceValidationException.class)
    public void addWorkerUserNotExist() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {

        int workersCrewOrganisationToAdd = 15;

        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);

        when(usersControllerServiceMocked.getUser(Mockito.anyInt())).thenReturn(null);

        workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd);

    }


    @Test(expected = ServiceValidationException.class)
    public void addWorkerTestCrewWasRemoved() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {

        int workersCrewOrganisationToAdd = 15;


        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);

        when(usersControllerServiceMocked.getUser(Mockito.anyInt())).thenReturn(
                new User(1, "login",
                        new Role(3, "role"),
                        new Organisation(workersCrewOrganisationToAdd, "name", new Role(3, "role"), true, "info"),
                        false, "name", "surname", "info")
        );

        when(workersCrewControlMockedDao.getWorkersCrewRemoveDate(Mockito.anyInt())).thenReturn("2015.08.01");

        workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd);

    }


    @Test(expected = ServiceValidationException.class)
    public void addWorkerTestCrewIdNotEqualsWorkerId() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {


        int workersCrewOrganisationToAdd = 15;


        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);

        when(usersControllerServiceMocked.getUser(Mockito.anyInt())).thenReturn(
                new User(1, "login",
                        new Role(3, "role"),
                        new Organisation(workersCrewOrganisationToAdd + 1, "name", new Role(3, "role"), true, "info"),
                        false, "name", "surname", "info")
        );

        when(workersCrewControlMockedDao.getWorkersCrewRemoveDate(Mockito.anyInt())).thenReturn(null);

        workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd);

    }

    @Test(expected = ServiceValidationException.class)
    public void addWorkerTestValidationException() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {


        int workersCrewOrganisationToAdd = 15;


        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);

        when(usersControllerServiceMocked.getUser(Mockito.anyInt())).
                thenThrow(by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException.class);


        workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd);

    }

    @Test(expected = ServiceException.class)
    public void addWorkerTestException() throws DAOException, ServiceException, by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException {


        int workersCrewOrganisationToAdd = 15;


        when(workersCrewControlMockedDao.getWorkersCrewOrganisationID(Mockito.anyInt())).thenReturn(workersCrewOrganisationToAdd);

        when(usersControllerServiceMocked.getUser(Mockito.anyInt())).
                thenThrow(by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException.class);


        workersCrewControlService.addWorker(1, 1, workersCrewOrganisationToAdd);

    }


}