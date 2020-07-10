package by.epam.orders.dao;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.dao.exceptions.DAOException;

public interface ITypeOfWorControl {

    TypeOfWork addTypeOfWork(String name) throws DAOException;

    TypeOfWork removeTypeOfWork(int id) throws DAOException;

    TypeOfWork[] getTypesOfWork() throws DAOException;

}
