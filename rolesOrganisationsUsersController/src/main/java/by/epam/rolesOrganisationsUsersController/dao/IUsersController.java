package by.epam.rolesOrganisationsUsersController.dao;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;

/**
 * JDBC roles table redactor
 *
 * @author Bulgak Alexander
 * @see User
 * @see DAOException
 */
public interface IUsersController {

    /**
     * add user to jdbc table
     *
     * @param login        user login
     * @param password     user password
     * @param organisation user organisation
     * @param name         user name
     * @param surname      user surname
     * @return {@link User} if success
     * @throws DAOException when get an exception during execution
     */
    User addUser(String login, String password, int role, int organisation, String name, String surname) throws DAOException;

    /**
     * @param id user id in jdbc
     * @return block condition
     * @throws DAOException when get an exception during execution
     */
    boolean getBlock(int id) throws DAOException;

    /**
     * set block in users table
     *
     * @param id    user id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setBlock(int id, boolean block) throws DAOException;

    /**
     * @param id    user id in jdbc
     * @param login new user login
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setLogin(int id, String login) throws DAOException;

    /**
     * @param id       user id in jdbc
     * @param password new user password
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setPassword(int id, String password) throws DAOException;

    /**
     * @param login user login
     * @return user password
     * @throws DAOException when get an exception during execution
     */
    String getPassword(String login) throws DAOException;

    /**
     * @param id           user id
     * @param organisation new organisation id
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setOrganisation(int id, int organisation) throws DAOException;

//    /**
//     * @param userId user id
//     * @return {@link Organisation}
//     * @throws DAOException when get an exception during execution
//     */
//   Organisation getOrganisation(int userId) throws DAOException;

    /**
     * get all users from data base
     *
     * @return {@link User} array
     * @throws DAOException when get an exception during execution
     */
    User[] getUsers() throws DAOException;

    /**
     * get all users from with organisation id param
     *
     * @param organisation organisation id
     * @return {@link User} array
     * @throws DAOException when get an exception during execution
     */
    User[] getUsers(int organisation) throws DAOException;

    /**
     * @param id user id
     * @return {@link User} with param id
     * @throws DAOException when get an exception during execution
     */
    User getUser(int id) throws DAOException;

    /**
     * @param login user login in jdbc
     * @return {@link User} with param login
     * @throws DAOException when get an exception during execution
     */
    User getUser(String login) throws DAOException;


    /**
     * @param id   user id in jdbc
     * @param info new  user info in jdbc
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setInfo(int id, String info) throws DAOException;

    /**
     * @param id   user id in jdbc
     * @param name new user name
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setName(int id, String name) throws DAOException;

    /**
     * @param id      user id in jdbc
     * @param surname new  user surname
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setSurname(int id, String surname) throws DAOException;

    /**
     * @param id      user id in jdbc
     * @param role new  user role
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setRole(int id, int role) throws DAOException;


}
