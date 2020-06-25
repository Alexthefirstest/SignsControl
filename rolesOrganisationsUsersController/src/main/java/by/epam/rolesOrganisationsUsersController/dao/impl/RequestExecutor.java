package by.epam.rolesOrganisationsUsersController.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.rolesOrganisationsUsersController.bean.FactoryType;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.dao.impl.factory.Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * set parameters inside requests to data base and execute it, forming results
 *
 * @author Bulgak Alexander
 */
class RequestExecutor {

    /**
     * {@link IConnectionPool} realisation from factory
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /**
     * {@link Factory}
     */
    private static final Factory signsControlFactory = Factory.getINSTANCE();

    /**
     * log4j2 logger
     */
    private static Logger logger = LogManager.getLogger(RequestExecutor.class);

    /**
     * empty constructor
     */
    private RequestExecutor() {
    }

    /*
     * set int parameters inside prepared statement request in order
     *
     * hasn't validation
     *
     */
    private static PreparedStatement intParametersInsideRequest(int[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            ps.setInt(i + 1, parameters[i]);
        }

        return ps;
    }

    /*
     * set String parameters inside prepared statement request in order
     *
     * hasn't validation
     *
     */
    private static PreparedStatement stringParametersInsideRequest(String[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            ps.setString(i + 1, parameters[i]);
        }

        return ps;
    }

    /*
     * set String and int parameters inside prepared statement request in order
     *
     * hasn't validation
     *
     */
    private static PreparedStatement differentParametersInsideRequest(Object[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {

            if (parameters[i] instanceof Integer) {
                ps.setInt(i + 1, (Integer) parameters[i]);
            }

            if (parameters[i] instanceof String) {
                ps.setString(i + 1, (String) parameters[i]);
            }
        }

        return ps;
    }

    /**
     * try to close result set, ignoring exceptions
     * return connection to {@link IConnectionPool}
     *
     * @param rs         {@link ResultSet} need to close
     * @param connection need to return
     */
    public static void closeResultSetAndReturnConnection(ResultSet rs, Connection connection) {

        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException ex) {
                logger.warn("closeResultSetEx", ex);
            }

        }

