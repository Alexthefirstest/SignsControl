package by.epam.orders.dao;

import by.epam.orders.bean.Order;
import by.epam.orders.dao.exceptions.DAOException;

public interface IOrdersControl {


    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws DAOException;


    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws DAOException;

    Boolean setTransaction(int orderID, int transactionID) throws DAOException;

    Boolean setWorkersCrew(int orderID, int workersCrewID) throws DAOException;

    Boolean setDateOfExecution(int orderID, String dateOfExecution) throws DAOException;

    Boolean removeOrder(int orderID) throws DAOException;

    Boolean setInfo(int orderID, String info) throws DAOException;

    Order[] getOrders() throws DAOException;
    Order[] getExecutedOrders() throws DAOException;
    Order[] getUnExecutedOrders() throws DAOException;


}
