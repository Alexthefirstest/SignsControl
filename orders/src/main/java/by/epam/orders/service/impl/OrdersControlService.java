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

/**
 * service for working with {@link Order}, get, add, set parameters of order
 */
public class OrdersControlService implements IOrdersControlService {

    /**
     * {@link IOrdersControl} instance
     */
    private final IOrdersControl ordersControl;

    /**
     * empty constructor
     */
    public OrdersControlService() {
        ordersControl = DaoFactory.getINSTANCE().getOrdersControl();
    }

    /**
     * constructor with set dao for working
     *
     * @param ordersControlDao {@link IOrdersControl}
     */
    OrdersControlService(IOrdersControl ordersControlDao) {
        ordersControl = ordersControlDao;
    }

    /**
     * add order
     *
     * @param signList           sign list id to add
     * @param sign               sign id
     * @param sign_standard_size standard size id
     * @param customer           customer id
     * @param typeOfWork         type of work id
     * @param info               order info
     * @return {@link Order} if success
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return ordersControl.addOrder(signList, sign, sign_standard_size, customer, typeOfWork, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * add order
     *
     * @param signList           sign list id to add
     * @param sign               sign id
     * @param sign_standard_size standard size id
     * @param customer           customer id
     * @param typeOfWork         type of work id
     * @param info               order info
     * @param transaction        transaction id
     * @return {@link Order} if success
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return ordersControl.addOrder(signList, sign, sign_standard_size, customer, typeOfWork, info, transaction);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * @param orderID       order id to set
     * @param transactionID transaction to set
     * @return true if success or false in other option
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public boolean setTransaction(int orderID, int transactionID) throws ServiceException {

        try {

            return ordersControl.setTransaction(orderID, transactionID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * @param orderID       order id to set
     * @param workersCrewID to set
     * @return true if success or false in other option
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public boolean setWorkersCrew(int orderID, int workersCrewID) throws ServiceException {

        try {

            return ordersControl.setWorkersCrew(orderID, workersCrewID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * @param orderID         order id to set
     * @param dateOfExecution date of execution to set
     * @return true if success or false in other option
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfExecution(int orderID, String dateOfExecution) throws ServiceException {

        InputValidation.nullAndDateCheck(dateOfExecution);

        try {

            return ordersControl.setDateOfExecution(orderID, dateOfExecution);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * @param orderID order id to remove
     * @return true if success
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public boolean removeOrder(int orderID) throws ServiceException {

        try {

            return ordersControl.removeOrder(orderID);

        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * @param orderID order id to find
     * @return {@link Order} if success
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public Order getOrder(int orderID) throws ServiceException {

        try {

            return ordersControl.getOrder(orderID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * @param orderID order id to set
     * @param info    info to set
     * @return true if success or false in other option
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public boolean setInfo(int orderID, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return ordersControl.setInfo(orderID, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * get all orders
     *
     * @return {@link Order} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
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


    /**
     * get orders with organisation performer id
     *
     * @param organisationPerformerID id to find
     * @return {@link Order} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public Order[] getOrders(int organisationPerformerID) throws ServiceException {
        try {

            return ordersControl.getOrders(organisationPerformerID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * get executed orders - where date of execution is not null
     *
     * @return {@link Order} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
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


    /**
     * get un executed orders - where date of execution is null
     *
     * @return {@link Order} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
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


    /**
     * get info
     *
     * @param orderID id to find info
     * @return info
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public String getInfo(int orderID) throws ServiceException {
        try {

            return ordersControl.getInfo(orderID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * get all orders combined with map points
     *
     * @return {@link MapPoint$Orders} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
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


    /**
     * get un executed orders - where date of execution is null
     *
     * @param point point coordinates to find
     * @return {@link Order} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
    @Override
    public Order[] getUnExecutedOrders(String point) throws ServiceException {

        InputValidation.pointCheck(point);

        try {

            return ordersControl.getUnExecutedOrders(point);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


    /**
     * get executed orders combined with map points - where date of execution is not null
     *
     * @return {@link MapPoint$Orders} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
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


    /**
     * get un executed orders combined with map point - where date of execution is null
     *
     * @return {@link MapPoint$Orders} array
     * @throws ServiceValidationException when {@link IOrdersControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IOrdersControl} throw {@link DAOException}
     */
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
