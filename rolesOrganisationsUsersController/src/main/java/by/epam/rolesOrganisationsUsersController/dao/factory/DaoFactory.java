package by.epam.rolesOrganisationsUsersController.dao.factory;

import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.IUsersController;
import by.epam.rolesOrganisationsUsersController.dao.impl.OrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.impl.RolesController;
import by.epam.rolesOrganisationsUsersController.dao.impl.UsersController;

/**
 * factory for dao layer
 *
 * @author Bulgak Alexander
 */
public class DaoFactory {

    /*
     *empty constructor
     */
    private DaoFactory() {

    }

    /**
     * this class instance
     */
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * {@link IOrganisationsController} realisation
     */
    private final IOrganisationsController organisationsController = new OrganisationsController();

    /**
     * {@link IRolesController} realisation
     */
    private final IRolesController rolesController = new RolesController();

    /**
     * {@link IUsersController} realisation
     */
    private final IUsersController usersController = new UsersController();

    /**
     * @return {@link DaoFactory#INSTANCE}
     */
    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link DaoFactory#organisationsController}
     */
    public IOrganisationsController getOrganisationsController() {
        return organisationsController;
    }

    /**
     * @return {@link DaoFactory#rolesController}
     */
    public IRolesController getRolesController() {
        return rolesController;
    }

    /**
     * @return {@link DaoFactory#usersController}
     */
    public IUsersController getUsersController() {
        return usersController;
    }
}
