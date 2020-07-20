package by.epam.orders.service;

import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.service.exceptions.ServiceException;

public interface ITypeOfWorkControlService {

    TypeOfWork addTypeOfWork(String name, double price) throws ServiceException;

    boolean setBlock(int id, boolean block) throws ServiceException;

    boolean setPrice(int id, double price) throws ServiceException;

    boolean removeTypeOfWork(int id) throws ServiceException;

    TypeOfWork[] getTypesOfWork() throws ServiceException;

    TypeOfWork[] getUnblockedTypesOfWork() throws ServiceException;
    TypeOfWork getTypeOfWork(int id) throws ServiceException;


}