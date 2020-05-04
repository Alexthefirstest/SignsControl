package rolesOrganisationsUsersController.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rolesOrganisationsUsersController.bean.Organisation;
import rolesOrganisationsUsersController.dao.IOrganisationsController;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class OrganisationsController implements IOrganisationsController {

    private static Logger logger = LogManager.getLogger(OrganisationsController.class);

    private static final String SQL_INSERT = "INSERT INTO `organisations` (`name`, role) VALUES (?,?);";
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id where org.id=LAST_INSERT_ID();";
    private static final String SQL_SET_NAME = "UPDATE `organisations` SET `name` = ? WHERE (`id` = ?);";
    private static final String SQL_SET_ROLE = "UPDATE `organisations` SET role = ? WHERE (`id` = ?);";
    private static final String SQL_SET_INFO = "UPDATE `organisations` SET info = ? WHERE (`id` = ?);";
    private static final String SQL_SET_BLOCK = "UPDATE `organisations` SET is_blocked = ? WHERE (`id` = ?);";
    private static final String SQL_GET_INFO = "SELECT info FROM `organisations` WHERE (`id` = ?);";
    private static final String SQL_GET_BLOCK = "SELECT is_blocked FROM `organisations` WHERE (`id` = ?);";
    private static final String SQL_GET_ROLE = "SELECT role FROM `organisations` WHERE (`id` = ?);";
    private static final String SQL_SELECT_ALL = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id order by org.name;";

    @Override
    public Organisation addOrganisation(String name, int role) throws DAOException {
        try {

            return (Organisation) RequestExecutor.createFieldUseDifferentParam(SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new Organisation(), name, role);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: name: " + name, ex);
                throw new DAOValidationException("this organisation are already exist");
            }
            if ((Pattern.matches("No value specified for parameter 1.*", ex.getMessage()))) {
                logger.info("add fail, no parameters specified: name: " + name);
                throw new DAOValidationException("name can't be null, name:" + name);
            }
            if ((Pattern.matches(".*a foreign key constraint fails.*", ex.getMessage()))) {
                logger.info("add fail, wrong role:  " + role);
                throw new DAOValidationException("add fail, wrong role:  " + role);
            }

            logger.warn("add fail, name, role: " + name + ", " + role, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setName(int id, String name) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("set name fail, no parameters specified: name: " + name);
                throw new DAOValidationException("name can't be null, name:" + name);
            }

            logger.warn("set name fail: " + name, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setRole(int id, int role) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_ROLE, id, role);

        } catch (SQLException ex) {

            logger.warn("set role fail: " + role, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public int getRole(int id) throws DAOException {
        try {

            return RequestExecutor.getInt(SQL_GET_ROLE, id);

        } catch (SQLException ex) {

            logger.warn("get role fail, id: " + id, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setInfo(int id, String info) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_INFO, id, info);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("set info fail, no parameters specified: info: " + info);
                throw new DAOValidationException("info can't be null, info:" + info);
            }

            logger.warn("set info fail: " + info, ex);
            throw new DAOException(ex);

        }

    }

    @Override
    public String getInfo(int id) throws DAOException {
        try {

            return RequestExecutor.getString(SQL_GET_INFO, id);

        } catch (SQLException ex) {

            logger.warn("get info fail, id: " + id, ex);
            throw new DAOException(ex);

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
            throw ex;
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


    @Override
    public Organisation[] getOrganisations() throws DAOException {
        try {

            return (Organisation[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new Organisation());

        } catch (SQLException ex) {

            logger.warn("select all fail,  name: ");
            throw new DAOException(ex);

        }
    }
}
