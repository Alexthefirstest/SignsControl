package by.epam.orders.dao.impl;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.ITypeOfWorControl;
import by.epam.orders.dao.exceptions.DAOException;

import java.sql.SQLException;

public class TypeOfWorkControl implements ITypeOfWorControl {

    private static final String SQL_ADD = "INSERT INTO `type_of_work` (`id`, `type`) VALUES (?, ?);";
    private static final String SQL_SELECT_BY_LAST_INSERT_ID = "SELECT * FROM type_of_work where id = LAST_INSERT_ID()";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM type_of_work where id = ";
    private static final String SQL_DELETE = "DELETE FROM `type_of_work` WHERE (`id` =?);";
    private static final String SQL_SELECT_ALL = "SELECT * FROM type_of_work order by id;";

    @Override
    public TypeOfWork addTypeOfWork(String name) throws DAOException {

        try {

            return (TypeOfWork) RequestExecutor.createFieldUseDifferentParameters(SQL_ADD, SQL_SELECT_BY_LAST_INSERT_ID, new TypeOfWork(), name);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public TypeOfWork removeTypeOfWork(int id) throws DAOException {
        try {

            return (TypeOfWork) RequestExecutor.createFieldUseDifferentParameters(SQL_DELETE, SQL_SELECT_BY_ID + id, new TypeOfWork(), id);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
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
