package by.epam.signsControl.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;

import by.epam.signsControl.bean.FactoryType;

import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.impl.factory.SignsControlFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;


class RequestExecutor {

    /*
     *connection pool instance
     */
    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    /*
     *signs control factory instance
     */
    private static final SignsControlFactory signsControlFactory = SignsControlFactory.getINSTANCE();

    /*
     *logger
     */
    private static Logger logger = LogManager.getLogger(RequestExecutor.class);

    /**
     * private constructor
     */
    private RequestExecutor() {
    }

    /*
     * put int parameters inside result statement
     */
    private static void intParametersInsideRequest(int[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            ps.setInt(i + 1, parameters[i]);
        }

    }

    /*
     * put String parameters inside result statement
     */
    private static void stringParametersInsideRequest(String[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            ps.setString(i + 1, parameters[i]);
        }

    }

    /*
     * put int, char and String parameters inside result statement
     */
    private static PreparedStatement differentParametersInsideRequest(Object[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {

            if (parameters[i] instanceof Integer) {
                ps.setInt(i + 1, (Integer) parameters[i]);
            }
            if (parameters[i] instanceof String) {
                ps.setString(i + 1, (String) parameters[i]);
            }
            if (parameters[i] instanceof Character) {
                ps.setString(i + 1, String.valueOf(parameters[i]));
            }
        }

        return ps;
    }

    /**
     * close resultSet and return connection to connection pool
     *
     * @param rs         {@link ResultSet}
     * @param connection {@link Connection} from {@link IConnectionPool}
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
     * create field new jdbc field
     *
     * @param sqlInsert  insert request
     * @param sqlSelect  select request for select field after creation
     * @param signsStaff object to set it with parameters from select request
     * @param parameters int parameters to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result}
     * @throws SQLException           when {@link ResultSet} or {@link PreparedStatement} throw it
     * @throws DAOValidationException when  {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} throw it
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
     * create field new jdbc field
     *
     * @param sqlInsert  insert request
     * @param sqlSelect  select request for select field after creation
     * @param signsStaff object to set it with parameters from select request
     * @param parameters int,string and double to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result}
     * @throws SQLException           when {@link ResultSet} or {@link PreparedStatement} throw it
     * @throws DAOValidationException when  {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} throw it
     */
    static FactoryType createFieldUseDifferentParameters(String sqlInsert, String sqlSelect, FactoryType signsStaff, Object... parameters) throws SQLException, DAOValidationException {

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
     * create field new jdbc field with existing check
     *
     * @param sqlInsert  insert request
     * @param sqlSelect  select request for select field after creation
     * @param signsStaff object to set it with parameters from select request
     * @param parameters int,string and double to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} or null if field wasn't inserted
     * @throws SQLException           when {@link ResultSet} or {@link PreparedStatement} throw it
     * @throws DAOValidationException when  {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} throw it
     */
    static FactoryType createFieldWithExistingCheck(String sqlInsert, String sqlSelect, FactoryType signsStaff, Object... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert); PreparedStatement psSelect = connection.prepareStatement(sqlSelect)) {

            differentParametersInsideRequest(parameters, psInsert);

            if (psInsert.executeUpdate() == 0) {
                logger.info("duplicate entry: " + signsStaff.getClass());
                return null;
            }

            rs = psSelect.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * set field in sql table
     *
     * @param request request for sql set
     * @param id      id of sql field to set parameter
     * @param info    string parameter to set
     * @return true if success or false in other option
     * @throws SQLException when  {@link PreparedStatement} throw it
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
     * set field in sql table
     *
     * @param request   request for sql set
     * @param id        id of sql field to set parameter
     * @param parameter int parameter to set
     * @return true if success or false in other option
     * @throws SQLException when  {@link PreparedStatement} throw it
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
     * set field in sql table
     *
     * @param request   request for sql set
     * @param id        id of sql field to set parameter
     * @param parameter {@link Timestamp} parameter to set
     * @return true if success or false in other option
     * @throws SQLException when   {@link PreparedStatement} throw it
     */
    static boolean setField(String request, int id, Timestamp parameter) throws SQLException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setTimestamp(1, parameter);
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
     * set field in sql table
     *
     * @param request   request for sql set
     * @param id        id of sql field to set parameter
     * @param parameter Date parameter to set
     * @return true if success or false in other option
     * @throws SQLException when  {@link PreparedStatement} throw it
     */
    static boolean setField(String request, int id, Date parameter) throws SQLException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setDate(1, parameter);
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
     * @param request request for sql select
     * @param id      id for sql select parameter
     * @return value of parameter with id
     * @throws SQLException when {@link ResultSet} or {@link PreparedStatement} throw it
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
     * @param request    select request for select field
     * @param signsStaff object to set it with parameters from select request
     * @param parameters int parameters to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaffArr(ResultSet, FactoryType) result}
     * @throws SQLException when {@link ResultSet} or {@link PreparedStatement} throw it
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
     * @param request    select request for select field
     * @param signsStaff object to set it with parameters from select request
     * @param parameters cha, int or string parameters to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaffArr(ResultSet, FactoryType) result}
     * @throws SQLException when {@link ResultSet} or {@link PreparedStatement} throw it
     */
    static FactoryType[] getSignsStaffWithDifferentParameters(String request, FactoryType signsStaff, Object... parameters) throws SQLException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            differentParametersInsideRequest(parameters, ps);

            rs = ps.executeQuery();

            return signsControlFactory.createSignStaffArr(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * create field new jdbc field
     *
     * @param request    select request for select field after creation
     * @param signsStaff object to set it with parameters from select request
     * @param parameters string parameters to insert into  request request
     * @return {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result}
     * @throws SQLException           when {@link ResultSet} or {@link PreparedStatement} throw it
     * @throws DAOValidationException when  {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} throw it
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

    /**
     * create field new jdbc field
     *
     * @param request    select request for select field after creation
     * @param signsStaff object to set it with parameters from select request
     * @param parameters string, char or int parameters to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result}
     * @throws SQLException           when {@link ResultSet} or {@link PreparedStatement} throw it
     * @throws DAOValidationException when  {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} throw it
     */
    static FactoryType getOneSignsStaff(String request, FactoryType signsStaff, Object... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            differentParametersInsideRequest(parameters, ps);


            rs = ps.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * create field new jdbc field with existing check
     *
     * @param sqlInsert  insert request
     * @param sqlSelect  select request for select field after creation
     * @param signsStaff object to set it with parameters from select request
     * @param parameters int,string and double to insert into  insert request
     * @return {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} or null if field wasn't inserted
     * @throws SQLException           when {@link ResultSet} or {@link PreparedStatement} throw it
     * @throws DAOValidationException when  {@link SignsControlFactory#createSignStaff(ResultSet, FactoryType) result} throw it
     */
    static FactoryType createFieldWithExistingCheckUseDifferentParameters(String sqlInsert, String sqlSelect, FactoryType signsStaff, Object... parameters) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert); PreparedStatement psSelect = connection.prepareStatement(sqlSelect)) {

            differentParametersInsideRequest(parameters, psInsert);

            if (psInsert.executeUpdate() == 0) {
                logger.info("duplicate entry: " + signsStaff.getClass());
                return null;
            }

            rs = psSelect.executeQuery();

            return signsControlFactory.createSignStaff(rs, signsStaff);


        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

    /**
     * set field in sql table
     *
     * @param request   request for sql set
     * @param parameters  parameters to set
     * @return true if success or false in other option
     * @throws SQLException {@link PreparedStatement} throw it
     */
    static boolean setFields(String request, Object...parameters) throws SQLException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            differentParametersInsideRequest(parameters, ps);

            if (ps.executeUpdate() == 0) {
                logger.info(" wrong id ");
                return false;
            }

            return true;

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

    }

}