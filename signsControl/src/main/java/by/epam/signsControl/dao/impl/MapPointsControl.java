package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.FactoryType;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * jsbc communication class
 */
public class MapPointsControl implements IMapPointsControl {

    /**
     * logger
     */
    private static final Logger logger = LogManager.getLogger(MapPointsControl.class);

    /**
     * sql select by coordinates to map points table
     */
    private static final String SQL_SELECT =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation  FROM map_points where coordinates=ST_GeomFromText(?);";

    /**
     * sql select all to map points table
     */
    private static final String SQL_SELECT_ALL =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation  FROM map_points order by coordinates, signs_list";

    /**
     * select use last inserted id
     */
    private static final String SQL_SELECT_USE_LAST_INSERT_ID =
            "SELECT ST_AsText(coordinates), address, signs_list, dir.direction, mp.annotation  FROM map_points as mp " +
                    "join directions as dir on dir.id=mp.direction where signs_list=LAST_INSERT_ID();";

    /**
     * select by sign list
     */
    private static final String SQL_SELECT_BY_SIGNS_LIST =
            "SELECT ST_AsText(coordinates), address, signs_list, dir.direction, mp.annotation  FROM map_points as mp " +
                    "join directions as dir on dir.id=mp.direction  where signs_list=?;";

    /**
     * select with no signs
     */
    private static final String SQL_SELECT_EMPTY =
            "SELECT ST_AsText(coordinates), address, signs_list, dir.direction, mp.annotation  FROM map_points as mp " +
                    "join directions as dir on dir.id=mp.direction " +
                    "where coordinates not in (SELECT coordinates FROM map_points as mp join sign_lists as sl on mp.signs_list=sl.signs_list_id) " +
                    "order by coordinates, signs_list;";

    /**
     * insert map point with coordinates, sirection, address
     */
    private static final String SQL_INSERT = "INSERT INTO map_points (coordinates, direction, address) " +
            "SELECT * FROM (SELECT ST_GeomFromText(?), ?, ? ) as rt " +
            "where not exists(SELECT coordinates, direction FROM map_points where coordinates=ST_GeomFromText(?) AND direction=?) LIMIT 1";
    /**
     * insert map point with coordinates, sirection, address, annotation
     */
    private static final String SQL_INSERT_WITH_ANNOTATION = "INSERT INTO map_points (coordinates, direction, address, annotation) " +
            "SELECT * FROM (SELECT ST_GeomFromText(?), ?, ?, ? ) as rt " +
            "where not exists(SELECT coordinates, direction FROM map_points where coordinates=ST_GeomFromText(?) AND direction=?) LIMIT 1";
    /**
     * sql set address direction annotation
     */
    private static final String SQL_SET_ADDRESS_DIRECTION_ANNOTATION =
            "UPDATE `map_points` SET `address` = ?, `direction` = ?, `annotation` = ? WHERE (`signs_list` = ?);";
    /**
     * sql set address annotation
     */
    private static final String SQL_SET_ADDRESS_ANNOTATION =
            "UPDATE `map_points` SET `address` = ?, `annotation` = ? WHERE (`signs_list` = ?);";

    /**
     * delete map point
     */
    private static final String SQL_DELETE = "DELETE FROM map_points WHERE (signs_list = ?);";

    /**
     * sql get by sign list id
     */
    private static final String SQL_FIND_BY_SINGS_LIST =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation  FROM map_points where signs_list=";

    /**
     * sql set address
     */
    private static final String SQL_SET_ADDRESS = "UPDATE map_points SET address = ? WHERE (signs_list = ?)";

    /**
     * sql set annotation
     */
    private static final String SQL_SET_ANNOTATION = "UPDATE map_points SET annotation = ? WHERE (signs_list = ?)";

    /**
     * sql set angle
     */
    private static final String SQL_SET_ANGLE = "UPDATE map_points SET signs_angle = ? WHERE (signs_list = ?)";

