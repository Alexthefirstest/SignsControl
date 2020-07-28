package by.epam.signsControl.dao;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.exceptions.DAOException;

/**
 * Interface for jdbc direction fields
 */
public interface IMapPointsControl {

    /**
     * get map point by coordinates
     *
     * @param coordinates to find
     * @return {@link MapPoint} with coordinates
     * @throws DAOException when exception during execution occurred
     */
    MapPoint getMapPoint(String coordinates) throws DAOException;

    /**
     * get map point by sign list
     *
     * @param signsList to find
     * @return {@link MapPoint} with sign list param
     * @throws DAOException when exception during execution occurred
     */
    MapPoint getMapPoint(int signsList) throws DAOException;

    /**
     * add map point
     *
     * @param coordinates to set
     * @param address     to set
     * @param signsAngle  to set
     * @return {@link MapPoint} if success
     * @throws DAOException when exception during execution occurred
     */
    MapPoint addMapPoint(String coordinates, String address, int signsAngle) throws DAOException;

    /**
     * add map point
     *
     * @param coordinates to set
     * @param address     to set
     * @param signsAngle  to set
     * @param annotation  to set
     * @return {@link MapPoint} if success
     * @throws DAOException when exception during execution occurred
     */
    MapPoint addMapPoint(String coordinates, String address, int signsAngle, String annotation) throws DAOException;


    /**
     * get all map points
     *
     * @return {@link MapPoint} array
     * @throws DAOException when exception during execution occurred
     */
    MapPoint[] getMapPoints() throws DAOException;

    /**
     * get  map points with no signs
     *
     * @return {@link MapPoint} array
     * @throws DAOException when exception during execution occurred
     */
    MapPoint[] getEmptyMapPoints() throws DAOException;

    /**
     * delete map with sign list point from db
     *
     * @param signsList to remove
     * @return true if success
     * @throws DAOException when exception during execution occurred
     */
    boolean removeMapPoint(int signsList) throws DAOException;

    /**
     * set direction, address, annotation
     *
     * @param signs_list   sign list id where set
     * @param newDirection direction to set
     * @param address      address to set
     * @param annotation   annotation to set
     * @return true if success
     * @throws DAOException when exception during execution occurred
     */
    boolean setParameters(int signs_list, int newDirection, String address, String annotation) throws DAOException;

    /**
     * set direction, address, annotation
     *
     * @param signs_list sign list id where set
     * @param address    address to set
     * @param annotation annotation to set
     * @return true if success
     * @throws DAOException when exception during execution occurred
     */
    boolean setParameters(int signs_list, String address, String annotation) throws DAOException;

    /**
     * set address
     *
     * @param signs_list sign list id where set
     * @param address    address to set
     * @return true if success
     * @throws DAOException when exception during execution occurred
     */
    boolean setAddress(int signs_list, String address) throws DAOException;

    /**
     * set angle
     *
     * @param signs_list sign list id where set
     * @param angle      angle to set
     * @return true if success
     * @throws DAOException when exception during execution occurred
     */
    boolean setAngle(int signs_list, int angle) throws DAOException;

    /**
     * set annotation
     *
     * @param signs_list sign list id where set
     * @param annotation annotation to set
     * @return true if success
     * @throws DAOException when exception during execution occurred
     */
    boolean setAnnotation(int signs_list, String annotation) throws DAOException;

}
