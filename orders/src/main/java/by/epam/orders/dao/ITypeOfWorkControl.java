package by.epam.orders.dao;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.exceptions.DAOException;

public interface ITypeOfWorkControl {

    /**
     * add type of work
     *
     * @param name  name
     * @param price price
     * @return object if success
     * @throws DAOException when catch exception during execution
     */
    TypeOfWork addTypeOfWork(String name, double price) throws DAOException;

    /**
     * @param id    int id of type of work to set
     * @param block block condition
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */
    boolean setBlock(int id, boolean block) throws DAOException;

    /**
     * @param id    int id of type of work to set
     * @param price price
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */
    boolean setPrice(int id, double price) throws DAOException;

    /**
     * @param id of type to remove
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */
    boolean removeTypeOfWork(int id) throws DAOException;

    /**
     * get all type of works
     *
     * @return {@link TypeOfWork} array
     * @throws DAOException when catch exception during execution
     */
    TypeOfWork[] getTypesOfWork() throws DAOException;

    /**
     * get type of works where block condition if false
     *
     * @return {@link TypeOfWork} array
     * @throws DAOException when catch exception during execution
     */
    TypeOfWork[] getUnblockedTypesOfWork() throws DAOException;

    /**
     * get type of work by id
     *
     * @return {@link TypeOfWork}
     * @throws DAOException when catch exception during execution
     */
    TypeOfWork getTypeOfWork(int id) throws DAOException;

}
