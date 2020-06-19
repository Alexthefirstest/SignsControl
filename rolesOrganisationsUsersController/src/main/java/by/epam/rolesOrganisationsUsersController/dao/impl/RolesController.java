package by.epam.rolesOrganisationsUsersController.dao.impl;//package organisationsRolesUsersController.dao.impl;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class RolesController implements IRolesController {

    private static Logger logger = LogManager.getLogger(RolesController.class);

    private static final String SQL_INSERT = "INSERT INTO `organisation_roles` (`role`) VALUES (?);";
    private static final String SQL_DELETE = "DELETE FROM `road_signs_control`.`organisation_roles` WHERE (`id` = ?);";
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT * FROM `organisation_roles` where id=LAST_INSERT_ID();";
    private static final String SQL_SELECT_USE_ID = "SELECT * FROM `organisation_roles` where id=";
    private static final String SQL_SELECT_ALL = "SELECT * FROM `organisation_roles` order by role";
    private static final String SQL_SET_NAME = "UPDATE `organisation_roles` SET `role` = ? WHERE (`id` = ?);";

    @Override
    public Role addRole(String name) throws DAOException {
        try {

            return (Role) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new Role(), name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: name: " + name, ex);
                throw new DAOValidationException("this role already exist");
            }

            logger.warn("add fail, name: " + name, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean deleteRole(int id) throws DAOException {
        try {

            RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_ID + id, new Role(), id);
            return false;
        } catch (DAOValidationException ex) {
            return true;
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
