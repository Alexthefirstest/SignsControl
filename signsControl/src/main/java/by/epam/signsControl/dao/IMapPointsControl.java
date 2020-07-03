package by.epam.signsControl.dao;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.exceptions.DAOException;

public interface IMapPointsControl {

    MapPoint getMapPoint(String coordinates) throws DAOException;
    MapPoint getMapPoint(int signsList) throws DAOException;

    MapPoint addMapPoint(String coordinates, String address, int signsAngle) throws DAOException;

    MapPoint addMapPoint(String coordinates, String address, int signsAngle, String annotation) throws DAOException;

    MapPoint[] getMapPoints() throws DAOException;
    MapPoint[] getEmptyMapPoints() throws DAOException;

    boolean removeMapPoint(int signsList) throws DAOException;
    boolean setParameters(int signs_list, int newDirection, String address, String annotation) throws DAOException;
    boolean setParameters(int signs_list, String address, String annotation) throws DAOException;


    boolean setAddress(int signs_list, String address) throws DAOException;

    boolean setAngle(int signs_list, int angle) throws DAOException;
    boolean setAnnotation(int signs_list, String annotation) throws DAOException;

}
