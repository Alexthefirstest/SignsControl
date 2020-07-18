package by.epam.orders.dao;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.exceptions.DAOException;

public interface ITypeOfWorkControl {

    TypeOfWork addTypeOfWork(String name, double price) throws DAOException;

    boolean setBlock(int id, boolean block) throws DAOException;
    boolean setPrice(int id, double price) throws DAOException;

    boolean removeTypeOfWork(int id) throws DAOException;

    TypeOfWork[] getTypesOfWork() throws DAOException;

}
