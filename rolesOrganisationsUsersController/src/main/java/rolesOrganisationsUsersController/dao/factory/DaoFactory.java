package rolesOrganisationsUsersController.dao.factory;

import rolesOrganisationsUsersController.dao.IOrganisationsController;
import rolesOrganisationsUsersController.dao.IRolesController;
import rolesOrganisationsUsersController.dao.IUsersController;
import rolesOrganisationsUsersController.dao.impl.OrganisationsController;
import rolesOrganisationsUsersController.dao.impl.RolesController;
import rolesOrganisationsUsersController.dao.impl.UsersController;

public class DaoFactory {

    private DaoFactory() {

    }

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final IOrganisationsController organisationsController = new OrganisationsController();
    private final IRolesController rolesController = new RolesController();
    private final IUsersController usersController = new UsersController();

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public IOrganisationsController getOrganisationsController() {
        return organisationsController;
    }

    public IRolesController getRolesController() {
        return rolesController;
    }

    public IUsersController getUsersController() {
        return usersController;
    }
}
