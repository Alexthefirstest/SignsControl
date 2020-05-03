package rolesOrganisationsUsersController.service.factory;

import rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import rolesOrganisationsUsersController.service.IRolesControllerService;
import rolesOrganisationsUsersController.service.IUsersControllerService;
import rolesOrganisationsUsersController.service.impl.OrganisationsControllerService;
import rolesOrganisationsUsersController.service.impl.RolesControllerService;
import rolesOrganisationsUsersController.service.impl.UsersControllerService;

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
