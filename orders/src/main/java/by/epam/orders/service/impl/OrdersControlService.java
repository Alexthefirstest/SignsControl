package by.epam.orders.service.impl;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.dao.IOrdersControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.dao.factory.DaoFactory;
import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;

public class OrdersControlService implements IOrdersControlService {

    private static final IOrdersControl ordersControl = DaoFactory.getINSTANCE().getOrdersControl();

    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws ServiceException {

        try {

            return ordersControl.addOrder(signList, sign, sign_standard_size, customer, typeOfWork, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws ServiceException {

        try {

            return ordersControl.addOrder(signList, sign, sign_standard_size, customer, typeOfWork, info, transaction);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Boolean setTransaction(int orderID, int transactionID) throws ServiceException {

        try {

            return ordersControl.setTransaction(orderID, transactionID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Boolean setWorkersCrew(int orderID, int workersCrewID) throws ServiceException {

        try {

            return ordersControl.setWorkersCrew(orderID, workersCrewID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Boolean setDateOfExecution(int orderID, String dateOfExecution) throws ServiceException {
        try {

            return ordersControl.setDateOfExecution(orderID, dateOfExecution);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Boolean removeOrder(int orderID) throws ServiceException {

        try {

            return ordersControl.removeOrder(orderID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Boolean setInfo(int orderID, String info) throws ServiceException {
        try {

            return ordersControl.setInfo(orderID, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Order[] getOrders() throws ServiceException {

        try {

            return ordersControl.getOrders();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Order[] getExecutedOrders() throws ServiceException {

        try {

            return ordersControl.getExecutedOrders();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Order[] getUnExecutedOrders() throws ServiceException {

        try {

            return ordersControl.getUnExecutedOrders();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public MapPoint$Orders[] getOrdersMapPoint() throws ServiceException {
        try {

            return ordersControl.getOrdersMapPoint();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public MapPoint$Orders[] getExecutedOrdersMapPoint() throws ServiceException {
        try {

            return ordersControl.getExecutedOrdersMapPoint();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws ServiceException {
        try {

            return ordersControl.getUnExecutedOrdersMapPoint();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
