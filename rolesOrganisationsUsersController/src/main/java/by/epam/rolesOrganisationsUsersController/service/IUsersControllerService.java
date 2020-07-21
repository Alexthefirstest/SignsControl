package by.epam.rolesOrganisationsUsersController.service;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.bean.User;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IUsersController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see User
 * @see ServiceException
 */
public interface IUsersControllerService {

    /**
     * add user to jdbc table
     *
     * @param login        user login
     * @param password     user password
     * @param role         user role
     * @param organisation user organisation
     * @param name         user name
     * @param surname      user surname
     * @return {@link User} if success
     * @throws ServiceException when get an exception during execution
     */
    User addUser(String login, String password, int role, int organisation, String name, String surname) throws ServiceException;

    /**
     * @param id user id in jdbc
     * @return block condition
     * @throws ServiceException when get an exception during execution
     */
    boolean getBlock(int id) throws ServiceException;

    /**
     * set block in user table
     *
     * @param id    user id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setBlock(int id, boolean block) throws ServiceException;

    /**
     * find user with login in data base and check their password
     *
     * @param login    user login from to check
     * @param password user password from to check
     * @return {@link User} if user with this login and password exist
     * @throws ServiceException when get an exception during execution
     */
    User checkLoginPassword(String login, String password) throws ServiceException;

    /**
     * set new login for user
     *
     * @param id    user id in jdbc
     * @param login new user login
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setLogin(int id, String login, String password, String newLogin) throws ServiceException;

    /**
     * set new user password
     *
     * @param id          user id in jdbc
     * @param login       user login in jdbc
     * @param password    user password in jdbc
     * @param newPassword new user password
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setPassword(int id, String login, String password, String newPassword) throws ServiceException;

    /**
     * set new user organisation
     *
     * @param id           user id
     * @param organisation new organisation id
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setOrganisation(int id, int organisation) throws ServiceException;

//    /**
//     * @param userId user id
//     * @return organisation of this user id
//     * @throws ServiceException when get an exception during execution
//     */
//    int getOrganisation(int userId) throws ServiceException;

    /**
     * get all users from data base
     *
     * @return {@link User} array
     * @throws ServiceException when get an exception during execution
     */
    User[] getUsers() throws ServiceException;

    /**
     * get all users from data base with organisation id param
     *
     * @param organisation organisation id
     * @return {@link User} array
     * @throws ServiceException when get an exception during execution
     */
    User[] getUsers(int organisation) throws ServiceException;

    /**
     * get user by id
     *
     * @param id user id
     * @return {@link User} with param id
     * @throws ServiceException when get an exception during execution
     */
    User getUser(int id) throws ServiceException;

    /**
     * set info for user
     *
     * @param id   user id in jdbc
     * @param info new  user info in jdbc
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setInfo(int id, String info) throws ServiceException;

    /**
     * set new name for user
     *
     * @param id   user id in jdbc
     * @param name new user name
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setName(int id, String name) throws ServiceException;

    /**
     * set new surname for user
     *
     * @param id      user id in jdbc
     * @param surname new  user surname
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setSurname(int id, String surname) throws ServiceException;

    /**
     * set new role for user
     *
     * @param id   user id in jdbc
     * @param role new  user role
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setRole(int id, int role) throws ServiceException;

}
