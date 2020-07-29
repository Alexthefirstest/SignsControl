package by.epam.signsControl.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.exceptions.ConnectionPoolException;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import by.epam.signsControl.bean.FactoryType;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jsbc communication class
 */
public class PDDSignsControl implements IPDDSignsControl {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(PDDSignsControl.class);

    /**
     * connection pool instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * select use last inserted id
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT * FROM `pdd_signs` where id=LAST_INSERT_ID();";

    /**
     * insert with unique check
     */
    private static final String SQL_INSERT_UNIQUE = "INSERT INTO `pdd_signs` (`pdd_section`, `pdd_sign`, `pdd_kind`, name) " +
            "SELECT * FROM (SELECT ? as se, ? as si, ? as ki, ? as na) as rt " +
            "where not exists(SELECT `pdd_section`, `pdd_sign`, `pdd_kind` FROM pdd_signs where `pdd_section`=? AND `pdd_sign`=? AND `pdd_kind`=? ) LIMIT 1";
    /**
     * insert with info and with unique check
     */
    private static final String SQL_INSERT_UNIQUE_WITH_INFO =
            "INSERT INTO `pdd_signs` (`pdd_section`, `pdd_sign`, `pdd_kind`, name, description) " +
                    "SELECT * FROM (SELECT ? as se, ? as si, ? as ki, ? as na, ? as des) as rt " +
                    "where not exists(SELECT `pdd_section`, `pdd_sign`, `pdd_kind` " +
                    "FROM pdd_signs where `pdd_section`=? AND `pdd_sign`=? AND `pdd_kind`=? ) LIMIT 1";

    /**
     * delete from table
     */
    private static final String SQL_DELETE = "DELETE FROM `pdd_signs` WHERE (`id` = ?);";

    /**
     * find by id
     */
    private static final String SQL_FIND_BY_ID = "SELECT * FROM pdd_signs where id=";

    /**
     * set section
     */
    private static final String SQL_SET_SECTION = "UPDATE `pdd_signs` SET `pdd_section` = ? WHERE (`id` = ?);";

    /**
     * set sign number of Sign
     */
    private static final String SQL_SET_SIGN = "UPDATE `pdd_signs` SET `pdd_sign` = ? WHERE (`id` = ?);";

    /**
     * set kind
     */
    private static final String SQL_SET_KIND = "UPDATE `pdd_signs` SET `pdd_kind` = ? WHERE (`id` = ?);";

    /**
     * set name
     */
    private static final String SQL_SET_NAME = "UPDATE `pdd_signs` SET `name` = ? WHERE (`id` = ?);";

    /**
     * set description
     */
    private static final String SQL_SET_DESCRIPTION = "UPDATE `pdd_signs` SET `description` = ? WHERE (`id` = ?);";

    /**
     * select all
     */
    private static final String SQL_SELECT_ALL = "SELECT * FROM pdd_signs order by pdd_section, pdd_sign, pdd_kind";

    /**
     * select all by section
     */
    private static final String SQL_SELECT_BY_SECTION = "SELECT * FROM pdd_signs WHERE pdd_section=? order by pdd_section, pdd_sign, pdd_kind";

    /**
     * select all by section and sign
     */
    private static final String SQL_SELECT_BY_SECTION_AND_SIGN = "SELECT * FROM pdd_signs WHERE pdd_section=? AND pdd_sign=? order by pdd_section, pdd_sign, pdd_kind";

    /**
     * insert picture
     */
    private static final String SQL_INSERT_PICTURE = "UPDATE `pdd_signs` SET `picture` = ? WHERE (`id` = ?);";

