package by.epam.signsControl.service;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.service.exceptions.ServiceException;

public interface IStandardSizesControlService {

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws ServiceException when get an exception during execution
     */
    StandardSize addStandardSize(int size) throws ServiceException;

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @param info info to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws ServiceException when get an exception during execution
     */
    StandardSize addStandardSize(int size, String info) throws ServiceException;

    /**
     * remove st size
     *
     * @param size size to remove
     * @return null if success
     * @throws ServiceException when get an exception during execution
     */
    boolean removeStandardSize(int size) throws ServiceException;

    /**
     * set info
     *
     * @param id   size to set
     * @param info to set
     * @return true if success or false in not
     * @throws ServiceException when get an exception during execution
     */
    boolean setInfo(int id, String info) throws ServiceException;

    /**
     * set info
     *
     * @param oldSize size to set
     * @param newSize new size
     * @return true if success or false in not
     * @throws ServiceException when get an exception during execution
     */
    boolean setSize(int oldSize, int newSize) throws ServiceException;

    /**
     * get info
     *
     * @param id size to get
     * @return info
     * @throws ServiceException when get an exception during execution
     */
    String getInfo(int id) throws ServiceException;

    /**
     * get all standard sizes
     *
     * @return {@link StandardSize} array
     * @throws ServiceException when get an exception during execution
     */
    StandardSize[] getStandardSizes() throws ServiceException;

}
