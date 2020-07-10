package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.IStandardSizesControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

public class StandardSizesControlService implements IStandardSizesControlService {

    private final IStandardSizesControl standardSizesControl = DaoFactory.getINSTANCE().getStandardSizesControl();

    @Override
    public StandardSize addStandardSize(int size) throws ServiceException {
        try {

            return standardSizesControl.addStandardSize(size);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public StandardSize addStandardSize(int size, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return standardSizesControl.addStandardSize(size, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean removeStandardSize(int size) throws ServiceException {
        try {

            return standardSizesControl.removeStandardSize(size);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setInfo(int id, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return standardSizesControl.setInfo(id, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setSize(int oldSize, int newSize) throws ServiceException {
        try {

            return standardSizesControl.setSize(oldSize, newSize);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public String getInfo(int id) throws ServiceException {
        try {

            return standardSizesControl.getInfo(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public StandardSize[] getStandardSizes() throws ServiceException {
        try {

            return standardSizesControl.getStandardSizes();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
