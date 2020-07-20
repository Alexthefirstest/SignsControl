package by.epam.orders.dao;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.signsControl.bean.MapPoint;

public interface IOrdersControl {


    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws DAOException;


    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws DAOException;

    Boolean setTransaction(int orderID, int transactionID) throws DAOException;

    Boolean setWorkersCrew(int orderID, int workersCrewID) throws DAOException;

    Boolean setDateOfExecution(int orderID, String dateOfExecution) throws DAOException;

    Boolean removeOrder(int orderID) throws DAOException;
    Order getOrder(int orderID) throws DAOException;

    Boolean setInfo(int orderID, String info) throws DAOException;

    String getInfo(int orderID) throws DAOException;

    Order[] getOrders() throws DAOException;
    Order[] getOrders(int organisationPerformerID) throws DAOException;

    Order[] getExecutedOrders() throws DAOException;

    Order[] getUnExecutedOrders() throws DAOException;

    MapPoint$Orders[] getOrdersMapPoint() throws DAOException;

    Order[] getUnExecutedOrders(String coordinates) throws DAOException;

    MapPoint$Orders[] getExecutedOrdersMapPoint() throws DAOException;

    MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws DAOException;


}
