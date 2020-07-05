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
//use this 4
    private static final String SQL_SELECT_USE_LAST_INSERT_ID =
            "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, " +
                    "sl.date_of_remove, sl.annotation, directions.direction, ps.name, ps.description FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign join map_points as mp on sl.signs_list_id=mp.signs_list" +
                    " join directions on mp.direction=directions.id where local_sign_id=last_insert_id();";

    private static final String SQL_INSERT = "INSERT INTO sign_lists (signs_list_id, sign, standard_size, annotation) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_WITH_DATE_OF_ADD = "INSERT INTO sign_lists (`signs_list_id`, `sign`, `standard_size`, `date_of_add`, annotation) VALUES (?, ?, ?,?,?);";
    private static final String SQL_INSERT_WITH_DATE_OF_ADD_DATE_OF_REMOVE = "INSERT INTO sign_lists (signs_list_id, sign, standard_size, date_of_add, date_of_remove, annotation) VALUES (?, ?, ?,?,?,?);";

  //  private static final String SQL_INSERT_WITH_ANNOTATION = "INSERT INTO sign_lists (signs_list_id, sign, standard_size, annotation) VALUES (?, ?, ?,?)";


    private static final String SQL_DELETE = "DELETE FROM `sign_lists` WHERE (`local_sign_id` = ?);";
    private static final String SQL_SELECT_ACTUAL = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, \n" +
            " sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign " +
            " where date_of_remove is null AND signs_list_id=? order by signs_list_id, pdd_section, pdd_sign, pdd_kind;";


    private static final String SQL_SELECT_ACTUAL_INCLUDE_MAP_POINT = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, " +
            "sl.date_of_remove, sl.annotation, directions.direction, ST_AsText(mp.coordinates), mp.address FROM sign_lists as sl " +
            "join pdd_signs as ps on ps.id = sl.sign join map_points as mp on mp.signs_list = sl.signs_list_id join directions on mp.direction=directions.id" +
            " where date_of_remove is null order by mp.coordinates, mp.signs_list;";


    //use
    private static final String SQL_SELECT_ALL_INCLUDE_MAP_POINT = "SELECT sl.local_sign_id, mp.signs_list, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, " +
            " sl.date_of_remove, mp.annotation, directions.direction, ST_AsText(mp.coordinates), mp.address, ps.name, ps.description, sl.annotation FROM sign_lists as sl " +
            " right join map_points as mp on mp.signs_list = sl.signs_list_id " +
            " left join pdd_signs as ps on ps.id = sl.sign " +
            " join directions on mp.direction=directions.id  " +
            " where coordinates in (SELECT coordinates FROM map_points as mp join sign_lists as sl on mp.signs_list=sl.signs_list_id) order by mp.coordinates, mp.signs_list;";

    //use
    private static final String SQL_SELECT_BY_DATE_INCLUDE_MAP_POINT = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, " +
            "sl.date_of_remove, mp.annotation, directions.direction, ST_AsText(mp.coordinates), mp.address, ps.name, ps.description, sl.annotation  " +
            "FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign join map_points as mp on mp.signs_list = sl.signs_list_id  " +
            "join directions on mp.direction=directions.id where  (STR_TO_DATE(?, '%Y.%m.%d') BETWEEN sl.date_of_add AND sl.date_of_remove) OR  (sl.date_of_remove is null AND (STR_TO_DATE(?, '%Y.%m.%d')>= sl.date_of_add))  " +
            "order by mp.coordinates, mp.signs_list;";


    //use
    private static final String SQL_SELECT_BY_COORDINATES = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add," +
            "     sl.date_of_remove, sl.annotation, directions.direction, ps.name, ps.description FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign join map_points as mp on sl.signs_list_id=mp.signs_list" +
            " join directions on mp.direction=directions.id       where mp.coordinates=st_geomfromtext(?)" +
            "        order by signs_list_id,  sl.date_of_remove, sl.date_of_add, pdd_section, pdd_sign, pdd_kind;";  //use
