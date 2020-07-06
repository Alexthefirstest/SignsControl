package by.epam.rolesOrganisationsUsersController.service;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IRolesController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see Role
 * @see ServiceException
 */
public interface IRolesControllerService {


//    /**
//     * add role to jdbc table
//     *
//     * @param name role name
//     * @return {@link Role} if successfully added
//     * @throws ServiceException when get an exception during execution
//     */
//    Role addRole(String name) throws ServiceException;
//
//    /**
//     * delete role from jdbc table
//     *
//     * @param id role id
//     * @return true if success, false - if no
//     * @throws ServiceException when get an exception during execution
//     */
//    boolean deleteRole(int id) throws ServiceException;


    /**
     * get array of all {@link by.epam.rolesOrganisationsUsersController.bean.Organisation} roles
     *
     * @return {@link Role} array
     * @throws ServiceException when get an exception during execution
     */
    Role[] getOrganisationsRoles() throws ServiceException;

    /**
     * get array of all {@link by.epam.rolesOrganisationsUsersController.bean.User} roles
     *
     * @return {@link Role} array
     * @throws ServiceException when get an exception during execution
     */
    Role[] getUsersRoles() throws ServiceException;

    /**
     * @param id   {@link by.epam.rolesOrganisationsUsersController.bean.User} role id
     * @param name new user role name
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setUserRoleName(int id, String name) throws ServiceException;

    /**
     * @param id   {@link by.epam.rolesOrganisationsUsersController.bean.Organisation} role id
     * @param name new organisation role name
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setOrganisationRoleName(int id, String name) throws ServiceException;

}
