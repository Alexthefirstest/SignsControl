package by.epam.signsControl.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StandardSizesControl implements IStandardSizesControl {

    private static Logger logger = LogManager.getLogger(StandardSizesControl.class);

    private static final IConnectionPool CONNECTION_POOL = ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();

    private static final String SQL_INSERT = "INSERT INTO `standard_sizes` (`number`) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM `standard_sizes` WHERE (`number` = ?);";
    private static final String SQL_SET_INFO = "UPDATE standard_sizes SET info = ? WHERE (`number` = ?)";
    private static final String SQL_SELECT_INFO = "SELECT info FROM standard_sizes WHERE number = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM standard_sizes order by number";
    private static final String SQL_SELECT_NUMBERS = "SELECT number FROM standard_sizes order by number";

    @Override
    public boolean addStandardSize(int size) throws DAOException {
        return addOrRemoveSize(size, SQL_INSERT);

    }


    @Override
    public boolean removeStandardSize(int size) throws DAOException {
        return addOrRemoveSize(size, SQL_DELETE);
    }

    private boolean addOrRemoveSize(int size, String sqlRequest) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            ps.setInt(1, size);
            ps.execute();

        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.info("addOrRemove size fail: size: " + size, ex);
                return false;
            }

            if ((Pattern.matches("Cannot delete or update a parent row.*", ex.getMessage()))) {
                logger.info("remove fail, deleting the used value is not allowed: " + size, ex);
                throw new DAOValidationException("remove fail, deleting the used value is not allowed");
            }

            logger.warn("addOrRemove size fail: size: " + size, ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {
            logger.warn("addOrRemove size fail: size: " + size, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;

    }

    @Override
    public boolean setInfo(int id, String info) throws DAOException {

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SET_INFO)) {
            ps.setString(1, info);
            ps.setInt(2, id);

            if (ps.executeUpdate() == 0) {
                logger.info("setInfo fail: wrong id: " + id);
                return false;
            }

        } catch (SQLException ex) {
            logger.warn("setInfo fail: size: " + id, ex);
            throw new DAOException(ex);

        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public String getInfo(int id) throws DAOException {
        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_INFO)) {
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (!rs.next()) {

                logger.info("getInfo fail: wrong size: " + id);
                throw new DAOValidationException("getInfo fail: wrong size");
            }

            return rs.getString(1);


        } catch (SQLException | DAOValidationException ex) {
            logger.warn("getInfo fail: size: " + id, ex);
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(id, ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Integer[] getSizesInt() throws DAOException {
        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        ArrayList<Integer> sizes = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL)) {

            rs = ps.executeQuery();

            while (rs.next()) {

                sizes.add(rs.getInt(1));

            }

            return sizes.toArray(new Integer[0]);


        } catch (SQLException ex) {
            logger.warn("getStandardSizesInt fail ");
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }


    @Override
    public StandardSize[] getStandardSizes() throws DAOException {

        ResultSet rs = null;

        Connection connection = CONNECTION_POOL.retrieveConnection();

        ArrayList<StandardSize> sizes = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL)) {

            rs = ps.executeQuery();

            while (rs.next()) {

                sizes.add(new StandardSize(rs.getInt(1), rs.getString(2)));

            }

            return sizes.toArray(new StandardSize[0]);


        } catch (SQLException ex) {
            logger.warn("getStandardSizes fail ");
            throw new DAOException(ex);

        } finally {


            if (rs != null) {

                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.warn(ex);
                }

            }

            CONNECTION_POOL.releaseConnection(connection);
        }
    }


}
