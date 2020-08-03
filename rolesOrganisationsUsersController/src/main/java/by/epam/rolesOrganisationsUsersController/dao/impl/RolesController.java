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

//    /**
//     * sql role insert request for {@link java.sql.PreparedStatement}
//     */
//    private static final String SQL_INSERT = "INSERT INTO `organisation_roles` (`role`) VALUES (?);";
//
//    /**
//     * sql delete role  request for {@link java.sql.PreparedStatement}
//     */
//    private static final String SQL_DELETE = "DELETE FROM `road_signs_control`.`organisation_roles` WHERE (`id` = ?);";


//    /**
//     * sql role select by id  request
//     */
//    private static final String SQL_SELECT_USE_ID = "SELECT * FROM `organisation_roles` where id=";

    /**
     * sql organisation role select use last insert id  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID_ORGANISATION = "SELECT * FROM `organisation_roles` where id=LAST_INSERT_ID();";

    /**
     * sql user role select use last insert id  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID_USER = "SELECT * FROM `user_roles` where id=LAST_INSERT_ID();";

    /**
     * sql organisation select all roles request
     */
    private static final String SQL_SELECT_ORGANISATION_ALL = "SELECT * FROM `organisation_roles` order by role";

    /**
     * sql user select all roles request
     */
    private static final String SQL_SELECT_USER_ALL = "SELECT * FROM `user_roles` order by role";

    /**
     * sql user select role by id request
     */
    private static final String SQL_SELECT_USER_ROLE_BY_ID = "SELECT * FROM `user_roles` order by role where id=?";

    /**
     * sql user select roles besides role with id
     */
    private static final String SQL_SELECT_USER_ROLES_BESIDE = "SELECT * FROM `user_roles` order by role where id!=?";

    /**
     * sql select organisation role name by id  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_NAME_ORGANISATION = "UPDATE `organisation_roles` SET `role` = ? WHERE (`id` = ?);";

    /**
     * sql select user role name by id  request for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_NAME_USER = "UPDATE `user_roles` SET `role` = ? WHERE (`id` = ?);";

//    /**
//     * sql add role
//     *
//     * @param name role name
//     * @return created object
//     * @throws DAOValidationException in case of role with this name already exist
//     * @throws DAOException           when other exception occurred
//     * @see RequestExecutor#createField(String, String, FactoryType, String...)
//     */
//    @Override
//    public Role addRole(String name) throws DAOException {
//
//        try {
//
//            return (Role) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new Role(), name);
//
//        } catch (SQLException ex) {
//
//            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
//                logger.info("add fail, duplicate entry: name: " + name, ex);
//                throw new DAOValidationException("this role already exist");
//            }
//
//            throw new DAOException(ex);
//
//        }
//    }
//
//    /**
//     * delete role from jdbc table
//     *
//     * @param id role id
//     * @return true if deleted successfully or false in other case
//     * @throws DAOException when other exception occurred
//     * @see RequestExecutor#createField(String, String, FactoryType, int...)
//     */
//    @Override
//    public boolean deleteRole(int id) throws DAOException {
//
//        try {
//
//            RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_ID + id, new Role(), id);
//            return false;
//        } catch (DAOValidationException ex) {
//            return true;
//        } catch (SQLException ex) {
//
//            throw new DAOException(ex);
//
//        }
//    }

    /**
     * get organisation roles from jdbc
     *
     * @return array of Roles or empty orray if can't find roles
     * @throws DAOException when other exception occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Role[] getOrganisationsRoles() throws DAOException {

        try {

            return (Role[]) RequestExecutor.getSignsStaff(SQL_SELECT_ORGANISATION_ALL, new Role());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * set organisation role name
     *
     * @param id   role id
     * @param name new role name
     * @return true if ok or else if no
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#setField(String, int, String)
     */
    @Override
    public boolean setOrganisationRoleName(int id, String name) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_NAME_ORGANISATION, id, name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Column 'role' cannot be null.*", ex.getMessage()))) {
                logger.info("Column 'role' cannot be null: name: " + name);
                throw new DAOValidationException("Column 'role' cannot be null: name: " + name);
            }

            throw new DAOException(ex);

        }
    }

    /**
     * get ousers roles from jdbc
     *
     * @return array of Roles or empty orray if can't find roles
     * @throws DAOException when other exception occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Role[] getUsersRoles() throws DAOException {

        try {

            return (Role[]) RequestExecutor.getSignsStaff(SQL_SELECT_USER_ALL, new Role());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get users role from jdbc with id like first element of arra or empty array
     *
     * @param id role to find
     * @return array of Roles or empty orray if can't find role
     * @throws DAOException when other exception occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Role[] getUsersRole(int id) throws DAOException {
        try {

            return (Role[]) RequestExecutor.getSignsStaff(SQL_SELECT_USER_ROLE_BY_ID, new Role(), id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get users roles from jdbc besides id param
     * @param id role to not show
     * @return array of Roles or empty orray if can't find roles
     * @throws DAOException when other exception occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Role[] getUsersRolesBeside(int id) throws DAOException {
        try {

            return (Role[]) RequestExecutor.getSignsStaff(SQL_SELECT_USER_ROLES_BESIDE, new Role(), id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * set user role name
     *
     * @param id   role id
     * @param name new role name
     * @return true if ok or else if no
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#setField(String, int, String)
     */
    @Override
    public boolean setUserRoleName(int id, String name) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_NAME_USER, id, name);

        } catch (SQLException ex) {

            if ((Pattern.matches("Column 'role' cannot be null.*", ex.getMessage()))) {
                logger.info("Column 'role' cannot be null: name: " + name);
                throw new DAOValidationException("Column 'role' cannot be null: name: " + name);
            }

            throw new DAOException(ex);

        }
    }
}
