package by.epam.rolesOrganisationsUsersController.dao.factory;

import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.IUsersController;
import by.epam.rolesOrganisationsUsersController.dao.impl.OrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.impl.RolesController;
import by.epam.rolesOrganisationsUsersController.dao.impl.UsersController;

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
