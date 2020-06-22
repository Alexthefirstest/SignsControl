package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

import java.util.Date;

public class LocalSignsControlService implements ILocalSignsControlService {

    private final ILocalSignsControl localSignsControl = DaoFactory.getINSTANCE().getLocalSignsControl();


    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize) throws ServiceException {

        try {

            return localSignsControl.addSign(signListId, pddSignId, standardSize);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws ServiceException {

        InputValidation.nullCheck(annotation);

        try {

            return localSignsControl.addSign(signListId, pddSignId, standardSize, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean deleteSign(int signId) throws ServiceException {
        try {

            return localSignsControl.deleteSign(signId);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public MapPoint$LocalSign[] getActualMapPoints$LocalSigns() throws ServiceException {
        try {

            return localSignsControl.getActualMapPoints$LocalSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public MapPoint$LocalSign[] getAllMapPoints$LocalSigns() throws ServiceException {
        try {

            return localSignsControl.getAllMapPoints$LocalSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public LocalSign[] getActualSigns() throws ServiceException {
        try {

            return localSignsControl.getActualSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public LocalSign[] getActualSigns(int signsListID, String date) throws ServiceException {

        InputValidation.nullAndDateCheck(date);

        try {

            return localSignsControl.getActualSigns(signsListID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public LocalSign[] getSignsLists() throws ServiceException {
        try {

            return localSignsControl.getSignsLists();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setDateOfAdd(int localSignID, Date date) throws ServiceException {

        InputValidation.nullCheck(date);

        try {

            return localSignsControl.setDateOfAdd(localSignID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setDateOfRemove(int localSignID, Date date) throws ServiceException {
        InputValidation.nullCheck(date);

        try {

            return localSignsControl.setDateOfRemove(localSignID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setAnnotation(int localSignID, String annotation) throws ServiceException {

        InputValidation.nullCheck(annotation);

        try {

            return localSignsControl.setAnnotation(localSignID, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
