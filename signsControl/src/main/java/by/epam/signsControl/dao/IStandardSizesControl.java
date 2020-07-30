package by.epam.signsControl.dao;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.exceptions.DAOException;

/**
 * Interface for jdbc direction fields
 */
public interface IStandardSizesControl {

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws DAOException when exception occur during execution
     */
    StandardSize addStandardSize(int size) throws DAOException;

    /**
     * sql add standard size to sql table
     *
     * @param size size to add
     * @param info info to add
     * @return {@link StandardSize} if success or null if duplicate entry
     * @throws DAOException when exception occur during execution
     */
    StandardSize addStandardSize(int size, String info) throws DAOException;

    /**
     * remove st size
     *
     * @param size size to remove
     * @return true if success
     * @throws DAOException when exception occur during execution
     */
    boolean removeStandardSize(int size) throws DAOException;

    /**
     * set info
     *
     * @param id   size to set
     * @param info to set
     * @return true if success or false in not
     * @throws DAOException when exception occur during execution
     */
    boolean setInfo(int id, String info) throws DAOException;

    /**
     * set info
     *
     * @param oldSize size to set
     * @param newSize new size
     * @return true if success or false in not
     * @throws DAOException when exception occur during execution
     */
    boolean setSize(int oldSize, int newSize) throws DAOException;

    /**
     * get info
     *
     * @param id size to get
     * @return info
     * @throws DAOException when exception occur during execution
     */
    String getInfo(int id) throws DAOException;

    /**
     * get all standard sizes
     *
     * @return {@link StandardSize} array
     * @throws DAOException when exception occur during execution
     */
    StandardSize[] getStandardSizes() throws DAOException;

}
