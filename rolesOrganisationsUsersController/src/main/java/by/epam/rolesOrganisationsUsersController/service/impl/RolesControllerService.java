package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.IRolesControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see Role
 * @see ServiceException
 */
public class RolesControllerService implements IRolesControllerService {

    /**
     * {@link IRolesController} realisation
     */
    private final IRolesController rolesController = DaoFactory.getINSTANCE().getRolesController();

//    /**
//     * add role to jdbc table
//     *
//     * @param name role name
//     * @return {@link Role} if successfully added
//     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
//     *                                    or catch {@link DAOValidationException}
//     *                                    from {@link IRolesController#addRole(String)}
//     * @throws ServiceException           when catch {@link DAOException}
//     *                                    from {@link IRolesController#addRole(String)}
//     */
//    @Override
//    public Role addRole(String name) throws ServiceException {
//
//        InputValidation.nullAndSymbolsCheck(name);
//
//        try {
//
//            return rolesController.(name);
//        } catch (DAOValidationException ex) {
//            throw new ServiceValidationException(ex.getMessage());
//        } catch (DAOException ex) {
//            throw new ServiceException(ex);
//        }
//    }
//
//    /**
//     * delete role from jdbc table
//     *
//     * @param id role id
//     * @return true if success, false - if no
//     * @throws ServiceValidationException when  catch {@link DAOValidationException}
//     *                                    from {@link IRolesController#deleteRole(int)}
//     * @throws ServiceException           when catch {@link DAOException}
//     *                                    from {@link IRolesController#deleteRole(int)}
//     */
//    @Override
//    public boolean deleteRole(int id) throws ServiceException {
//
//        try {
//
//            return rolesController.deleteRole(id);
//        } catch (DAOValidationException ex) {
//            throw new ServiceValidationException(ex.getMessage());
//        } catch (DAOException ex) {
//            throw new ServiceException(ex);
//
//        }
//    }

    /**
     * get array of all roles
     *
     * @return {@link Role} array
     * @throws ServiceValidationException when { catch {@link DAOValidationException}
     *                                    from {@link IRolesController#getOrganisationsRoles()} ()}  }
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#getOrganisationsRoles()} ()}
     */
    @Override
    public Role[] getOrganisationsRoles() throws ServiceException {

        try {

            return rolesController.getOrganisationsRoles();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get array of  roles with first element is role with id
     *
     * @param id to find
     * @return {@link Role} array
     * @throws ServiceValidationException when { catch {@link DAOValidationException}
     *                                    from {@link IRolesController#getOrganisationsRoles()} ()}  }
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#getOrganisationsRoles()} ()}
     */
    @Override
    public Role[] getUsersRole(int id) throws ServiceException {
        try {

            return rolesController.getUsersRole(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get array of  roles with first element is role with id
     *
     * @param id to not show
     * @return {@link Role} array
     * @throws ServiceValidationException when { catch {@link DAOValidationException}
     *                                    from {@link IRolesController#getOrganisationsRoles()} ()}  }
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#getOrganisationsRoles()} ()}
     */
    @Override
    public Role[] getUsersRolesBeside(int id) throws ServiceException {
        try {

            return rolesController.getUsersRolesBeside(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id   role id
     * @param name new role name
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IRolesController#setOrganisationRoleName(int, String)} (int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#setOrganisationRoleName(int, String)} (int, String)}
     */
    @Override
    public boolean setOrganisationRoleName(int id, String name) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(name);

        try {

            return rolesController.setOrganisationRoleName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * get array of all roles
     *
     * @return {@link Role} array
     * @throws ServiceValidationException when { catch {@link DAOValidationException}
     *                                    from {@link IRolesController#getUsersRoles()} ()}  }
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#getUsersRoles()} ()}
     */
    @Override
    public Role[] getUsersRoles() throws ServiceException {

        try {

            return rolesController.getUsersRoles();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id   role id
     * @param name new role name
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IRolesController#setUserRoleName(int, String)} (int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#setUserRoleName(int, String)} (int, String)}
     */
    @Override
    public boolean setUserRoleName(int id, String name) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(name);

        try {

            return rolesController.setUserRoleName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
