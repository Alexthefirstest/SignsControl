package by.epam.orders.service;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.service.exceptions.ServiceException;

public interface ITypeOfWorkControlService {

    TypeOfWork addTypeOfWork(String name) throws ServiceException;

    TypeOfWork removeTypeOfWork(int id) throws ServiceException;

    TypeOfWork[] getTypesOfWork() throws ServiceException;

}
