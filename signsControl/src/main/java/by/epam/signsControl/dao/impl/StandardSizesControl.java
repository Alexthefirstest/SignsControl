package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.SQLException;

import java.util.regex.Pattern;

public class StandardSizesControl implements IStandardSizesControl {

    private static Logger logger = LogManager.getLogger(StandardSizesControl.class);

    private static final String SQL_SELECT_USE_ID = "SELECT * FROM standard_sizes WHERE number = ";
    private static final String SQL_INSERT = "INSERT INTO `standard_sizes` (`number`) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM `standard_sizes` WHERE (`number` = ?);";
    private static final String SQL_SET_INFO = "UPDATE standard_sizes SET info = ? WHERE (`number` = ?)";
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
    public StandardSize removeStandardSize(int size) throws DAOException {

        try {

            return (StandardSize) RequestExecutor.createField(SQL_DELETE, SQL_SELECT_USE_ID + size, new StandardSize(), size);


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