        CONNECTION_POOL.releaseConnection(connection);
    }

    /**
     * insert int parameters inside sqlInsert  in order
     * execute sqlInsert to change data base table
     * use sqlSelect to get data from data base
     *
     * @param sqlInsert  insertRequest to jdbc
     * @param sqlSelect  selectRequest to jdbc
     * @param signsStaff {@link FactoryType} bean
     * @param parameters int parameters to set inside sqlInsert
     * @return {@link FactoryType} bean
     * @throws SQLException           when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it,
     *                                parameters count not equal {@link PreparedStatement} places for set parameters
     *                                or {@link PreparedStatement} throw it
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     */
    static FactoryType createField(String sqlInsert, String sqlSelect, FactoryType signsStaff, int... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert); PreparedStatement psSelect = connection.prepareStatement(sqlSelect)) {

            intParametersInsideRequest(parameters, psInsert);

            psInsert.executeUpdate();

            rs = psSelect.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * insert String parameters inside sqlInsert  in order
     * execute sqlInsert to change data base table
     * use sqlSelect to get data from data base
     *
     * @param sqlInsert  insertRequest to jdbc
     * @param sqlSelect  selectRequest to jdbc
     * @param signsStaff {@link FactoryType} bean
     * @param parameters String parameters to set inside sqlInsert
     * @return {@link FactoryType} bean
     * @throws SQLException           when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it,
     *                                parameters count not equal {@link PreparedStatement} places for set parameters
     *                                or {@link PreparedStatement} throw it
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     */
    static FactoryType createField(String sqlInsert, String sqlSelect, FactoryType signsStaff, String... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert); PreparedStatement psSelect = connection.prepareStatement(sqlSelect)) {

            stringParametersInsideRequest(parameters, psInsert);
            psInsert.executeUpdate();

            rs = psSelect.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * insert String and int parameters inside sqlInsert in order
     * execute sqlInsert to change data base table
     * use sqlSelect to get data from data base
     *
     * @param sqlInsert  insertRequest to jdbc
     * @param sqlSelect  selectRequest to jdbc
     * @param signsStaff {@link FactoryType} bean
     * @param parameters String and int parameters to set inside sqlInsert
     * @return {@link FactoryType} bean
     * @throws SQLException           when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it,
     *                                parameters count not equal {@link PreparedStatement} places for set parameters
     *                                or {@link PreparedStatement} throw it
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     */
    static FactoryType createFieldUseDifferentParam(String sqlInsert, String sqlSelect, FactoryType signsStaff, Object... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert); PreparedStatement psSelect = connection.prepareStatement(sqlSelect)) {

            differentParametersInsideRequest(parameters, psInsert);

            psInsert.executeUpdate();

            rs = psSelect.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * set field using request to data base
     *
     * @param request request to data base
     * @param id      first request parameter - int
     * @param info    second request parameter - String
     * @return true in case of success or false if nothing was changed
     * @throws SQLException when  {@link PreparedStatement#executeUpdate()} throw it
     */
    static boolean setField(String request, int id, String info) throws SQLException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setString(1, info);
            ps.setInt(2, id);

            if (ps.executeUpdate() == 0) {
                logger.info(" wrong id ");
                return false;
            }

            return true;

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /**
     * set field using request to data base
     *
     * @param request   request to data base
     * @param id        first request parameter int
     * @param parameter second request parameter int
     * @return true in case of success or false if nothing was changed
     * @throws SQLException when  {@link PreparedStatement#executeUpdate()} throw it
     */
    static boolean setField(String request, int id, int parameter) throws SQLException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setInt(1, parameter);
            ps.setInt(2, id);

            if (ps.executeUpdate() == 0) {
                logger.info(" wrong id ");
                return false;
            }

            return true;

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /**
     * set field using request to data base
     *
     * @param request   request to data base
     * @param id        first request parameter - int
     * @param parameter second request parameter - boolean
     * @return true in case of success or false if nothing was changed
     * @throws SQLException when  {@link PreparedStatement#executeUpdate()} throw it
     */
    static boolean setField(String request, int id, boolean parameter) throws SQLException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setBoolean(1, parameter);
            ps.setInt(2, id);

            if (ps.executeUpdate() == 0) {
                logger.info(" wrong id ");
                return false;
            }

            return true;

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

    /**
     * get jdbc field using request to data base
     *
     * @param request request to data base
     * @param id      first request parameter int
     * @return string if success or null if nothing to return
     * @throws SQLException when {@link PreparedStatement} throw it
     */
    static String getString(String request, int id) throws SQLException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();


            return rs.next() ? rs.getString(1) : null;

        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * get jdbc field using request to data base
     *
     * @param request   request to data base
     * @param parameter String parameter to include in request
     * @return string if success or null if nothing to return
     * @throws SQLException when {@link PreparedStatement} throw it
     */
    static String getString(String request, String parameter) throws SQLException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setString(1, parameter);

            rs = ps.executeQuery();

            return rs.next() ? rs.getString(1) : null;

        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * get field using request to data base
     *
     * @param request request to data base
     * @param id      first request parameter
     * @return int parameter if success or -1 in not
     * @throws SQLException when {@link PreparedStatement} throw it
     */
    static int getInt(String request, int id) throws SQLException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setInt(1, id);


            rs = ps.executeQuery();

            return rs.next() ? rs.getInt(1) : -1;

        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * get field using request to data base
     *
     * @param request request to data base
     * @param id      first request parameter
     * @return boolean parameter
     * @throws SQLException           when {@link PreparedStatement} throw it
     * @throws DAOValidationException if nothing to return
     */
    static boolean getBoolean(String request, int id) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new DAOValidationException("wrong id: " + id);
            }

            return rs.getBoolean(1);

        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * get array of {@link FactoryType} bean using request with int parameters
     *
     * @param request    request to jdbc
     * @param signsStaff factory type that need to return
     * @param parameters int parameters need to include inside request
     * @return array of {@link FactoryType}
     * @throws SQLException when {@link Factory#createSignStaffArr(ResultSet, FactoryType)} throw it,
     *                      parameters count not equal {@link PreparedStatement} places for set parameters
     *                      or {@link PreparedStatement} throw it
     */
    static FactoryType[] getSignsStaff(String request, FactoryType signsStaff, int... parameters) throws SQLException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            intParametersInsideRequest(parameters, ps);

            rs = ps.executeQuery();

            return signsControlFactory.createSignStaffArr(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * get exemplar {@link FactoryType} bean using request with int parameters
     *
     * @param request    request to jdbc
     * @param signsStaff factory type that need to return
     * @param parameters int parameters need to include inside request
     * @return {@link FactoryType}
     * @throws SQLException           when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it,
     *                                parameters count not equal {@link PreparedStatement} places for set parameters
     *                                or {@link PreparedStatement} throw it
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     */
    static FactoryType getOneSignsStaff(String request, FactoryType signsStaff, int... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            intParametersInsideRequest(parameters, ps);


            rs = ps.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * get exemplar {@link FactoryType} bean using request with int parameters
     *
     * @param request    request to jdbc
     * @param signsStaff factory type that need to return
     * @param parameters String parameters need to include inside request
     * @return {@link FactoryType}
     * @throws SQLException           when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it,
     *                                parameters count not equal {@link PreparedStatement} places for set parameters
     *                                or {@link PreparedStatement} throw it
     * @throws DAOValidationException when {@link Factory#createSignStaff(ResultSet, FactoryType)} throw it
     */
    static FactoryType getOneSignsStaff(String request, FactoryType signsStaff, String... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            stringParametersInsideRequest(parameters, ps);


            rs = ps.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

}