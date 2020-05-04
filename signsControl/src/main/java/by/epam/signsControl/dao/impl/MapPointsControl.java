package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MapPointsControl implements IMapPointsControl {

    private static final Logger logger = LogManager.getLogger(MapPointsControl.class);


    private static final String SQL_SELECT =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation  FROM map_points where coordinates=ST_GeomFromText(?);";
    private static final String SQL_SELECT_ALL =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation  FROM map_points order by coordinates";
    private static final String SQL_SELECT_USE_LAST_INSERT_ID =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation FROM map_points where signs_list=LAST_INSERT_ID();";
    private static final String SQL_INSERT = "INSERT INTO map_points (coordinates, signs_angle, address) " +
            "SELECT * FROM (SELECT ST_GeomFromText(?), ?, ? ) as rt " +
            "where not exists(SELECT coordinates, signs_angle FROM map_points where coordinates=ST_GeomFromText(?) AND signs_angle=?) LIMIT 1";
    private static final String SQL_INSERT_WITH_ANNOTATION = "INSERT INTO map_points (coordinates, signs_angle, address, annotation) " +
            "SELECT * FROM (SELECT ST_GeomFromText(?), ?, ?, ? ) as rt " +
            "where not exists(SELECT coordinates, signs_angle FROM map_points where coordinates=ST_GeomFromText(?) AND signs_angle=?) LIMIT 1";
    private static final String SQL_DELETE = "DELETE FROM map_points WHERE (signs_list = ?);";
    private static final String SQL_FIND_BY_SINGS_LIST =
            "SELECT ST_AsText(coordinates), address, signs_list, signs_angle, annotation  FROM map_points where signs_list=";

    private static final String SQL_SET_ADDRESS = "UPDATE map_points SET address = ? WHERE (signs_list = ?)";
    private static final String SQL_SET_ANNOTATION = "UPDATE map_points SET annotation = ? WHERE (signs_list = ?)";
    private static final String SQL_SET_ANGLE = "UPDATE map_points SET signs_angle = ? WHERE (signs_list = ?)";

    @Override
    public MapPoint getMapPoint(String coordinates) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.getOneSignsStaff(SQL_SELECT, new MapPoint(), coordinates);

        } catch (SQLException ex) {

            logger.warn("getMapPoint fail: coordinates: " + coordinates, ex);
            throw new DAOException(ex);

        }
    }


    @Override
    public MapPoint addMapPoint(String coordinates, String address, int angle) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createFieldWithExistingCheckUseDifferentParameters
                    (SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new MapPoint(), coordinates, angle, address, coordinates, angle);

        } catch (SQLException ex) {

            logger.warn("add with coordinates, address fail: coordinates: " + coordinates + ";" + address, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint addMapPoint(String coordinates, String address, int angle, String annotation) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createFieldWithExistingCheckUseDifferentParameters
                    (SQL_INSERT_WITH_ANNOTATION, SQL_SELECT_USE_LAST_INSERT_ID, new MapPoint(), coordinates, angle, address, annotation, coordinates, angle);

        } catch (SQLException ex) {

            logger.warn("add with coordinates, address, annotation fail: coordinates: " + coordinates + "; " + address + "; " + annotation, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint[] getMapPoints() throws DAOException {
        try {

            return (MapPoint[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new MapPoint());

        } catch (SQLException ex) {

            logger.warn("getMapPoints fail ", ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean removeMapPoint(int signsList) throws DAOException {
        try {

            RequestExecutor.createField
                    (SQL_DELETE, SQL_FIND_BY_SINGS_LIST + signsList, new MapPoint(), signsList);
            return false;
        } catch (DAOValidationException e) {
            return true;
        } catch (SQLException ex) {

            logger.warn("remove mapPoint fail: signsList: " + signsList, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setAddress(int signs_list, String address) throws DAOException {

        if (address == null) {
            address = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_ADDRESS, signs_list, address);
        } catch (SQLException ex) {
            logger.warn("setAddress fail: id: " + signs_list, ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean setAngle(int signs_list, int angle) throws DAOException {

        try {
            return RequestExecutor.setField(SQL_SET_ANGLE, signs_list, angle);
        } catch (SQLException ex) {
            logger.warn("setAddress fail: id: " + signs_list, ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean setAnnotation(int signs_list, String annotation) throws DAOException {
        if (annotation == null) {
            annotation = "";
        }

        try {
            return RequestExecutor.setField(SQL_SET_ANNOTATION, signs_list, annotation);
        } catch (SQLException ex) {
            logger.warn("setAdress fail: id: " + signs_list, ex);
            throw new DAOException(ex);
        }
    }
}
