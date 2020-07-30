package by.epam.orders.dao;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.signsControl.bean.MapPoint;

public interface IOrdersControl {

    /**
     * add order
     *
     * @param signList           sign list id to add
     * @param sign               sign to add
     * @param sign_standard_size standard size to add
     * @param customer           customer id to add
     * @param typeOfWork         type of work id to add
     * @param info               info to add
     * @return object if success
     * @throws DAOException when catch exception during execution
     */
    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws DAOException;

    /**
     * add order
     *
     * @param signList           sign list id to add
     * @param sign               sign to add
     * @param sign_standard_size standard size to add
     * @param customer           customer id to add
     * @param typeOfWork         type of work id to add
     * @param info               info to add
     * @param transaction        transaction id
     * @return object if success
     * @throws DAOException when catch exception during execution
     */
    Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws DAOException;

    /**
     * set transaction id to order
     *
     * @param orderID       order id
     * @param transactionID transaction id
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */
    boolean setTransaction(int orderID, int transactionID) throws DAOException;

    /**
     * set workers crew id
     *
     * @param orderID       order id
     * @param workersCrewID id
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */


    boolean setWorkersCrew(int orderID, int workersCrewID) throws DAOException;

    /**
     * set date of execution
     *
     * @param orderID         order id
     * @param dateOfExecution date of execution to add
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */

    boolean setDateOfExecution(int orderID, String dateOfExecution) throws DAOException;

    /**
     * remove order from table
     *
     * @param orderID to remove
     * @return true if success
     * @throws DAOException when catch exception during execution
     */

    boolean removeOrder(int orderID) throws DAOException;

    /**
     * get order
     *
     * @param orderID to return order
     * @return {@link Order}
     * @throws DAOException when catch exception during execution
     */
    Order getOrder(int orderID) throws DAOException;

    /**
     * @param orderID sign id where set
     * @param info    info to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception during execution
     */
    boolean setInfo(int orderID, String info) throws DAOException;

    /**
     * get info
     *
     * @param orderID order to get
     * @return info order info
     * @throws DAOException when catch exception during execution
     */
    String getInfo(int orderID) throws DAOException;

    /**
     * get all orders
     *
     * @return {@link Order} array
     * @throws DAOException when catch exception during execution
     */
    Order[] getOrders() throws DAOException;


    /**
     * get orders with organisation performer id
     *
     * @param organisationPerformerID orginisation to find order
     * @return {@link Order} array
     * @throws DAOException when catch exception during execution
     */
    Order[] getOrders(int organisationPerformerID) throws DAOException;

    /**
     * get  executed orders where date of execution is null
     *
     * @return {@link Order} array
     * @throws DAOException when catch exception during execution
     */
    Order[] getExecutedOrders() throws DAOException;

    /**
     * get  orders with date of execution is null
     *
     * @return {@link Order} array
     * @throws DAOException when catch exception during execution
     */
    Order[] getUnExecutedOrders() throws DAOException;

    /**
     * get all  orders combine with map point
     *
     * @return {@link MapPoint$Orders} array
     * @throws DAOException when catch exception during execution
     */
    MapPoint$Orders[] getOrdersMapPoint() throws DAOException;

    /**
     * get  orders with date of execution is null with coordinates
     *
     * @param coordinates map point coordinates
     * @return {@link Order} array
     * @throws DAOException when catch exception during execution
     */
    Order[] getUnExecutedOrders(String coordinates) throws DAOException;

    /**
     * get  orders combine with map point with date of execution is not null
     *
     * @return {@link MapPoint$Orders} array
     * @throws DAOException when catch exception during execution
     */
    MapPoint$Orders[] getExecutedOrdersMapPoint() throws DAOException;

    /**
     * get  orders with date of execution is null
     *
     * @return {@link MapPoint$Orders} array
     * @throws DAOException when catch exception during execution
     */
    MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws DAOException;


}
