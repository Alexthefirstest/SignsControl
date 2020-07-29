package by.epam.rolesOrganisationsUsersController.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import by.epam.rolesOrganisationsUsersController.bean.FactoryType;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.dao.IUsersController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.dao.impl.factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * operations with users table from mysql
 *
 * @author Bulgak Alexander
 */
public class UsersController implements IUsersController {

    /**
     * {@link IConnectionPool} instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * DAO factory instance
     */
    private static final Factory signsControlFactory = Factory.getINSTANCE();

    /**
     * logger log4j2
     */
    private static final Logger logger = LogManager.getLogger(UsersController.class);

    /**
     * sql insert user to db table
     */
    private static final String SQL_INSERT_USER_INFO = "INSERT INTO `user_info` (`name`, `surname`) VALUES (?, ?);";

    /**
     * sql insert user to db table
     */
    private static final String SQL_INSERT_USER = "INSERT INTO `users` (`id`, `role`, `organisation`, `login`, `password`) VALUES ((SELECT id FROM user_info where id=LAST_INSERT_ID()),?, ?, ?, ?)";

    /**
     * sql select user with last inserted id from db
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked, ui.name, ui.surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation join user_roles as ur on u.role=ur.id join organisation_roles as orr on o.role=orr.id where u.id=LAST_INSERT_ID();";

    /**
     * sql select all users from db
     */
    private static final String SQL_SELECT_ALL = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked, ui.name, ui.surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation join user_roles as ur on u.role=ur.id join organisation_roles as orr on o.role=orr.id order by ui.surname, ui.name ";

    /**
     * sql select users by organisation from db
     */
    private static final String SQL_SELECT_USE_ORGANISATION =
            "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked, ui.name, ui.surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation join user_roles as ur on u.role=ur.id join organisation_roles as orr on o.role=orr.id where u.organisation=? order by ui.surname, ui.name  ";

    /**
     * sql select user by id from db
     */
    private static final String SQL_SELECT_USER = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked, ui.name, ui.surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation join user_roles as ur on u.role=ur.id join organisation_roles as orr on o.role=orr.id  where u.id=?";

    /**
     * sql select user by login from db
     */
    private static final String SQL_SELECT_USER_LOGIN = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked, ui.name, ui.surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation join user_roles as ur on u.role=ur.id join organisation_roles as orr on o.role=orr.id  where u.login=?";
    //private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT u.id, login, o.id as orgID, o.name as org, u.is_blocked, ui.name, surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation  where u.login=? AND u.password=?";

    /**
     * sql get user block condition
     */
    private static final String SQL_GET_BLOCK = "SELECT is_blocked FROM users WHERE (`id` = ?);";

    /**
     * sql set user block condition
     */
    private static final String SQL_SET_BLOCK = "UPDATE `users` SET `is_blocked` = ? WHERE (`id` = ?);";

    /**
     * sql select user info from db
     */
    private static final String SQL_SET_INFO = "UPDATE `user_info` SET info = ? WHERE (`id` = ?);";

    /**
     * sql set organisation for user
     */
    private static final String SQL_SET_ORGANISATION = "UPDATE users SET `organisation` = ? WHERE (`id` = ?);";    /**

     /**
     * sql set organisation for user
     */
    private static final String SQL_SET_ROLE = "UPDATE users SET `role` = ? WHERE (`id` = ?);";

//    /**
//     * sql get user organisation from db
//     */
//    private static final String SQL_GET_ORGANISATION = "SELECT organisation FROM users WHERE (`id` = ?);";

    /**
     * sql set name of user to db
     */
    private static final String SQL_SET_NAME = "UPDATE `user_info` SET name = ? WHERE (`id` = ?);";

    /**
     * sql set surname of user
     */
    private static final String SQL_SET_SURNAME = "UPDATE `user_info` SET surname = ? WHERE (`id` = ?);";
    // private static final String SQL_SET_LOGIN = "UPDATE users SET login = if(?=login AND ?=password, ?, null) where id=?";

