package by.epam.rolesOrganisationsUsersController.dao.impl;

import by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganisationsControllerTest {

    private static IOrganisationsController organisationsController;

    @BeforeClass
    public static void beforeClass() {
        organisationsController = DaoFactory.getINSTANCE().getOrganisationsController();
    }

    @AfterClass
    public static void afterClass() {
        organisationsController = null;
    }

    @Test(expected = DAOValidationException.class)
    public void addOrganisationExceptionTest() throws DAOException {

        organisationsController.addOrganisation("testOrgUnit", 15000000);

    }
//
//    public void addOrganisationTest() throws DAOException {
//
//        organisationsController.addOrganisation("testOrgUnit", 1);
//
//    }
//
//    @Test
//    public void setName() {
//    }
//
//    @Test
//    public void setRole() {
//    }
//
//    @Test
//    public void setInfo() {
//    }
//
//    @Test
//    public void getInfo() {
//    }
//
//    @Test
//    public void getBlock() {
//    }
//
//    @Test
//    public void setBlock() {
//    }
//
//    @Test
//    public void getOrganisations() {
//    }
//
//    @Test
//    public void getOrganisation() {
//    }
//
//    @Test
//    public void getOrganisationsBeside() {
//    }
//
//    @Test
//    public void testGetOrganisations() {
//    }
//
//    @Test
//    public void getUnblockedOrganisations() {
//    }
}