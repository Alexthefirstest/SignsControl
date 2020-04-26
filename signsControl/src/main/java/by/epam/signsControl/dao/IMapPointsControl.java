package by.epam.signsControl.dao;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.exceptions.DAOException;

public interface IMapPointsControl {

    MapPoint getMapPoint(String coordinates) throws DAOException;

    MapPoint addMapPoint(String coordinates, String address) throws DAOException;
    MapPoint addMapPoint(String coordinates, String address, String annotation) throws DAOException;

    MapPoint[] getMapPoints() throws DAOException;
    MapPoint removeMapPoint(int signsList) throws DAOException;

}
