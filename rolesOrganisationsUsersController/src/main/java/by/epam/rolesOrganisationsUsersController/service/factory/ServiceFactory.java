package by.epam.rolesOrganisationsUsersController.service.factory;

import by.epam.rolesOrganisationsUsersController.service.impl.OrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.impl.RolesControllerService;
import by.epam.rolesOrganisationsUsersController.service.impl.UsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.IRolesControllerService;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final IOrganisationsControllerService organisationsControllerService = new OrganisationsControllerService();

    private final IRolesControllerService rolesControllerService = new RolesControllerService();

    private final IUsersControllerService usersControllerService = new UsersControllerService();

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public IOrganisationsControllerService getOrganisationsControllerService() {
        return organisationsControllerService;
    }

    public IRolesControllerService getRolesControllerService() {
        return rolesControllerService;
    }

    public IUsersControllerService getUsersControllerService() {
        return usersControllerService;
    }
}