//use
    private static final String SQL_SET_ADD_REMOVE_DATES_ANNOTATION = "UPDATE `sign_lists` SET `date_of_add` = ?, `date_of_remove` = ?, `annotation` = ? WHERE (`local_sign_id` = ?);";


    private static final String SQL_SELECT_ACTUAL_USE_DATE = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add,  " +
            " sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign  " +
            "  where sl.signs_list_id=? AND (STR_TO_DATE(?, '%Y.%m.%d') BETWEEN sl.date_of_add AND sl.date_of_remove " +
            "   AND signs_list_id=8 OR sl.date_of_remove is null) " +
            "   order by signs_list_id, pdd_section, pdd_sign, pdd_kind; ";


    private static final String SQL_SELECT_ALL = "SELECT sl.local_sign_id, sl.signs_list_id, ps.id, pdd_section, pdd_sign, pdd_kind, picture, standard_size, sl.date_of_add, \n" +
            " sl.date_of_remove, sl.annotation FROM sign_lists as sl join pdd_signs as ps on ps.id = sl.sign " +
            "order by signs_list_id, pdd_section, pdd_sign, pdd_kind;";
    private static final String SQL_SET_INFO = "UPDATE sign_lists SET annotation = ? WHERE (`local_sign_id` = ?)";
    private static final String SQL_SET_DATE_OF_ADD = "UPDATE sign_lists SET date_of_add = ? WHERE (`local_sign_id` = ?)";
    private static final String SQL_SET_DATE_OF_REMOVE = "UPDATE sign_lists SET date_of_remove = ? WHERE (`local_sign_id` = ?)";

    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws DAOException {
        try {

            return (LocalSign) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new LocalSign(), signListId, pddSignId, standardSize, annotation);

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

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.warn("add fail: string is null ", ex);
                throw new DAOValidationException("string is null");
            }

            logger.warn("add fail fail", ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {

            logger.warn("add fail", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd,  String annotation) throws DAOException {
        try {

            return (LocalSign) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_INSERT_WITH_DATE_OF_ADD, SQL_SELECT_USE_LAST_INSERT_ID, new LocalSign(), signListId, pddSignId,
                            standardSize, dateOfAdd, annotation);

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

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.warn("add fail: string is null ", ex);
                throw new DAOValidationException("string is null");
            }

            logger.warn("add fail fail", ex);
            throw new DAOException(ex);

        } catch (SQLException ex) {

            logger.warn("add fail", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String dateOfRemove,  String annotation) throws DAOException {
        try {

            return (LocalSign) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_INSERT_WITH_DATE_OF_ADD_DATE_OF_REMOVE, SQL_SELECT_USE_LAST_INSERT_ID, new LocalSign(),
                            signListId, pddSignId, standardSize, dateOfAdd, dateOfRemove, annotation);

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

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.warn("add fail: string is null ", ex);
                throw new DAOValidationException("string is null");
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
    public MapPoint$LocalSign[] getAllMapPoints$LocalSigns() throws DAOException {

        try {

            return (MapPoint$LocalSign[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL_INCLUDE_MAP_POINT, new MapPoint$LocalSign());

        } catch (SQLException ex) {
            logger.warn("select actual MapPoint$LocalSign fail ", ex);
            throw new DAOException(ex);

        }

    }

    @Override
    public MapPoint$LocalSign[] getMapPoints$LocalSignsByDate(String date) throws DAOException {
        try {

            return (MapPoint$LocalSign[]) RequestExecutor.getSignsStaffWithDifferentParameters(SQL_SELECT_BY_DATE_INCLUDE_MAP_POINT,
                    new MapPoint$LocalSign(), date, date);

        } catch (SQLException ex) {
            logger.warn("select actual MapPoint$LocalSign fail ", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public LocalSign[] getSigns(String coordinates) throws DAOException {

        try {

            return (LocalSign[]) RequestExecutor.getSignsStaffWithDifferentParameters(SQL_SELECT_BY_COORDINATES, new LocalSign(), coordinates);

        } catch (SQLException ex) {
            logger.warn("select sign use coordinates fail ", ex);
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
    public boolean setParameters(int localSignId, String dateOfAdd, String dateOfRemove, String annotation) throws DAOException {
        if (annotation == null) {
            annotation = "-";
        }

        try {
            return RequestExecutor.setFields(SQL_SET_ADD_REMOVE_DATES_ANNOTATION, dateOfAdd, dateOfRemove, annotation, localSignId);
        } catch (SQLException ex) {
            logger.warn("set params fail fail: id: " + localSignId, ex);
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
    public boolean setDateOfAdd(int localSignID, String date) throws DAOException {
        try {
            return RequestExecutor.setField(SQL_SET_DATE_OF_ADD, localSignID, date);
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
    public boolean setDateOfRemove(int localSignID, String date) throws DAOException {

        try {
            return RequestExecutor.setField(SQL_SET_DATE_OF_REMOVE, localSignID, date);
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
