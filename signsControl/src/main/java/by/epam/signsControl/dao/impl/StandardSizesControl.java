package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;

import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;

import java.util.regex.Pattern;

public class StandardSizesControl implements IStandardSizesControl {

    private static Logger logger = LogManager.getLogger(StandardSizesControl.class);

    private static final String SQL_SELECT_USE_ID = "SELECT * FROM standard_sizes WHERE number = ";
    private static final String SQL_INSERT = "INSERT INTO `standard_sizes` (`number`) VALUES (?)";
    private static final String SQL_INSERT_WITH_INFO = "INSERT INTO `standard_sizes` (`number`, `info`) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM `standard_sizes` WHERE (`number` = ?);";
    private static final String SQL_SET_INFO = "UPDATE standard_sizes SET info = ? WHERE (`number` = ?)";
    private static final String SQL_SET_SIZE = "UPDATE standard_sizes SET number = ? WHERE (`number` = ?)";
    private static final String SQL_SELECT_INFO = "SELECT info FROM standard_sizes WHERE number = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM standard_sizes order by number";

    @Override
    public StandardSize addStandardSize(int size) throws DAOException {

        try {

            return (StandardSize) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_ID + size, new StandardSize(), size);

        } catch (SQLException ex) {

            if ((Pattern.matches("Duplicate entry.*", ex.getMessage()))) {
                logger.warn("add size fail, duplicate entry: size: " + size, ex);
                return null;
            }

            logger.warn("add size fail: size: " + size, ex);
            throw new DAOException(ex);

        }

    }

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

            logger.warn("add size fail: size: " + size, ex);
            throw new DAOException(ex);

        }
    }


    @Override
    public boolean removeStandardSize(int size) throws DAOException {

        try {

            RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_ID + size, new StandardSize(), size);
            return false;

        } catch (DAOValidationException e) {
            return true;
        } catch (SQLException ex) {

            logger.warn("delete size fail: size: " + size, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setInfo(int id, String info) throws DAOException {

        if (info == null) {
            info = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_INFO, id, info);
        } catch (SQLException ex) {
            logger.warn("setInfo fail: id: " + id, ex);
            throw new DAOException(ex);
        }

    }

    @Override
    public boolean setSize(int oldSize, int newSize) throws DAOException {
        try {
            return RequestExecutor.setField(SQL_SET_SIZE, oldSize, newSize);
        } catch (SQLException ex) {
            logger.warn("setInfo fail: id: " + oldSize, ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public String getInfo(int id) throws DAOException {

        try {

            return RequestExecutor.getString(SQL_SELECT_INFO, id);

        } catch (SQLException ex) {
            logger.warn("getInfo fail: size: " + id, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public StandardSize[] getStandardSizes() throws DAOException {


        try {

            return (StandardSize[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new StandardSize());

        } catch (SQLException ex) {
            logger.warn("get all fail ", ex);
            throw new DAOException(ex);

        }
    }


}