    /**
     * get map point by coordinates
     *
     * @param coordinates to find
     * @return {@link MapPoint} with coordinates
     * @throws DAOException when catch exception from {@link RequestExecutor#getOneSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public MapPoint getMapPoint(String coordinates) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.getOneSignsStaff(SQL_SELECT, new MapPoint(), coordinates);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get map point by sign list
     *
     * @param signsList to find
     * @return {@link MapPoint} with sign list param
     * @throws DAOException when catch exception from {@link RequestExecutor#getOneSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public MapPoint getMapPoint(int signsList) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.getOneSignsStaff(SQL_SELECT_BY_SIGNS_LIST, new MapPoint(), signsList);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * add map point
     *
     * @param coordinates to set
     * @param address     to set
     * @param angle       to set
     * @return {@link MapPoint} if success
     * @throws DAOException when wrong direction or catch exception from {@link RequestExecutor#createFieldWithExistingCheckUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public MapPoint addMapPoint(String coordinates, String address, int angle) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createFieldWithExistingCheckUseDifferentParameters
                    (SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new MapPoint(), coordinates, angle, address, coordinates, angle);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*fk_direction.*", ex.getMessage()))) {
                logger.warn("add fail: wrong direction ", ex);
                throw new DAOValidationException("wrong direction");
            }


            throw new DAOException(ex);

        }
    }

    /**
     * add map point
     *
     * @param coordinates to set
     * @param address     to set
     * @param angle       to set
     * @param annotation  to set
     * @return {@link MapPoint} if success
     * @throws DAOException when wrong direction or catch exception from {@link RequestExecutor#createFieldWithExistingCheckUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public MapPoint addMapPoint(String coordinates, String address, int angle, String annotation) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createFieldWithExistingCheckUseDifferentParameters
                    (SQL_INSERT_WITH_ANNOTATION, SQL_SELECT_USE_LAST_INSERT_ID, new MapPoint(), coordinates, angle, address, annotation, coordinates, angle);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*fk_direction.*", ex.getMessage()))) {
                logger.warn("add fail: wrong direction ", ex);
                throw new DAOValidationException("wrong direction");
            }

            throw new DAOException(ex);

        }
    }

    /**
     * get all map points
     *
     * @return {@link MapPoint} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, int...)}
     */
    @Override
    public MapPoint[] getMapPoints() throws DAOException {
        try {

            return (MapPoint[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new MapPoint());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * get  map points with no signs
     *
     * @return {@link MapPoint} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, int...)}
     */
    @Override
    public MapPoint[] getEmptyMapPoints() throws DAOException {
        try {

            return (MapPoint[]) RequestExecutor.getSignsStaff(SQL_SELECT_EMPTY, new MapPoint());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * delete map with sign list point from db
     *
     * @param signsList to remove
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public boolean removeMapPoint(int signsList) throws DAOException {
        try {

            RequestExecutor.createField
                    (SQL_DELETE, SQL_FIND_BY_SINGS_LIST + signsList, new MapPoint(), signsList);
            return false;
        } catch (DAOValidationException e) {
            return true;
        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * set address
     *
     * @param signs_list sign list where set
     * @param address    address to set
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setAddress(int signs_list, String address) throws DAOException {

        if (address == null) {
            address = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_ADDRESS, signs_list, address);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    /**
     * set angle
     *
     * @param signs_list sign list id where set
     * @param angle      angle to set
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */
    @Override
    public boolean setAngle(int signs_list, int angle) throws DAOException {

        try {
            return RequestExecutor.setField(SQL_SET_ANGLE, signs_list, angle);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    /**
     * set annotation
     *
     * @param signs_list sign list id where set
     * @param annotation annotation to set
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setAnnotation(int signs_list, String annotation) throws DAOException {
        if (annotation == null) {
            annotation = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_ANNOTATION, signs_list, annotation);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    /**
     * set direction, address, annotation
     *
     * @param signs_list   sign list id where set
     * @param newDirection direction to set
     * @param address      address to set
     * @param annotation   annotation to set
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setParameters(int signs_list, int newDirection, String address, String annotation) throws DAOException {
        if (annotation == null) {
            annotation = "-";
        }
        if (address == null) {
            address = "-";
        }

        try {
            return RequestExecutor.setFields(SQL_SET_ADDRESS_DIRECTION_ANNOTATION, address, newDirection, annotation, signs_list);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    /**
     * set direction, address, annotation
     *
     * @param signs_list sign list id where set
     * @param address    address to set
     * @param annotation annotation to set
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setParameters(int signs_list, String address, String annotation) throws DAOException {
        if (annotation == null) {
            annotation = "-";
        }
        if (address == null) {
            address = "-";
        }

        try {
            return RequestExecutor.setFields(SQL_SET_ADDRESS_ANNOTATION, address, annotation, signs_list);
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }
}
