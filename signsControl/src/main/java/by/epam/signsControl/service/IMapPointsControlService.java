package by.epam.signsControl.service;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.service.exceptions.ServiceException;

public interface IMapPointsControlService {

    MapPoint getMapPoint(String coordinateX, String coordinateY) throws ServiceException;

    MapPoint addMapPoint(String coordinateX, String coordinateY, String address, int signsAngle) throws ServiceException;

    MapPoint addMapPoint(String coordinateX, String coordinateY, String address, int signsAngle, String annotation) throws ServiceException;

    MapPoint[] getMapPoints() throws ServiceException;

    boolean removeMapPoint(int signsList) throws ServiceException;

    boolean setAddress(int signs_list, String address) throws ServiceException;

    boolean setAngle(int signs_list, int angle) throws ServiceException;

    boolean setAnnotation(int signs_list, String annotation) throws ServiceException;

}