    /**
     * select picture
     */
    private static final String SQL_SELECT_PICTURE = "SELECT picture from pdd_signs where id=?";

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param kind    sign kind
     * @param name    sign name
     * @return object if success or null if not
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldWithExistingCheck(String, String, FactoryType, Object...)}
     */
    @Override
    public Sign addSign(int section, int number, int kind, String name) throws DAOException {

        try {

            return (Sign) RequestExecutor.createFieldWithExistingCheck
                    (SQL_INSERT_UNIQUE, SQL_SELECT_USE_LAST_INSERT_ID, new Sign(), section, number, kind, name, section, number, kind);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }

    }

    /**
     * add sign to table
     *
     * @param section     sign section
     * @param number      sign number
     * @param kind        sign kind
     * @param name        sign name
     * @param description sign description
     * @return object if success or null if not
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldWithExistingCheck(String, String, FactoryType, Object...)}
     */
    @Override
    public Sign addSign(int section, int number, int kind, String name, String description) throws DAOException {

        try {

            return (Sign) RequestExecutor.createFieldWithExistingCheck
                    (SQL_INSERT_UNIQUE_WITH_INFO, SQL_SELECT_USE_LAST_INSERT_ID, new Sign(),
                            section, number, kind, name, description, section, number, kind);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }

    }

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param name    sign name
     * @return object if success or null if not
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldWithExistingCheck(String, String, FactoryType, Object...)}
     */
    @Override
    public Sign addSign(int section, int number, String name) throws DAOException {
        return addSign(section, number, -1, name);
    }

    /**
     * remove sign from table
     *
     * @param id sign id to remove
     * @return null if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public boolean removeSign(int id) throws DAOException {
        try {

            RequestExecutor.createField
                    (SQL_DELETE, SQL_FIND_BY_ID + id, new Sign(), id);
            return false;
        } catch (DAOValidationException e) {
            return true;
        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * @param id      sign id where set
     * @param section section to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */
    @Override
    public boolean updateSection(int id, int section) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_SECTION, id, section);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * @param id     sign id where set
     * @param number of sign in section to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */
    @Override
    public boolean updateNumber(int id, int number) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_SIGN, id, number);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }

    }

    /**
     * @param id   sign id where set
     * @param kind to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */
    @Override
    public boolean updateKind(int id, int kind) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_KIND, id, kind);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * @param id   sign id where set
     * @param name to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean updateName(int id, String name) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_NAME, id, name);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * @param id   sign id where set
     * @param info to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean updateDescription(int id, String info) throws DAOException {
        try {

            return RequestExecutor.setField(SQL_SET_DESCRIPTION, id, info);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * @param id          sign id where set
     * @param inputStream of picture to set
     * @return true if success or false in other case
     * @throws DAOException when catch  {@link SQLException} from {@link PreparedStatement}
     */
    @Override
    public boolean setPicture(int id, InputStream inputStream, long imageSize) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PICTURE)) {

            ps.setBlob(1, inputStream, imageSize);

            ps.setInt(2, id);

            return ps.executeUpdate() == 0 ? false : true;

        } catch (SQLException ex) {

            throw new DAOException(ex);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /**
     * @param id sign id where get
     * @return picture of sign if success or null
     * @throws DAOException when catch  {@link SQLException} from {@link PreparedStatement} or{@link ResultSet}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public byte[] getPicture(int id) throws DAOException {

        Connection connection;

        try {
            connection = CONNECTION_POOL.retrieveConnection();
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex.getMessage());
        }

        ResultSet rs = null;

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_PICTURE)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBytes(1);
            }

            return null;

        } catch (SQLException ex) {

            throw new DAOException(ex);

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn("return picture rs close fail ", ex);
                }
            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    /*get signs from jdbc*/
    private Sign[] getPddSignsDef(String sql, int... parameters) throws DAOException {
        try {

            return (Sign[]) RequestExecutor.getSignsStaff(sql, new Sign(), parameters);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get all signs
     *
     * @return {@link Sign} array
     * @throws DAOException when catch  exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, int...)}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Sign[] getPddSigns() throws DAOException {

        return getPddSignsDef(SQL_SELECT_ALL);
    }

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @return {@link Sign} array
     * @throws DAOException when catch  exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, int...)}
     */
    @Override
    public Sign[] getPddSigns(int section) throws DAOException {
        return getPddSignsDef(SQL_SELECT_BY_SECTION, section);
    }

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @param number  to find signs
     * @return {@link Sign} array
     * @throws DAOException when catch  exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, int...)}
     */
    @Override
    public Sign[] getPddSigns(int section, int number) throws DAOException {
        return getPddSignsDef(SQL_SELECT_BY_SECTION_AND_SIGN, section, number);
    }


}
