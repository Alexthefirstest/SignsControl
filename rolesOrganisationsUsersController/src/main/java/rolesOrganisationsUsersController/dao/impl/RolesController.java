package rolesOrganisationsUsersController.dao.impl;//package organisationsRolesUsersController.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rolesOrganisationsUsersController.bean.Role;
import rolesOrganisationsUsersController.dao.IRolesController;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class RolesController implements IRolesController {

    private static Logger logger = LogManager.getLogger(RolesController.class);

    private static final String SQL_INSERT = "INSERT INTO `organisation_roles` (`role`) VALUES (?);";
    private static final String SQL_DELETE = "DELETE FROM `road_signs_control`.`organisation_roles` WHERE (`id` = ?);";
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT * FROM `organisation_roles` where id=LAST_INSERT_ID();";
    private static final String SQL_SELECT_ALL = "SELECT * FROM `organisation_roles` order by role";
    private static final String SQL_SET_NAME = "UPDATE `organisation_roles` SET `role` = ? WHERE (`id` = ?);";

    @Override
    public Role addRole(String name) throws DAOException {
        try {

            return (Role) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new Role(), name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: name: " + name, ex);
                return null;
            }

            logger.warn("add fail, name: " + name, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public Role deleteRole(int id) throws DAOException {
        try {

            return (Role) RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_LAST_INSERT_ID, new Role(), id);

        } catch (SQLException ex) {

            logger.warn("delete fail: name: " + id, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public Role[] getRoles() throws DAOException {
        try {

            return (Role[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new Role());

        } catch (SQLException ex) {

            logger.warn("select all fail,  name: ");
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setName(int id, String name) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Column 'role' cannot be null.*", ex.getMessage()))) {
                logger.info("Column 'role' cannot be null: name: " + name);
                throw new DAOValidationException("Column 'role' cannot be null: name: " + name);
            }

            logger.warn("updateSection sign fail: id: " + id, ex);
            throw new DAOException(ex);

        }
    }
}
