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

    private final IMapPointsControl mapPointsControl = DaoFactory.getINSTANCE().getMapPointsControl();

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

    @Override
    public boolean setParameters(int signs_list, int newDirection, String address, String annotation) throws ServiceException {
        try {

            return mapPointsControl.setParameters(signs_list, newDirection, address, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setParameters(int signs_list, String address, String annotation) throws ServiceException {
        try {

            return mapPointsControl.setParameters(signs_list, address, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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
