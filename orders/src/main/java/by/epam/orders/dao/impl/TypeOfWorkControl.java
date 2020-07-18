package by.epam.orders.dao.impl;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.ITypeOfWorkControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;

import java.sql.SQLException;

public class TypeOfWorkControl implements ITypeOfWorkControl {

    private static final String SQL_ADD = "INSERT INTO `type_of_work` (`type`, price) VALUES (?,?);";
    private static final String SQL_SELECT_BY_LAST_INSERT_ID = "SELECT * FROM type_of_work where id = LAST_INSERT_ID()";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM type_of_work where id = ";
    private static final String SQL_DELETE = "DELETE FROM `type_of_work` WHERE (`id` =?);";
    private static final String SQL_SELECT_ALL = "SELECT * FROM type_of_work order by id;";
    private static final String SET_BLOCK = "UPDATE `type_of_work` SET `blocked` = ? WHERE (`id` = ?);";
    private static final String SET_PRICE = "UPDATE `type_of_work` SET `price` = ? WHERE (`id` = ?);";


    @Override
    public TypeOfWork addTypeOfWork(String name, double price) throws DAOException {

        try {

            return (TypeOfWork) RequestExecutor.createFieldUseDifferentParameters(SQL_ADD, SQL_SELECT_BY_LAST_INSERT_ID, new TypeOfWork(), name, price);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setBlock(int id, boolean block) throws DAOException {
        try {
            return RequestExecutor.setField(SET_BLOCK, id, block);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public boolean setPrice(int id, double price) throws DAOException {
        try {
            return RequestExecutor.setField(SET_PRICE, id, price);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }


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

    @Override
    public TypeOfWork[] getTypesOfWork() throws DAOException {


        try {

            return (TypeOfWork[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new TypeOfWork());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }


    }
}
