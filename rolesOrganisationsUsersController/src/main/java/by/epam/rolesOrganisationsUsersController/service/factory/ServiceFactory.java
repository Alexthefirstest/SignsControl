package by.epam.rolesOrganisationsUsersController.service.factory;

import by.epam.rolesOrganisationsUsersController.service.impl.OrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.impl.RolesControllerService;
import by.epam.rolesOrganisationsUsersController.service.impl.UsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.IRolesControllerService;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;

/**
 * factory for service layer
 *
 * @author Bulgak Alexander
 */
public class ServiceFactory {

    /*
     *empty constructor
     */
    public ServiceFactory() {
    }

    /**
     * this class instance
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * {@link IOrganisationsControllerService} realisation
     */
    private final IOrganisationsControllerService organisationsControllerService = new OrganisationsControllerService();

    /**
     * {@link IRolesControllerService} realisation
     */
    private final IRolesControllerService rolesControllerService = new RolesControllerService();

    /**
     * {@link IUsersControllerService} realisation
     */
    private final IUsersControllerService usersControllerService = new UsersControllerService();

    /**
     * @return {@link ServiceFactory#INSTANCE}
     */
    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link ServiceFactory#organisationsControllerService}
     */
    public IOrganisationsControllerService getOrganisationsControllerService() {
        return organisationsControllerService;
    }

    /**
     * @return {@link ServiceFactory#rolesControllerService}
     */
    public IRolesControllerService getRolesControllerService() {
        return rolesControllerService;
    }

    /**
     * @return {@link ServiceFactory#usersControllerService}
     */
    public IUsersControllerService getUsersControllerService() {
        return usersControllerService;
    }
}
