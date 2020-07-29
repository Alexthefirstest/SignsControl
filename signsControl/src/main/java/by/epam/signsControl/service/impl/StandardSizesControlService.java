package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.IStandardSizesControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

/**
 * class for communication with dao layer
 */
public class StandardSizesControlService implements IStandardSizesControlService {

    /**
     * {@link IStandardSizesControl} instance
     */
    private final IStandardSizesControl standardSizesControl = DaoFactory.getINSTANCE().getStandardSizesControl();

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @param info info to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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

    /**
     * remove st size
     *
     * @param size size to remove
     * @return null if success
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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

    /**
     * set info
     *
     * @param id   size to set
     * @param info to set
     * @return true if success or false in not
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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

    /**
     * set info
     *
     * @param oldSize size to set
     * @param newSize new size
     * @return true if success or false in not
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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

    /**
     * get info
     *
     * @param id size to get
     * @return info
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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

    /**
     * get all standard sizes
     *
     * @return {@link StandardSize} array
     * @throws ServiceValidationException when {@link IStandardSizesControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IStandardSizesControl} throw {@link DAOException}
     */
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
