package by.epam.rolesOrganisationsUsersController.service.factory;


import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceFactoryTest {

    @Test
    public void getINSTANCES() {

        ServiceFactory serviceFactory=ServiceFactory.getINSTANCE();

        assertNotNull(serviceFactory.getOrganisationsControllerService());
        assertNotNull(serviceFactory.getRolesControllerService());
        assertNotNull(serviceFactory.getUsersControllerService());

    }
}