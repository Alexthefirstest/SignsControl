package by.epam.rolesOrganisationsUsersController.dao.impl;//package organisationsRolesUsersController.dao.impl;

import by.epam.rolesOrganisationsUsersController.bean.FactoryType;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * Roles table jdbc handler
 *
 * @author Bulgak Alexander
 */
public class RolesController implements IRolesController {

    /**
     * log4j2 logger
     */
    private static Logger logger = LogManager.getLogger(RolesController.class);

    /**
     * sql role insert request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_INSERT = "INSERT INTO `organisation_roles` (`role`) VALUES (?);";

    /**
     * sql delete role  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_DELETE = "DELETE FROM `road_signs_control`.`organisation_roles` WHERE (`id` = ?);";

    /**
     * sql role select use last insert id  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT * FROM `organisation_roles` where id=LAST_INSERT_ID();";

    /**
     * sql role select by id  request
     */
    private static final String SQL_SELECT_USE_ID = "SELECT * FROM `organisation_roles` where id=";

    /**
     * sql select all roles request
     */
    private static final String SQL_SELECT_ALL = "SELECT * FROM `organisation_roles` order by role";

    /**
     * sql select role name by id  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_NAME = "UPDATE `organisation_roles` SET `role` = ? WHERE (`id` = ?);";

    /**
     * sql add role
     *
     * @param name role name
     * @return created object
     * @throws DAOValidationException in case of role with this name already exist
     * @throws DAOException           when other exception occurred
     * @see RequestExecutor#createField(String, String, FactoryType, String...)
     */
    @Override
    public Role addRole(String name) throws DAOException {

        try {

            return (Role) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new Role(), name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("add fail, duplicate entry: name: " + name, ex);
                throw new DAOValidationException("this role already exist");
            }

            throw new DAOException(ex);

        }
    }

    /**
     * delete role from jdbc table
     *
     * @param id role id
     * @return true if deleted successfully or false in other case
     * @throws DAOException when other exception occurred
     * @see RequestExecutor#createField(String, String, FactoryType, int...)
     */
    @Override
    public boolean deleteRole(int id) throws DAOException {

        try {

            RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_ID + id, new Role(), id);
            return false;
        } catch (DAOValidationException ex) {
            return true;
        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get roles from jdbc
     *
     * @return array of Roles or empty orray if can't find roles
     * @throws DAOException when other exception occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Role[] getRoles() throws DAOException {

        try {

            return (Role[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new Role());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * set role name
     *
     * @param id   role id
     * @param name new role name
     * @return true if ok or else if no
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#setField(String, int, String)
     */
    @Override
    public boolean setName(int id, String name) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Column 'role' cannot be null.*", ex.getMessage()))) {
                logger.info("Column 'role' cannot be null: name: " + name);
                throw new DAOValidationException("Column 'role' cannot be null: name: " + name);
            }

            throw new DAOException(ex);

        }
    }
}
