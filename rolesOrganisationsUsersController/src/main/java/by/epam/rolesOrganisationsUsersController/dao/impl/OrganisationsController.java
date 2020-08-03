package by.epam.rolesOrganisationsUsersController.dao.impl;

import by.epam.rolesOrganisationsUsersController.bean.FactoryType;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * operations with organisation table from mysql
 *
 * @author Bulgak Alexander
 */
public class OrganisationsController implements IOrganisationsController {

    public OrganisationsController() {
    }

    /**
     * logger log4j2
     */
    private static Logger logger = LogManager.getLogger(OrganisationsController.class);

    /**
     * insert request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_INSERT = "INSERT INTO `organisations` (`name`, role) VALUES (?,?);";

    /**
     * select use last insert id request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id where org.id=LAST_INSERT_ID();";

    /**
     * set name request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_NAME = "UPDATE `organisations` SET `name` = ? WHERE (`id` = ?);";

    /**
     * set role request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_ROLE = "UPDATE `organisations` SET role = ? WHERE (`id` = ?);";

    /**
     * set info request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_INFO = "UPDATE `organisations` SET info = ? WHERE (`id` = ?);";

    /**
     * set block condition request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SET_BLOCK = "UPDATE `organisations` SET is_blocked = ? WHERE (`id` = ?);";

    /**
     * get info request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_GET_INFO = "SELECT info FROM `organisations` WHERE (`id` = ?);";

    /**
     * get block request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_GET_BLOCK = "SELECT is_blocked FROM `organisations` WHERE (`id` = ?);";

//    /**
//     * get role request in jdbc for {@link java.sql.PreparedStatement}
//     */
//    private static final String SQL_GET_ROLE = "SELECT role FROM `organisations` WHERE (`id` = ?);";

    /**
     * select request request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_ALL = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id order by org.name;";

    /**
     * select request request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_BY_ID = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id order by org.name where org.id=?";

    /**
     * select request request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_WITHOUT_ID = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id order by org.name where org.id!=?";

    /**
     * select request request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_BY_ROLE = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id where org.role=? order by org.name;";

    /**
     * select request request in jdbc for {@link java.sql.PreparedStatement}
     */
    private static final String SQL_SELECT_UNBLOCKED_BY_ROLE = "SELECT org.id, org.name, org.role, orgR.role, is_blocked," +
            " info FROM organisations as org join organisation_roles as orgR on org.role=orgR.id where org.role=? AND is_blocked=0 order by org.name;";


    /**
     * add organisation to jdbc table
     *
     * @param name organisation name
     * @param role organisation members role
     * @return {@link Organisation}
     * @throws DAOValidationException in cases of: organisation already exist, null name, role with this id doesn't exist,
     *                                object was't created for some reason
     * @throws DAOException           when other exceptions during process occurred
     * @see RequestExecutor#createFieldUseDifferentParam(String, String, FactoryType, Object...)
     */
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


            throw new DAOException(ex);

        }
    }

    /**
     * set organisation name to jdbc
     *
     * @param id   organisation id in jdbc
     * @param name new organisation name in jdbc
     * @return true if success, false if no
     * @throws DAOValidationException if name is null
     * @throws DAOException           when other exceptions during process occurred
     * @see RequestExecutor#setField(String, int, String)
     */
    @Override
    public boolean setName(int id, String name) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("set name fail, no parameters specified: name: " + name);
                throw new DAOValidationException("name can't be null, name:" + name);
            }


            throw new DAOException(ex);

        }
    }

    /**
     * set role to jdbc
     *
     * @param id   organisation id in jdbc
     * @param role new organisation members role
     * @return true if success, false if no
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#setField(String, int, int)
     */
    @Override
    public boolean setRole(int id, int role) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_ROLE, id, role);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

//    /**
//     * get role from jdbc
//     *
//     * @param id organisation id in jdbc
//     * @return role id or -1 if can't find role
//     * @throws DAOException when other exceptions during process occurred
//     * @see RequestExecutor#getInt(String, int)
//     */
//    @Override
//    public int getRole(int id) throws DAOException {
//
//        try {
//
//            return RequestExecutor.getInt(SQL_GET_ROLE, id);
//
//        } catch (SQLException ex) {
//
//            throw new DAOException(ex);
//
//        }
//    }

    /**
     * set info inside jdbc table
     *
     * @param id   organisation id in jdbc
     * @param info new organisation info in jdbc
     * @return true if success, false if no
     * @throws DAOValidationException when info is null
     * @throws DAOException           when other exceptions during process occurred
     * @see RequestExecutor#setField(String, int, String)
     */
    @Override
    public boolean setInfo(int id, String info) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_INFO, id, info);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.info("set info fail, no parameters specified: info: " + info);
                throw new DAOValidationException("info can't be null, info:" + info);
            }

            throw new DAOException(ex);

        }

    }

    /**
     * get info from jdbc table
     *
     * @param id organisation id in jdbc
     * @return info or null - if can't find info
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#getString(String, int)
     */
    @Override
    public String getInfo(int id) throws DAOException {

        try {

            return RequestExecutor.getString(SQL_GET_INFO, id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * return block condition from jdbc
     *
     * @param id organisation id in jdbc
     * @return block condition ni jdbc
     * @throws DAOValidationException if cant find this id
     * @throws DAOException           when other exceptions during process occurred
     * @see RequestExecutor#getBoolean(String, int)
     */
    @Override
    public boolean getBlock(int id) throws DAOException {

        try {

            return RequestExecutor.getBoolean(SQL_GET_BLOCK, id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * @param id    organisation id in jdbc
     * @param block new block condition
     * @throws DAOException when other exceptions during process occurred
     * @retur true if success, false if no
     * @see RequestExecutor#setField(String, int, boolean)
     */
    @Override
    public boolean setBlock(int id, boolean block) throws DAOException {

        try {

            return RequestExecutor.setField(SQL_SET_BLOCK, id, block);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get array of organisation from jdbc
     *
     * @return array of organisation or empty array when nothing was find
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Organisation[] getOrganisations() throws DAOException {

        try {

            return (Organisation[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new Organisation());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * * @param id to find
     * @return organisation like first element of array with id or empty array
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Organisation[] getOrganisation(int id) throws DAOException {
        try {

            return (Organisation[]) RequestExecutor.getSignsStaff(SQL_SELECT_BY_ID, new Organisation(), id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /*** @param id to not show
     * @return organisations array without organisation with id param
     * @throws DAOException when get an exception during execution
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Organisation[] getOrganisationsBeside(int id) throws DAOException {
        try {

            return (Organisation[]) RequestExecutor.getSignsStaff(SQL_SELECT_WITHOUT_ID, new Organisation(), id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get array of organisation from jdbc with with role id
     *
     * @param organisationRole organisation id in jdbc
     * @return array of organisation or empty array when nothing was find
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Organisation[] getOrganisations(int organisationRole) throws DAOException {

        try {

            return (Organisation[]) RequestExecutor.getSignsStaff(SQL_SELECT_BY_ROLE, new Organisation(), organisationRole);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get array of unblocked organisation from jdbc with with role id
     *
     * @param organisationRole organisation id in jdbc
     * @return array of organisation or empty array when nothing was find
     * @throws DAOException when other exceptions during process occurred
     * @see RequestExecutor#getSignsStaff(String, FactoryType, int...)
     */
    @Override
    public Organisation[] getUnblockedOrganisations(int organisationRole) throws DAOException {

        try {

            return (Organisation[]) RequestExecutor.getSignsStaff(SQL_SELECT_UNBLOCKED_BY_ROLE, new Organisation(), organisationRole);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }
}
