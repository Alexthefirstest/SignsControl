package by.epam.orders.service.impl;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.ITypeOfWorkControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.dao.factory.DaoFactory;
import by.epam.orders.service.ITypeOfWorkControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;

public class TypeOfWorkControlService implements ITypeOfWorkControlService {

    private static final ITypeOfWorkControl typeOfWorkControl = DaoFactory.getINSTANCE().getTypeOfWorkControl();

    @Override
    public TypeOfWork addTypeOfWork(String name, double price) throws ServiceException {
        try {

            return typeOfWorkControl.addTypeOfWork(name, price);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setBlock(int id, boolean block) throws ServiceException {
        try {

            return typeOfWorkControl.setBlock(id, block);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setPrice(int id, double price) throws ServiceException {
        try {

            return typeOfWorkControl.setPrice(id, price);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean removeTypeOfWork(int id) throws ServiceException {
        try {

            return typeOfWorkControl.removeTypeOfWork(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public TypeOfWork[] getTypesOfWork() throws ServiceException {
        try {

            return typeOfWorkControl.getTypesOfWork();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public TypeOfWork[] getUnblockedTypesOfWork() throws ServiceException {
        try {

            return typeOfWorkControl.getUnblockedTypesOfWork();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public TypeOfWork getTypeOfWork(int id) throws ServiceException {
        try {

            return typeOfWorkControl.getTypeOfWork(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
