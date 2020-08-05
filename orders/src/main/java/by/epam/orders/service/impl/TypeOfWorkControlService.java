package by.epam.orders.service.impl;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.ITypeOfWorkControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.dao.factory.DaoFactory;
import by.epam.orders.service.ITypeOfWorkControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;

/**
 * service for working with {@link TypeOfWork}, get, add, set parameters of order
 */
public class TypeOfWorkControlService implements ITypeOfWorkControlService {

    /**
     * {@link ITypeOfWorkControl} instance
     */
    private final ITypeOfWorkControl typeOfWorkControl;


    /**
     * empty constructor
     */
    public TypeOfWorkControlService() {
        typeOfWorkControl = DaoFactory.getINSTANCE().getTypeOfWorkControl();
    }

    /**
     * constructor with set dao for working
     *
     * @param typeOfWorkControlDao {@link ITypeOfWorkControl}
     */
    TypeOfWorkControlService(ITypeOfWorkControl typeOfWorkControlDao) {
        typeOfWorkControl = typeOfWorkControlDao;
    }

    /**
     * add type of work
     *
     * @param name  type of work
     * @param price for execution
     * @return {@link TypeOfWork} if success
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
    @Override
    public TypeOfWork addTypeOfWork(String name, double price) throws ServiceException {

        InputValidation.nullCheck(name);

        try {

            return typeOfWorkControl.addTypeOfWork(name, price);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * set block
     *
     * @param id    where set
     * @param block to set
     * @return true if success
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
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

    /**
     * set price
     *
     * @param id    where set
     * @param price to set
     * @return true if success
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
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

    /**
     * remove type of work
     *
     * @param id what remove
     * @return true if success
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
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


    /**
     * get all types of work
     *
     * @return {@link TypeOfWork} array
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
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

    /**
     * get types  of work where block is false
     *
     * @return {@link TypeOfWork} array
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
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


    /**
     * get type of work with id param
     *
     * @param id id to find
     * @return {@link TypeOfWork}
     * @throws ServiceValidationException when {@link ITypeOfWorkControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ITypeOfWorkControl} throw {@link DAOException}
     */
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
