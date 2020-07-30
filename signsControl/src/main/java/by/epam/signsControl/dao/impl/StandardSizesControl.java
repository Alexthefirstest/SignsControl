package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.FactoryType;
import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;

import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;

import java.util.regex.Pattern;

/**
 * jsbc communication class
 */
public class StandardSizesControl implements IStandardSizesControl {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(StandardSizesControl.class);

    /**
     * select use last insert id
     */
    private static final String SQL_SELECT_USE_ID = "SELECT * FROM standard_sizes WHERE number = ";

    /**
     * sql insert
     */
    private static final String SQL_INSERT = "INSERT INTO `standard_sizes` (`number`) VALUES (?)";

    /**
     * sql insert with info
     */
    private static final String SQL_INSERT_WITH_INFO = "INSERT INTO `standard_sizes` (`number`, `info`) VALUES (?, ?)";

    /**
     * sql delete
     */
    private static final String SQL_DELETE = "DELETE FROM `standard_sizes` WHERE (`number` = ?);";

    /**
     * sql set info
     */
    private static final String SQL_SET_INFO = "UPDATE standard_sizes SET info = ? WHERE (`number` = ?)";

    /**
     * sql set size
     */
    private static final String SQL_SET_SIZE = "UPDATE standard_sizes SET number = ? WHERE (`number` = ?)";

    /**
     * sql set info
     */
    private static final String SQL_SELECT_INFO = "SELECT info FROM standard_sizes WHERE number = ?";

    /**
     * sql select all
     */
    private static final String SQL_SELECT_ALL = "SELECT * FROM standard_sizes order by number";

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public StandardSize addStandardSize(int size) throws DAOException {

        try {

            return (StandardSize) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_ID + size, new StandardSize(), size);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.warn("add size fail, duplicate entry: size: " + size, ex);
                return null;
            }

            throw new DAOException(ex);

        }

    }

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @param info info to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public StandardSize addStandardSize(int size, String info) throws DAOException {

        try {

            return (StandardSize) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_INSERT_WITH_INFO, SQL_SELECT_USE_ID + size, new StandardSize(), size, info);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.warn("add size fail, duplicate entry: size: " + size, ex);
                return null;
            }

            throw new DAOException(ex);

        }
    }

    /**
     * remove st size
     *
     * @param size size to remove
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public boolean removeStandardSize(int size) throws DAOException {

        try {

            RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_ID + size, new StandardSize(), size);
            return false;

        } catch (DAOValidationException e) {
            return true;
        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * set info
     *
     * @param id   size to set
     * @param info to set
     * @return true if success or false in not
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setInfo(int id, String info) throws DAOException {

        if (info == null) {
            info = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_INFO, id, info);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

    }

    /**
     * set info
     *
     * @param oldSize size to set
     * @param newSize new size
     * @return true if success or false in not
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */
    @Override
    public boolean setSize(int oldSize, int newSize) throws DAOException {
        try {
            return RequestExecutor.setField(SQL_SET_SIZE, oldSize, newSize);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    /**
     * get info
     *
     * @param id size to get
     * @return info
     * @throws DAOException when catch exception from {@link RequestExecutor#getString(String, int)}
     */
    @Override
    public String getInfo(int id) throws DAOException {

        try {

            return RequestExecutor.getString(SQL_SELECT_INFO, id);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get all standard sizes
     *
     * @return {@link StandardSize} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, int...)}
     */
    @Override
    public StandardSize[] getStandardSizes() throws DAOException {


        try {

            return (StandardSize[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new StandardSize());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }


}
