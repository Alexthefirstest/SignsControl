package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

public class LocalSingsControl implements ILocalSignsControl {

    Logger logger = LogManager.getLogger(LocalSingsControl.class);

    private static final String SQL_SELECT_USE_LAST_INSERT_ID =
            "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, " +
                    "sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign " +
                    "where local_sign_id=last_insert_id();";
    private static final String SQL_INSERT = "INSERT INTO sign_lists (signs_list_id, sign, standard_size) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_WITH_ANNOTATION = "INSERT INTO sign_lists (signs_list_id, sign, standard_size, annotation) VALUES (?, ?, ?,?)";
    private static final String SQL_DELETE = "DELETE FROM `sign_lists` WHERE (`local_sign_id` = ?);";
    private static final String SQL_SELECT_ACTUAL = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, \n" +
            " sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign " +
            " where date_of_remove is null AND signs_list_id=? order by signs_list_id, pdd_section, pdd_sign, pdd_kind;";
    private static final String SQL_SELECT_ACTUAL_INCLUDE_MAP_POINT = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, " +
            "sl.date_of_remove, sl.annotation, mp.signs_angle, ST_AsText(mp.coordinates), mp.address FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign join map_points as mp on mp.signs_list = sl.signs_list_id " +
            " where date_of_remove is null order by mp.coordinates, mp.signs_list;";
    private static final String SQL_SELECT_ACTUAL_USE_DATE = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add,  " +
            " sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign  " +
            "  where sl.signs_list_id=? AND (STR_TO_DATE(?, '%Y.%m.%d') BETWEEN sl.date_of_add AND sl.date_of_remove " +
            "   AND signs_list_id=8 OR sl.date_of_remove is null) " +
            "   order by signs_list_id, pdd_section, pdd_sign, pdd_kind ";
    private static final String SQL_SELECT_ALL = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, \n" +
            " sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign " +
            "order by signs_list_id, pdd_section, pdd_sign, pdd_kind;";
    private static final String SQL_SET_INFO = "UPDATE sign_lists SET annotation = ? WHERE (`local_sign_id` = ?)";
    private static final String SQL_SET_DATE_OF_ADD = "UPDATE sign_lists SET date_of_add = ? WHERE (`local_sign_id` = ?)";
    private static final String SQL_SET_DATE_OF_REMOVE = "UPDATE sign_lists SET date_of_remove = ? WHERE (`local_sign_id` = ?)";

    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize) throws DAOException {
        try {

            return (LocalSign) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new LocalSign(), signListId, pddSignId, standardSize);

        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches(".*fk_sign_list.*", ex.getMessage()))) {
                logger.warn("add fail: wrong sign list ", ex);
                throw new DAOValidationException("wrong sign_list");
            }

            if ((Pattern.matches(".*fk_pdd_sign.*", ex.getMessage()))) {
                logger.warn("add fail: wrong sign ", ex);
                throw new DAOValidationException("wrong sign");
            }

            if ((Pattern.matches(".*fk_standard_size.*", ex.getMessage()))) {
                logger.warn("add fail: wrong standard size ", ex);
                throw new DAOValidationException("wrong standard size");
            }

            logger.warn("add fail fail", ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {

            logger.warn("add fail", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws DAOException {
        try {

            return (LocalSign) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_INSERT_WITH_ANNOTATION, SQL_SELECT_USE_LAST_INSERT_ID, new LocalSign(), signListId, pddSignId, standardSize, annotation);

        } catch (SQLIntegrityConstraintViolationException ex) {

            if ((Pattern.matches(".*fk_sign_list.*", ex.getMessage()))) {
                logger.warn("add fail: wrong sign list ", ex);
                throw new DAOValidationException("wrong sign_list");
            }

            if ((Pattern.matches(".*fk_pdd_sign.*", ex.getMessage()))) {
                logger.warn("add fail: wrong sign ", ex);
                throw new DAOValidationException("wrong sign");
            }

            if ((Pattern.matches(".*fk_standard_size.*", ex.getMessage()))) {
                logger.warn("add fail: wrong standard size ", ex);
                throw new DAOValidationException("wrong standard size");
            }

            logger.warn("add fail fail", ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {

            logger.warn("add fail", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean deleteSign(int signId) throws DAOException {
        try {

            RequestExecutor.createFieldUseDifferentParameters
                    (SQL_DELETE, SQL_SELECT_USE_LAST_INSERT_ID, new LocalSign(), signId);

            return false;

        } catch (DAOValidationException e) {
            return true;
        } catch (SQLException ex) {

            logger.warn("delete fail", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint$LocalSign[] getActualMapPoints$LocalSigns() throws DAOException {

        try {

            return (MapPoint$LocalSign[]) RequestExecutor.getSignsStaff(SQL_SELECT_ACTUAL_INCLUDE_MAP_POINT, new MapPoint$LocalSign());

        } catch (SQLException ex) {
            logger.warn("select actual MapPoint$LocalSign fail ", ex);
            throw new DAOException(ex);

        }

    }

    @Override
    public LocalSign[] getActualSigns() throws DAOException {
        try {

            return (LocalSign[]) RequestExecutor.getSignsStaff(SQL_SELECT_ACTUAL, new LocalSign());

        } catch (SQLException ex) {
            logger.warn("select actual fail ", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public LocalSign[] getActualSigns(int signsListID, String date) throws DAOException {
        try {

            return (LocalSign[]) RequestExecutor.getSignsStaffWithDifferentParameters(SQL_SELECT_ACTUAL_USE_DATE, new LocalSign(), signsListID, date);

        } catch (SQLException ex) {
            logger.warn("select actual date fail ", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public LocalSign[] getSignsLists() throws DAOException {
        try {

            return (LocalSign[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new LocalSign());

        } catch (SQLException ex) {
            logger.warn("select all fail ", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setDateOfAdd(int localSignID, Date date) throws DAOException {
        try {
            return RequestExecutor.setField(SQL_SET_DATE_OF_ADD, localSignID, new Timestamp(date.getTime()));
        } catch (SQLException ex) {
            logger.warn("set date add: id: " + localSignID, ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean setDateOfRemove(int localSignID, Date date) throws DAOException {

        try {
            return RequestExecutor.setField(SQL_SET_DATE_OF_REMOVE, localSignID, new java.sql.Date(date.getTime()));
        } catch (SQLException ex) {
            logger.warn("set date remove: id: " + localSignID, ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean setAnnotation(int localSignID, String annotation) throws DAOException {
        if (annotation == null) {
            annotation = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_INFO, localSignID, annotation);
        } catch (SQLException ex) {
            logger.warn("setInfo fail: id: " + localSignID, ex);
            throw new DAOException(ex);
        }
    }
}
