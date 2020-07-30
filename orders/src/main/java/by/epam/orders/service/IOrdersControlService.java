package by.epam.orders.service;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.service.exceptions.ServiceException;


/**
 * service for working with {@link Order}, get, add, set parameters of orders
 */
public interface IOrdersControlService {

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
     * @throws ServiceException when get an exception during execution
     */
    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws ServiceException;

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
     * @throws ServiceException when get an exception during execution
     */
    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws ServiceException;

    /**
     * @param orderID       order id to set
     * @param transactionID transaction to set
     * @return true if success or false in other option
     * @throws ServiceException when get an exception during execution
     */
    boolean setTransaction(int orderID, int transactionID) throws ServiceException;

    /**
     * @param orderID       order id to set
     * @param workersCrewID to set
     * @return true if success or false in other option
     * @throws ServiceException when get an exception during execution
     */
    boolean setWorkersCrew(int orderID, int workersCrewID) throws ServiceException;

    /**
     * @param orderID         order id to set
     * @param dateOfExecution date of execution to set
     * @return true if success or false in other option
     * @throws ServiceException when get an exception during execution
     */
    boolean setDateOfExecution(int orderID, String dateOfExecution) throws ServiceException;

    /**
     * @param orderID order id to remove
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean removeOrder(int orderID) throws ServiceException;


    /**
     * @param orderID order id to find
     * @return {@link Order} if success
     * @throws ServiceException when get an exception during execution
     */
    Order getOrder(int orderID) throws ServiceException;

    /**
     * @param orderID order id to set
     * @param info    info to set
     * @return true if success or false in other option
     * @throws ServiceException when get an exception during execution
     */
    boolean setInfo(int orderID, String info) throws ServiceException;

    /**
     * get all orders
     *
     * @return {@link Order} array
     * @throws ServiceException when get an exception during execution
     */
    Order[] getOrders() throws ServiceException;


    /**
     * get orders with organisation performer id
     *
     * @param organisationPerformerID id to find
     * @return {@link Order} array
     * @throws ServiceException when get an exception during execution
     */
    Order[] getOrders(int organisationPerformerID) throws ServiceException;

    /**
     * get executed orders - where date of execution is not null
     *
     * @return {@link Order} array
     * @throws ServiceException when get an exception during execution
     */
    Order[] getExecutedOrders() throws ServiceException;

    /**
     * get un executed orders - where date of execution is null
     *
     * @return {@link Order} array
     * @throws ServiceException when get an exception during execution
     */
    Order[] getUnExecutedOrders() throws ServiceException;


    /**
     * get info
     *
     * @param orderID id to find info
     * @return info
     * @throws ServiceException when get an exception during execution
     */
    String getInfo(int orderID) throws ServiceException;

    /**
     * get all orders combined with map points
     *
     * @return {@link MapPoint$Orders} array
     * @throws ServiceException when get an exception during execution
     */
    MapPoint$Orders[] getOrdersMapPoint() throws ServiceException;

    /**
     * get un executed orders - where date of execution is null
     *
     * @param point point coordinates to find
     * @return {@link Order} array
     * @throws ServiceException when get an exception during execution
     */
    Order[] getUnExecutedOrders(String point) throws ServiceException;

    /**
     * get executed orders combined with map points - where date of execution is not null
     *
     * @return {@link MapPoint$Orders} array
     * @throws ServiceException when get an exception during execution
     */
    MapPoint$Orders[] getExecutedOrdersMapPoint() throws ServiceException;

    /**
     * get un executed orders combined with map point - where date of execution is null
     *
     * @return {@link MapPoint$Orders} array
     * @throws ServiceException when get an exception during execution
     */
    MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws ServiceException;
}
