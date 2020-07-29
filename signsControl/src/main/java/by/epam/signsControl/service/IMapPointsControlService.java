package by.epam.signsControl.service;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.service.exceptions.ServiceException;

public interface IMapPointsControlService {

    /**
     * get map point by coordinates
     *
     * @param coordinates to find
     * @return {@link MapPoint} with coordinates
     * @throws ServiceException when get an exception during execution
     */
    MapPoint getMapPoint(String coordinates) throws ServiceException;

    /**
     * get map point by sign list
     *
     * @param signsList to find
     * @return {@link MapPoint} with sign list param
     * @throws ServiceException when get an exception during execution
     */
    MapPoint getMapPoint(int signsList) throws ServiceException;

    /**
     * add map point
     *
     * @param coordinates to set
     * @param address     to set
     * @param signsAngle  to set
     * @return {@link MapPoint} if success
     * @throws ServiceException when get an exception during execution
     */
    MapPoint addMapPoint(String coordinates, String address, int signsAngle) throws ServiceException;

    /**
     * add map point
     *
     * @param coordinates to set
     * @param address     to set
     * @param signsAngle  to set
     * @param annotation  to set
     * @return {@link MapPoint} if success
     * @throws ServiceException when get an exception during execution
     */
    MapPoint addMapPoint(String coordinates, String address, int signsAngle, String annotation) throws ServiceException;

    /**
     * get all map points
     *
     * @return {@link MapPoint} array
     * @throws ServiceException when get an exception during execution
     */
    MapPoint[] getMapPoints() throws ServiceException;

    /**
     * get  map points with no signs
     *
     * @return {@link MapPoint} array
     * @throws ServiceException when get an exception during execution
     */
    MapPoint[] getEmptyMapPoints() throws ServiceException;

    /**
     * delete map with sign list point from db
     *
     * @param signsList to remove
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean removeMapPoint(int signsList) throws ServiceException;

    /**
     * set direction, address, annotation
     *
     * @param signs_list   sign list id where set
     * @param newDirection direction to set
     * @param address      address to set
     * @param annotation   annotation to set
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean setParameters(int signs_list, int newDirection, String address, String annotation) throws ServiceException;

    /**
     * set direction, address, annotation
     *
     * @param signs_list sign list id where set
     * @param address    address to set
     * @param annotation annotation to set
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean setParameters(int signs_list, String address, String annotation) throws ServiceException;

    /**
     * set address
     *
     * @param signs_list sign list where set
     * @param address    address to set
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean setAddress(int signs_list, String address) throws ServiceException;

    /**
     * set angle
     *
     * @param signs_list sign list id where set
     * @param angle      angle to set
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean setAngle(int signs_list, int angle) throws ServiceException;

    /**
     * set annotation
     *
     * @param signs_list sign list id where set
     * @param annotation annotation to set
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean setAnnotation(int signs_list, String annotation) throws ServiceException;

}
