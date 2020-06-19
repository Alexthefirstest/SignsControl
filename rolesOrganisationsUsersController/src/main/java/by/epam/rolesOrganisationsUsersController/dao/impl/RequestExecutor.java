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


class RequestExecutor {

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();
    private static final Factory signsControlFactory = Factory.getINSTANCE();

    private static Logger logger = LogManager.getLogger(RequestExecutor.class);

    private RequestExecutor() {
    }


    private static PreparedStatement intParametersInsideRequest(int[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            ps.setInt(i + 1, parameters[i]);
        }

        return ps;
    }

    private static PreparedStatement stringParametersInsideRequest(String[] parameters, PreparedStatement ps) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            ps.setString(i + 1, parameters[i]);
        }

        return ps;
    }

    /*work with integer, string*/
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

    static FactoryType createFieldWithTwoInserts(String sqlInsert, String sqlInsert2, String sqlSelect, FactoryType signsStaff, Object... parameters) throws SQLException, DAOValidationException {

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

    static String getString(String request, String info) throws SQLException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setString(1, info);

            rs = ps.executeQuery();

            return rs.next() ? rs.getString(1) : null;

        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

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

    static boolean getBoolean(String request, int id) throws SQLException, DAOValidationException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(request)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (!rs.next()) {
                logger.info("getboolean wrong id: " + id);
                throw new DAOValidationException("wrong id: " + id);
            }

            return rs.getBoolean(1);

        } finally {
            closeResultSetAndReturnConnection(rs, connection);
        }

    }

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