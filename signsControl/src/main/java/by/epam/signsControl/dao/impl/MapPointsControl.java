package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class MapPointsControl implements IMapPointsControl {

    private static final Logger logger = LogManager.getLogger(MapPointsControl.class);


    private static final String SQL_SELECT = "SELECT ST_AsText(coordinates), address, signs_list, annotation  FROM map_points where coordinates=ST_GeomFromText(?);";
    private static final String SQL_SELECT_ALL = "SELECT ST_AsText(coordinates), address, signs_list, annotation  FROM map_points order by coordinates";
    private static final String SQL_SELECT_USE_LAST_INSERT_ID = "SELECT ST_AsText(coordinates), address, signs_list, annotation FROM map_points where signs_list=LAST_INSERT_ID();";
    private static final String SQL_INSERT = "INSERT INTO map_points (coordinates, address) values (ST_GeomFromText(?), ?)";
    private static final String SQL_INSERT_WITH_ANNOTATION = "INSERT INTO map_points (coordinates, address, annotation) values (ST_GeomFromText(?), ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM map_points WHERE (signs_list = ?);";
    private static final String SQL_FIND_BY_SINGS_LIST = "SELECT ST_AsText(coordinates), address, signs_list, annotation  FROM map_points where signs_list=";

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
    public MapPoint addMapPoint(String coordinates, String address) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createField(SQL_INSERT, SQL_SELECT_USE_LAST_INSERT_ID, new MapPoint(), coordinates, address);

        } catch (SQLException ex) {

            logger.warn("add with coordinates, address fail: coordinates: " + coordinates + ";" + address, ex);
            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint addMapPoint(String coordinates, String address, String annotation) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createField(SQL_INSERT_WITH_ANNOTATION, SQL_SELECT_USE_LAST_INSERT_ID, new MapPoint(), coordinates, address, annotation);

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
    public MapPoint removeMapPoint(int signsList) throws DAOException {
        try {

            return (MapPoint) RequestExecutor.createField
                    (SQL_DELETE, SQL_FIND_BY_SINGS_LIST + signsList, new MapPoint(), signsList);

        } catch (SQLException ex) {

            logger.warn("remove mapPoint fail: signsList: " + signsList, ex);
            throw new DAOException(ex);

        }
    }
}