    /**
     * sql set user login
     */
    private static final String SQL_SET_LOGIN = "UPDATE users SET login = ? where id=?";
    //private static final String SQL_SET_PASSWORD = "UPDATE users SET password = if(?=login AND ?=password, ?, null) where id=?";
    /**
     * sql set user password
     */
    private static final String SQL_SET_PASSWORD = "UPDATE users SET password = ? where id=?";

    /**
     * sql get user password
     */
    private static final String SQL_GET_PASSWORD = "SELECT password FROM users WHERE login=?";

    /**
     * add user to jdbc table
     *
     * @param login        user login
     * @param password     user password
     * @param organisation user organisation
     * @param name         user name
     * @param surname      user surname
     * @return {@link User} if success
     * @throws DAOValidationException when user with this login are already exist,
     *                                when wrong organisation id, when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     * @throws DAOException           when connection pool throw exception when other exceptions during process occurred
     */
    @Override
    public User addUser(String login, String password, int role, int organisation, String name, String surname) throws DAOException {

        ResultSet rs = null;

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        }catch (ConnectionPoolException ex){
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement psInsertUserInfo = connection.prepareStatement(SQL_INSERT_USER_INFO);
             PreparedStatement psInsertUser = connection.prepareStatement(SQL_INSERT_USER);
             PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_USE_LAST_INSERT_ID)) {

            connection.setAutoCommit(false);

            psInsertUserInfo.setString(1, name);
            psInsertUserInfo.setString(2, surname);

            psInsertUserInfo.executeUpdate();

            psInsertUser.setInt(1, role);
            psInsertUser.setInt(2, organisation);
            psInsertUser.setString(3, login);
            psInsertUser.setString(4, password);

            psInsertUser.executeUpdate();

            rs = psSelect.executeQuery();

            return (User) signsControlFactory.createSignStaff(rs, new User());


        } catch (SQLException ex) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.warn("rollBackFail", ex);
            }

            if ((Pattern.matches("Duplicate entry.+?login.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: login: " + name, ex);
                throw new DAOValidationException("duplicate login: " + login);
            }

            if ((Pattern.matches(".*a foreign key constraint fails.*", ex.getMessage()))) {
                logger.info("add fail, wrong organisation or role: " + organisation, ex);
                throw new DAOValidationException("wrong organisation: " + organisation);
            }


            throw new DAOException(ex);

        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.info(e);
            }

            RequestExecutor.closeResultSetAndReturnConnection(rs, connection);
        }
    }

    /**
     * @param id user id in jdbc
     * @return block condition
     * @throws DAOValidationException when user with this id do not exist
     * @throws DAOException           when other exceptions during process occurred
     * @see RequestExecutor#getBoolean(String, int)
     */
    @Override
    public boolean getBlock(int id) throws DAOException {
        try {

            return RequestExecutor.getBoolean(SQL_GET_BLOCK, id);

        } catch (SQLException ex) {

            logger.warn("get boolean fail, id: " + id, ex);
            throw new DAOException(ex);

        } catch (DAOValidationException ex) {
            throw new DAOValidationException("wrong id: " + id);
        }
    }

    /**
     * set block in users table
     *
     * @param id    user id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     * @see RequestExecutor#setField(String, int, boolean)
     */
    @Override
    public boolean setBlock(int id, boolean block) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_BLOCK, id, block);

        } catch (SQLException ex) {

            logger.warn("set block fail: " + block, ex);
            throw new DAOException(ex);

        }
    }

    /**
     * set user login
     *
     * @param id    user id in jdbc
     * @param login new user login
     * @return true if success, false - if no
     * @throws DAOValidationException if user with this login is already exist
     * @throws DAOException           when get an exception during execution
     */
    @Override
    public boolean setLogin(int id, String login) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_LOGIN, id, login);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.+?login.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: login: " + login, ex);
                throw new DAOValidationException("duplicate login: " + login);
            }

            logger.warn("set login fail: " + login, ex);
            throw new DAOException(ex);

        }
    }

    /**
     * @param id       user id in jdbc
     * @param password new user password
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     * @see RequestExecutor#setField(String, int, String)
     */
    @Override
    public boolean setPassword(int id, String password) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_PASSWORD, id, password);

        } catch (SQLException ex) {

            logger.warn("set password fail: " + password, ex);
            throw new DAOException(ex);

        }
    }

    /**
     * @param login user login
     * @return user password or null if can't find user with this login
     * @throws DAOException when get an exception during execution
     */
    @Override
    public String getPassword(String login) throws DAOException {
        try {

            return RequestExecutor.getString(SQL_GET_PASSWORD, login);

        } catch (SQLException ex) {

            logger.warn("get password fail, id: ", ex);
            throw new DAOException(ex);

        }
    }

    /**
     * @param id           user id
     * @param organisation new organisation id
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    @Override
    public boolean setOrganisation(int id, int organisation) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_ORGANISATION, id, organisation);

        } catch (SQLException ex) {

            logger.warn("set organisation fail: " + organisation, ex);
            throw new DAOException(ex);

        }
    }

//    /**
//     * @param userId user id
//     * @return organisation id or -1 if can't find it
//     * @throws DAOException when get an exception during execution
//     */
//    @Override
//    public int getOrganisation(int userId) throws DAOException {
//        try {
//
//            return RequestExecutor.getInt(SQL_GET_ORGANISATION, userId);
//
//        } catch (SQLException ex) {
//
//            logger.warn("get organisation fail, id: " + userId, ex);
//            throw new DAOException(ex);
//
//        }
//    }

    /**
     * get all users from data base
     *
     * @return {@link User} array
     * @throws DAOException when get an exception during execution
     */
    @Override
    public User[] getUsers() throws DAOException {
        try {

            return (User[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new User());

        } catch (SQLException ex) {

            logger.warn("select all fail");
            throw new DAOException(ex);

        }
    }

    /**
     * get all users from with organisation id param
     *
     * @param organisation organisation id
     * @return {@link User} array
     * @throws DAOException when get an exception during execution
     */
    @Override
    public User[] getUsers(int organisation) throws DAOException {
        try {

            return (User[]) RequestExecutor.getSignsStaff(SQL_SELECT_USE_ORGANISATION, new User(), organisation);

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    /**
     * @param id user id
     * @return {@link User} with param id
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     * @throws DAOException           when get an exception during execution
     */
    @Override
    public User getUser(int id) throws DAOException {
        try {


            return (User) RequestExecutor.getOneSignsStaff(SQL_SELECT_USER, new User(), id);
        } catch (SQLException ex) {

            logger.warn("select user fail, ");
            throw new DAOException(ex);

        }
    }

    /**
     * @param login user login in jdbc
     * @return {@link User} with param login
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     * @throws DAOException           when get an exception during execution
     */
    @Override
    public User getUser(String login) throws DAOException {
        try {


            return (User) RequestExecutor.getOneSignsStaff(SQL_SELECT_USER_LOGIN, new User(), login);
        } catch (SQLException ex) {

            logger.warn("select user fail, ");
            throw new DAOException(ex);

        }
    }

    /**
     * @param id   user id in jdbc
     * @param info new  user info in jdbc
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    @Override
    public boolean setInfo(int id, String info) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_INFO, id, info);

        } catch (SQLException ex) {

            logger.warn("set info fail: " + info, ex);
            throw new DAOException(ex);

        }
    }

    /**
     * @param id   user id in jdbc
     * @param name new user name
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    @Override
    public boolean setName(int id, String name) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            logger.warn("set info name: " + name, ex);
            throw new DAOException(ex);

        }
    }

    /**
     * @param id      user id in jdbc
     * @param surname new  user surname
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    @Override
    public boolean setSurname(int id, String surname) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_SURNAME, id, surname);

        } catch (SQLException ex) {

            logger.warn("set info surname: " + surname, ex);
            throw new DAOException(ex);

        }
    }

    /**
     * @param id      user id in jdbc
     * @param role new  user role
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    @Override
    public boolean setRole(int id, int role) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_ROLE, id, role);

        } catch (SQLException ex) {

            logger.warn("set role fail: " + role, ex);
            throw new DAOException(ex);

        }
    }
}
