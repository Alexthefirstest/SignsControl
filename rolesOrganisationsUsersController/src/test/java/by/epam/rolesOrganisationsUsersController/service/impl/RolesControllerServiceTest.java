package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class RolesControllerServiceTest {


    private static RolesControllerService rolesControllerService;
    private static IRolesController rolesControllerMock;

//    @BeforeClass
//    public static void setUp() {
//
////        rolesControllerService = new RolesControllerService();
//        rolesControllerService = mock(RolesControllerService.class);
//
//        rolesControllerMock = mock(DaoFactory.getINSTANCE().getRolesController().getClass());
//
//
//    }
//
//    @AfterClass
//    public static void tearDown() {
//
//        rolesControllerService = null;
//        rolesControllerMock = null;
//    }
//
//    @Test
//    public void getOrganisationsRolesTest() throws ServiceException, DAOException {
//
//
//        when(rolesControllerService.getOrganisationsRoles()).thenReturn(new Role[]{new Role(2, "role2")});
//
//        System.out.println(Arrays.toString(rolesControllerService.getOrganisationsRoles()));
//        System.out.println(Arrays.toString(new Role[]{new Role(2, "role2")}));
//
//        System.out.println(Arrays.equals((new Role[]{new Role(2, "role2")}), rolesControllerService.getOrganisationsRoles()));
//        System.out.println((new Role(2, "role2")).equals(rolesControllerService.getOrganisationsRoles()[0]));
//
//        Assert.assertArrayEquals(rolesControllerService.getOrganisationsRoles(), new Role[]{new Role(2, "role2")});
//
//    }
//
//    @Test(expected = DAOException.class)
//    public void getOrganisationsRolesExceptionTest() throws ServiceException, DAOException {
//
//        when(rolesControllerMock.getOrganisationsRoles()).thenThrow(DAOException.class);
//
//        rolesControllerService.getOrganisationsRoles();
//
//    }
//
//    @Test
//    public void getUsersRole() {
//    }
//
//    @Test
//    public void getUsersRolesBeside() {
//    }
//
//    @Test
//    public void setOrganisationRoleName() {
//    }
//
//    @Test
//    public void getUsersRoles() {
//    }
//
//    @Test
//    public void setUserRoleName() {
//    }
}