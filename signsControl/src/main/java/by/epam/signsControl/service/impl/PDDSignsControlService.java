package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.IPDDSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

import java.io.InputStream;

public class PDDSignsControlService implements IPDDSignsControlService {

    private final IPDDSignsControl pddSignsControl = DaoFactory.getINSTANCE().getPddSignsControl();


    @Override
    public Sign addSign(int section, int number, int kind, String name, String description) throws ServiceException {
        try {

            return pddSignsControl.addSign(section, number, kind, name, description);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Sign addSign(int section, int number, int kind, String name) throws ServiceException {
        try {

            return pddSignsControl.addSign(section, number, kind, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Sign addSign(int section, int number, String name) throws ServiceException {
        try {

            return pddSignsControl.addSign(section, number, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean removeSign(int id) throws ServiceException {
        try {

            return pddSignsControl.removeSign(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean updateSection(int id, int section) throws ServiceException {
        try {

            return pddSignsControl.updateSection(id, section);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean updateNumber(int id, int number) throws ServiceException {
        try {

            return pddSignsControl.updateNumber(id, number);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean updateKind(int id, int kind) throws ServiceException {
        try {

            return pddSignsControl.updateKind(id, kind);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setPicture(int id, InputStream inputStream, long imageSize) throws ServiceException {

        InputValidation.nullCheck(inputStream);

        try {

            return pddSignsControl.setPicture(id, inputStream, imageSize);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public byte[] getPicture(int id) throws ServiceException {
        try {

            return pddSignsControl.getPicture(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean updateName(int id, String name) throws ServiceException {
        try {

            return pddSignsControl.updateName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean updateDescription(int id, String info) throws ServiceException {
        try {

            return pddSignsControl.updateDescription(id, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Sign[] getPddSigns() throws ServiceException {
        try {

            return pddSignsControl.getPddSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Sign[] getPddSigns(int section) throws ServiceException {
        try {

            return pddSignsControl.getPddSigns(section);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Sign[] getPddSigns(int section, int number) throws ServiceException {
        try {

            return pddSignsControl.getPddSigns(section, number);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
