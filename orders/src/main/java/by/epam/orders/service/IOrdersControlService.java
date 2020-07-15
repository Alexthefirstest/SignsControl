package by.epam.orders.service;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.service.exceptions.ServiceException;

public interface IOrdersControlService {

    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws ServiceException;


    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws ServiceException;

    Boolean setTransaction(int orderID, int transactionID) throws ServiceException;

    Boolean setWorkersCrew(int orderID, int workersCrewID) throws ServiceException;

    Boolean setDateOfExecution(int orderID, String dateOfExecution) throws ServiceException;

    Boolean removeOrder(int orderID) throws ServiceException;

    Boolean setInfo(int orderID, String info) throws ServiceException;

    Order[] getOrders() throws ServiceException;

    Order[] getExecutedOrders() throws ServiceException;

    Order[] getUnExecutedOrders() throws ServiceException;

    MapPoint$Orders[] getOrdersMapPoint() throws ServiceException;
    Order[] getOrdersMapPoint(String coordinates) throws ServiceException;

    MapPoint$Orders[] getExecutedOrdersMapPoint() throws ServiceException;

    MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws ServiceException;
}
