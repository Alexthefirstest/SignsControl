package rolesOrganisationsUsersController.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rolesOrganisationsUsersController.bean.User;
import rolesOrganisationsUsersController.dao.IUsersController;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import rolesOrganisationsUsersController.dao.impl.factory.Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UsersController implements IUsersController {

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();
    private static final Factory signsControlFactory = Factory.getINSTANCE();
    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private static final String SQL_INSERT_USER_INFO = "INSERT INTO `user_info` (`name`, `surname`) VALUES (?, ?);";
    private static final String SQL_INSERT_USER = "INSERT INTO `users` (`id`, `organisation`, `login`, `password`) VALUES ((SELECT id FROM user_info where id=LAST_INSERT_ID()), ?, ?, ?)";
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT u.id, login, o.id as orgID, o.name as org, u.is_blocked, ui.name, surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation where u.id=LAST_INSERT_ID();";
    private static final String SQL_SELECT_ALL = "SELECT u.id, login, o.id as orgID, o.name as org, u.is_blocked, ui.name, surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation order by u.organisation, ui.surname, ui.name";
    private static final String SQL_SELECT_USE_ORGANISATION = "SELECT u.id, login, o.id as orgID, o.name as org, u.is_blocked, ui.name, surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation where u.organisation=? order by ui.surname, ui.name";
    private static final String SQL_SELECT_USER = "SELECT u.id, login, o.id as orgID, o.name as org, u.is_blocked, ui.name, surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation  where u.id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT u.id, login, o.id as orgID, o.name as org, u.is_blocked, ui.name, surname, ui.info FROM users as u join user_info as ui on u.id=ui.id join organisations as o on o.id=u.organisation  where u.login=? AND u.password=?";

    private static final String SQL_GET_BLOCK = "SELECT is_blocked FROM users WHERE (`id` = ?);";

    private static final String SQL_SET_BLOCK = "UPDATE `users` SET `is_blocked` = ? WHERE (`id` = ?);";

    private static final String SQL_SET_INFO = "UPDATE `user_info` SET info = ? WHERE (`id` = ?);";
    private static final String SQL_SET_ORGANISATION = "UPDATE users SET `is_blocked` = ? WHERE (`id` = ?);";
    private static final String SQL_GET_ORGANISATION = "SELECT organisation FROM users WHERE (`id` = ?);";
    private static final String SQL_SET_NAME = "UPDATE `user_info` SET name = ? WHERE (`id` = ?);";
    private static final String SQL_SET_SURNAME = "UPDATE `user_info` SET surname = ? WHERE (`id` = ?);";
    private static final String SQL_SET_LOGIN = "UPDATE users SET login = if(?=login AND ?=password, ?, null) where id=?";
    private static final String SQL_SET_PASSWORD = "UPDATE users SET password = if(?=login AND ?=password, ?, null) where id=?";


    @Override
    public User addUser(String login, String password, int organisation, String name, String surname) throws DAOException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement psInsertUserInfo = connection.prepareStatement(SQL_INSERT_USER_INFO);
             PreparedStatement psInsertUser = connection.prepareStatement(SQL_INSERT_USER);
             PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_USE_LAST_INSERT_ID)) {

            connection.setAutoCommit(false);

            psInsertUserInfo.setString(1, name);
            psInsertUserInfo.setString(2, surname);

            psInsertUserInfo.executeUpdate();

            psInsertUser.setInt(1, organisation);
            psInsertUser.setString(2, login);
            psInsertUser.setString(3, password);

            psInsertUser.executeUpdate();

            rs = psSelect.executeQuery();

            return (User) signsControlFactory.createSignStaff(rs, new User());


        } catch (SQLException ex) {


            if ((Pattern.matches("Duplicate entry.+?login.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: login: " + name, ex);
                throw new DAOValidationException("duplicate login: " + login);
            }

            if ((Pattern.matches(".*a foreign key constraint fails.*", ex.getMessage()))) {
                logger.info("add fail, wrong organisation: " + organisation, ex);
                return null;
            }

            try {
                connection.rollback();
            } catch (SQLException eex) {
                logger.warn(eex);
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

    @Override
    public boolean setBlock(int id, boolean block) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_BLOCK, id, block);

        } catch (SQLException ex) {

            logger.warn("set block fail: " + block, ex);
            throw new DAOException(ex);

        }
    }

    private boolean setLoginOrPassword(int id, String login, String password, String newLoginOrPassword, String sql_request) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(sql_request)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, newLoginOrPassword);
            ps.setInt(4, id);

            return ps.executeUpdate() != 0;


        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("wrong login or password ");
                throw new DAOValidationException("wrong login or password");
            }
            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("user with this login already exist ");
                throw new DAOValidationException("user with this login already exist ");
            }
            logger.warn("set login or password fail: " + login);
            throw new DAOException("set login or password fail");
        }

    }

    @Override
    public User checkLoginPassword(String login, String password) throws DAOException {
        try {


            return (User) RequestExecutor.getOneSignsStaff(SQL_SELECT_USER_BY_LOGIN_PASSWORD, new User(), login, password);
        } catch (SQLException ex) {

            logger.warn("check login_password");
            throw new DAOException("check login_password");

        }
    }

    @Override
    public boolean setLogin(int id, String login, String password, String newLogin) throws DAOException {
        return setLoginOrPassword(id, login, password, newLogin, SQL_SET_LOGIN);
    }

    @Override
    public boolean setPassword(int id, String login, String password, String newPassword) throws DAOException {
        return setLoginOrPassword(id, login, password, newPassword, SQL_SET_PASSWORD);
    }

    @Override
    public boolean setOrganisation(int id, int organisation) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_ORGANISATION, id, organisation);

        } catch (SQLException ex) {

            logger.warn("set organisation fail: " + organisation, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public int getOrganisation(int userId) throws DAOException {
        try {

            return RequestExecutor.getInt(SQL_GET_ORGANISATION, userId);

        } catch (SQLException ex) {

            logger.warn("get organisation fail, id: " + userId, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public User[] getUsers() throws DAOException {
        try {

            return (User[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new User());

        } catch (SQLException ex) {

            logger.warn("select all fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public User[] getUsers(int organisation) throws DAOException {
        try {

            return (User[]) RequestExecutor.getSignsStaff(SQL_SELECT_USE_ORGANISATION, new User(), organisation);

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public User getUser(int id) throws DAOException {
        try {


            return (User) RequestExecutor.getOneSignsStaff(SQL_SELECT_USER, new User(), id);
        } catch (SQLException ex) {

            logger.warn("select user fail, ");
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setInfo(int id, String info) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_INFO, id, info);

        } catch (SQLException ex) {

            logger.warn("set info fail: " + info, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setName(int id, String name) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            logger.warn("set info name: " + name, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setSurname(int id, String surname) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_SURNAME, id, surname);

        } catch (SQLException ex) {

            logger.warn("set info surname: " + surname, ex);
            throw new DAOException(ex);

        }
    }
}
