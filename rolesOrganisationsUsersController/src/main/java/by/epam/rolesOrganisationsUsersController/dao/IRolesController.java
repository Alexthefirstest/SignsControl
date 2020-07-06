package by.epam.rolesOrganisationsUsersController.dao;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;

/**
 * JDBC roles table redactor
 *
 * @author Bulgak Alexander
 * @see Role
 * @see DAOException
 */
public interface IRolesController {

//    /**
//     * add role to jdbc table
//     *
//     * @param name role name
//     * @return {@link Role} if successfully added
//     * @throws DAOException when get an exception during execution
//     */
//    Role addRole(String name) throws DAOException;
//
//    /**
//     * delete role from jdbc table
//     *
//     * @param id role id
//     * @return true if success, false - if no
//     * @throws DAOException when get an exception during execution
//     */
//    boolean deleteRole(int id) throws DAOException;

    /**
     * get array of all {@link by.epam.rolesOrganisationsUsersController.bean.Organisation} roles
     *
     * @return {@link Role} array
     * @throws DAOException when get an exception during execution
     */
    Role[] getOrganisationsRoles() throws DAOException;

    /**
     * get array of all {@link by.epam.rolesOrganisationsUsersController.bean.User} roles
     *
     * @return {@link Role} array
     * @throws DAOException when get an exception during execution
     */
    Role[] getUsersRoles() throws DAOException;

    /**
     * @param id   {@link by.epam.rolesOrganisationsUsersController.bean.User} role id
     * @param name new user role name
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setUserRoleName(int id, String name) throws DAOException;

    /**
     * @param id   {@link by.epam.rolesOrganisationsUsersController.bean.Organisation} role id
     * @param name new organisation role name
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setOrganisationRoleName(int id, String name) throws DAOException;

}
