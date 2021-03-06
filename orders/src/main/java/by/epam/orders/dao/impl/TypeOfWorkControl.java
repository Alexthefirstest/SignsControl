package by.epam.orders.dao.impl;

import by.epam.orders.bean.FactoryType;
import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.ITypeOfWorkControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;

import java.sql.SQLException;

/**
 * class for type of work mysql field
 */
public class TypeOfWorkControl implements ITypeOfWorkControl {

    /**
     * add type of work
     */
    private static final String SQL_ADD = "INSERT INTO `type_of_work` (`type`, price) VALUES (?,?);";

    /**
     * select use last inserted id
     */
    private static final String SQL_SELECT_BY_LAST_INSERT_ID = "SELECT * FROM type_of_work where id = LAST_INSERT_ID()";

    /**
     * select by id
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM type_of_work where id = ";

    /**
     * delete type of work
     */
    private static final String SQL_DELETE = "DELETE FROM `type_of_work` WHERE (`id` =?);";

    /**
     * select all
     */
    private static final String SQL_SELECT_ALL = "SELECT * FROM type_of_work order by id;";

    /**
     * select where block is false
     */
    private static final String SQL_SELECT_UNBLOCKED = "SELECT * FROM type_of_work where blocked=0 order by id ";

    /**
     * set block
     */
    private static final String SET_BLOCK = "UPDATE `type_of_work` SET `blocked` = ? WHERE (`id` = ?);";

    /**
     * set price
     */
    private static final String SET_PRICE = "UPDATE `type_of_work` SET `price` = ? WHERE (`id` = ?);";

    /**
     * add type of work
     *
     * @param name  name
     * @param price price
     * @return object if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public TypeOfWork addTypeOfWork(String name, double price) throws DAOException {

        try {

            return (TypeOfWork) RequestExecutor.createFieldUseDifferentParameters(SQL_ADD, SQL_SELECT_BY_LAST_INSERT_ID, new TypeOfWork(), name, price);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * @param id    int id of type of work to set
     * @param block block condition
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, boolean)}
     */
    @Override
    public boolean setBlock(int id, boolean block) throws DAOException {

        try {
            return RequestExecutor.setField(SET_BLOCK, id, block);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * @param id    int id of type of work to set
     * @param price price
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, double)}
     */
    @Override
    public boolean setPrice(int id, double price) throws DAOException {
        try {
            return RequestExecutor.setField(SET_PRICE, id, price);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * @param id of type to remove
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public boolean removeTypeOfWork(int id) throws DAOException {
        try {

            RequestExecutor.createFieldUseDifferentParameters(SQL_DELETE, SQL_SELECT_BY_ID + id, new TypeOfWork(), id);

        } catch (DAOValidationException ignored) {

            return true;

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
        return false;
    }

    /**
     * get all type of works
     *
     * @return {@link TypeOfWork} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public TypeOfWork[] getTypesOfWork() throws DAOException {


        try {

            return (TypeOfWork[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new TypeOfWork());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }


    }

    /**
     * get type of works where block condition if false
     *
     * @return {@link TypeOfWork} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public TypeOfWork[] getUnblockedTypesOfWork() throws DAOException {


        try {

            return (TypeOfWork[]) RequestExecutor.getSignsStaff(SQL_SELECT_UNBLOCKED, new TypeOfWork());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }


    }

    /**
     * get type of work by id
     *
     * @return {@link TypeOfWork}
     * @throws DAOException when catch exception from {@link RequestExecutor#getOneSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public TypeOfWork getTypeOfWork(int id) throws DAOException {


        try {

            return (TypeOfWork) RequestExecutor.getOneSignsStaff(SQL_SELECT_BY_ID + id, new TypeOfWork());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }


    }
}
