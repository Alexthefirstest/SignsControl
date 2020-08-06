package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.IMapPointsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

public class MapPointsControlService implements IMapPointsControlService {

    /**
     * {@link IMapPointsControl} instance
     */
    private final IMapPointsControl mapPointsControl;

    /**
     * empty constructor
     */
    public MapPointsControlService() {
        mapPointsControl = DaoFactory.getINSTANCE().getMapPointsControl();
    }

    /**
     * @param mapPointsControlDao {@link IMapPointsControl}
     */
    MapPointsControlService(IMapPointsControl mapPointsControlDao) {
        mapPointsControl = mapPointsControlDao;
    }

    /**
     * get map point by coordinates
     *
     * @param coordinates to find
     * @return {@link MapPoint} with coordinates
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint getMapPoint(String coordinates) throws ServiceException {

        String point = StringTransformer.coordinatesToPointWithCheck(coordinates);

        try {

            return mapPointsControl.getMapPoint(point);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get map point by sign list
     *
     * @param signsList to find
     * @return {@link MapPoint} with sign list param
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint getMapPoint(int signsList) throws ServiceException {


        try {

            return mapPointsControl.getMapPoint(signsList);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add map point
     *
     * @param point      to set
     * @param address    to set
     * @param signsAngle to set
     * @return {@link MapPoint} if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint addMapPoint(String point, String address, int signsAngle) throws ServiceException {

        String checkedPoint = StringTransformer.coordinatesOrPointToPointWithCheck(point);

        InputValidation.nullCheck(address);

        try {

            return mapPointsControl.addMapPoint(checkedPoint, address, signsAngle);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add map point
     *
     * @param point      to set
     * @param address    to set
     * @param signsAngle to set
     * @param annotation to set
     * @return {@link MapPoint} if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint addMapPoint(String point, String address, int signsAngle, String annotation) throws ServiceException {

        String checkedPoint = StringTransformer.coordinatesOrPointToPointWithCheck(point);
        InputValidation.nullCheck(address);
        InputValidation.nullCheck(annotation);

        try {

            return mapPointsControl.addMapPoint(checkedPoint, address, signsAngle, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get all map points
     *
     * @return {@link MapPoint} array
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint[] getMapPoints() throws ServiceException {
        try {

            return mapPointsControl.getMapPoints();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get  map points with no signs
     *
     * @return {@link MapPoint} array
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint[] getEmptyMapPoints() throws ServiceException {
        try {

            return mapPointsControl.getEmptyMapPoints();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * delete map with sign list point from db
     *
     * @param signsList to remove
     * @return true if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public boolean removeMapPoint(int signsList) throws ServiceException {
        try {

            return mapPointsControl.removeMapPoint(signsList);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
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
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public boolean setParameters(int signs_list, int newDirection, String address, String annotation) throws ServiceException {

        InputValidation.nullCheck(address);
        InputValidation.nullCheck(annotation);

        try {

            return mapPointsControl.setParameters(signs_list, newDirection, address, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set direction, address, annotation
     *
     * @param signs_list sign list id where set
     * @param address    address to set
     * @param annotation annotation to set
     * @return true if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public boolean setParameters(int signs_list, String address, String annotation) throws ServiceException {

        InputValidation.nullCheck(address);
        InputValidation.nullCheck(annotation);

        try {

            return mapPointsControl.setParameters(signs_list, address, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set address
     *
     * @param signs_list sign list where set
     * @param address    address to set
     * @return true if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public boolean setAddress(int signs_list, String address) throws ServiceException {

        InputValidation.nullCheck(address);

        try {

            return mapPointsControl.setAddress(signs_list, address);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set angle
     *
     * @param signs_list sign list id where set
     * @param angle      angle to set
     * @return true if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public boolean setAngle(int signs_list, int angle) throws ServiceException {
        try {

            return mapPointsControl.setAngle(signs_list, angle);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set annotation
     *
     * @param signs_list sign list id where set
     * @param annotation annotation to set
     * @return true if success
     * @throws ServiceValidationException when {@link IMapPointsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IMapPointsControl} throw {@link DAOException}
     */
    @Override
    public boolean setAnnotation(int signs_list, String annotation) throws ServiceException {

        InputValidation.nullCheck(annotation);

        try {

            return mapPointsControl.setAnnotation(signs_list, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
