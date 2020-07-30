package by.epam.orders.dao.impl;

import by.epam.connectionPoolForDataBase.connectionPool.IConnectionPool;
import by.epam.orders.bean.FactoryType;
import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.dao.IOrdersControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * class for orders field
 */
public class OrdersControl implements IOrdersControl {

    /**
     * add order sql request
     */
    private static final String ADD_ORDER =
            "INSERT INTO `orders` (`sign_list`, `sign`, `sign_standard_size`, `customer`, `type_of_work`, info) VALUES (?,?,?,?,?,?);";

    /**
     * add order with transaction request
     */
    private static final String ADD_ORDER_WITH_TRANSACTION =
            "INSERT INTO `orders` (`sign_list`, `sign`, `sign_standard_size`, `customer`, `type_of_work`, info, transaction) VALUES (?,?,?,?,?,?, ?);";

    /**
     * set transaction request
     */
    private static final String SET_TRANSACTION = "UPDATE `orders` SET `transaction` = ? WHERE (`id` = ?)";

    /**
     * set workers crew request
     */
    private static final String SET_WORKERS_CREW = "UPDATE `orders` SET `workers_crew` = ? WHERE (`id` = ?)";

    /**
     * set date of execution
     */
    private static final String SET_DATE_OF_EXECUTION = "UPDATE `orders` SET `date_of_execution` = ? WHERE (`id` = ?)";

    /**
     * remove order from jdbc
     */
    private static final String REMOVE_ORDER = "DELETE FROM `orders` WHERE (`id` = ?);";

    /**
     * set info
     */
    private static final String SET_INFO = "UPDATE `orders` SET `info` = ? WHERE (`id` = ?)";

    /**
     * get info
     */
    private static final String GET_INFO = "SELECT info FROM orders where id = ?";

    /**
     * get all orders
     */
    private static final String GET_ORDERS =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "order by ord.id DESC";

    /**
     * get orders with performers id
     */
    private static final String GET_ORDERS_BY_PERFORMER =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    " where tr.to=? order by ord.id DESC";

    /**
     * get orders by id
     */
    private static final String GET_ORDERS_BY_ID =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.id =";

    /**
     * get orders with executed date is null
     */
    private static final String GET_UNEXECUTED_ORDERS_BY_COORDINATES =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "join map_points as mp on mp.signs_list = ord.sign_list " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where  mp.coordinates=st_geomfromtext(?) AND ord.date_of_execution is null " +
                    "order by ord.id DESC";

    /**
     * get orders combine with map point
     */
    private static final String GET_ORDERS_MAP_POINT =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew,  " +
                    " ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description,  " +
                    " o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info,  " +
                    " tr.id, tr.money,  " +
                    "tow.id,  " +
                    "tow.type, " +
                    "ST_AsText(mp.coordinates) as coords, mp.address, directions.direction, mp.annotation, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign  " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id  " +
                    "join map_points as mp on mp.signs_list = ord.sign_list join directions on mp.direction=directions.id  " +
                    " left join transactions as tr on ord.transaction = tr.id  " +
                    "join type_of_work as tow on tow.id = ord.type_of_work  " +
                    "order by mp.coordinates, mp.signs_list, ord.id DESC";

    /**
     * get orders with date of execution is not null
     */
    private static final String GET_EXECUTED_ORDERS =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.date_of_execution is not null " +
                    "order by ord.id DESC";

    /**
     * get orders combine with map point with date of order is not null
     */
    private static final String GET_EXECUTED_ORDERS_MAP_POINT =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew,  " +
                    " ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description,  " +
                    " o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info,  " +
                    " tr.id, tr.money,  " +
                    "tow.id,  " +
                    "tow.type, " +
                    "ST_AsText(mp.coordinates) as coords, mp.address, directions.direction, mp.annotation, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign  " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id  " +
                    "join map_points as mp on mp.signs_list = ord.sign_list join directions on mp.direction=directions.id  " +
                    " left join transactions as tr on ord.transaction = tr.id  " +
                    "join type_of_work as tow on tow.id = ord.type_of_work  " +
                    "where ord.date_of_execution is not null " +
                    "order by mp.coordinates, mp.signs_list, ord.id DESC";

    /**
     * get orders  with date of order is null
     */
    private static final String GET_UNEXECUTED_ORDERS =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.date_of_execution is null " +
                    "order by ord.id DESC";

    /**
     * get orders combine with map point with date of order is null
     */
    private static final String GET_UNEXECUTED_ORDERS_MAP_POINT =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew,  " +
                    " ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description,  " +
                    " o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info,  " +
                    " tr.id, tr.money,  " +
                    "tow.id,  " +
                    "tow.type, " +
                    "ST_AsText(mp.coordinates) as coords, mp.address, directions.direction, mp.annotation, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign  " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id  " +
                    "join map_points as mp on mp.signs_list = ord.sign_list join directions on mp.direction=directions.id  " +
                    " left join transactions as tr on ord.transaction = tr.id  " +
                    "join type_of_work as tow on tow.id = ord.type_of_work  " +
                    "where ord.date_of_execution is null " +
                    "order by mp.coordinates, mp.signs_list, ord.id DESC";

    /**
     * get oder with last inserted id
     */
    private static final String GET_ORDERS_LAST_INSERTED_ID =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type, " +
                    "tow.price, " +
                    "tow.blocked " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.id=LAST_INSERT_ID()";

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
     * @throws DAOValidationException if wrong sign ist, wong sign, wrong standard size, wrong customer, wrong type of work, string is null
     * @throws DAOException           when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info) throws DAOException {
        try {

            return (Order) RequestExecutor.createFieldUseDifferentParameters
                    (ADD_ORDER, GET_ORDERS_LAST_INSERTED_ID, new Order(), signList, sign, sign_standard_size, customer, typeOfWork, info);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {

                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

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
     * @throws DAOValidationException if wrong sign ist, wong sign, wrong standard size, wrong customer, wrong type of work, wrong transaction, string is null
     * @throws DAOException           when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws DAOException {
        try {

            return (Order) RequestExecutor.createFieldUseDifferentParameters
                    (ADD_ORDER_WITH_TRANSACTION, GET_ORDERS_LAST_INSERTED_ID, new Order(),
                            signList, sign, sign_standard_size, customer, typeOfWork, info, transaction);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {

                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

    /**
     * set transaction id to order
     *
     * @param orderID       order id
     * @param transactionID transaction id
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */
    @Override
    public boolean setTransaction(int orderID, int transactionID) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_TRANSACTION, orderID, transactionID);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * set workers crew id
     *
     * @param orderID       order id
     * @param workersCrewID id
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, int)}
     */

    @Override
    public boolean setWorkersCrew(int orderID, int workersCrewID) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_WORKERS_CREW, orderID, workersCrewID);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * set date of execution
     *
     * @param orderID         order id
     * @param dateOfExecution date of execution to add
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setDateOfExecution(int orderID, String dateOfExecution) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_DATE_OF_EXECUTION, orderID, dateOfExecution);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * remove order from table
     *
     * @param orderID to remove
     * @return true if sucesss
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public boolean removeOrder(int orderID) throws DAOException {
        try {

            return  RequestExecutor.createField
                    (REMOVE_ORDER, GET_ORDERS_BY_ID + orderID, new Order(), orderID) == null;

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {

                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

    /**
     * get order
     *
     * @param orderID to return order
     * @return {@link Order}
     * @throws DAOException when catch  exception from {@link RequestExecutor#getOneSignsStaff(String, FactoryType, String...)}
     * @throws DAOException when {@link IConnectionPool} throw exception
     */
    @Override
    public Order getOrder(int orderID) throws DAOException {
        try {

            return (Order) RequestExecutor.getOneSignsStaff(GET_ORDERS_BY_ID + orderID, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * @param orderID sign id where set
     * @param info    info to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setInfo(int orderID, String info) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_INFO, orderID, info);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * get all orders
     *
     * @return {@link Order} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public Order[] getOrders() throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_ORDERS, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get orders with organisation performer id
     *
     * @param organisationPerformerID orginisation to find order
     * @return {@link Order} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public Order[] getOrders(int organisationPerformerID) throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_ORDERS_BY_PERFORMER, new Order(), organisationPerformerID);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get  executed orders where date of execution is null
     *
     * @return {@link Order} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public Order[] getExecutedOrders() throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_EXECUTED_ORDERS, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get info
     *
     * @param id order to get
     * @return info order info
     * @throws DAOException when catch exception from {@link RequestExecutor#getString(String, int)}
     */
    @Override
    public String getInfo(int id) throws DAOException {
        try {

            return RequestExecutor.getString(GET_INFO, id);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get  orders with date of execution is null
     *
     * @return {@link Order} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public Order[] getUnExecutedOrders() throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_UNEXECUTED_ORDERS, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get all  orders combine with map point
     *
     * @return {@link MapPoint$Orders} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public MapPoint$Orders[] getOrdersMapPoint() throws DAOException {
        try {

            return (MapPoint$Orders[]) RequestExecutor.getSignsStaff(GET_ORDERS_MAP_POINT, new MapPoint$Orders());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get  orders with date of execution is null with coordinates
     *
     * @param coordinates map point coordinates
     * @return {@link Order} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public Order[] getUnExecutedOrders(String coordinates) throws DAOException {

        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_UNEXECUTED_ORDERS_BY_COORDINATES, new Order(), coordinates);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get  orders with date of execution is not null
     *
     * @return {@link MapPoint$Orders} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public MapPoint$Orders[] getExecutedOrdersMapPoint() throws DAOException {
        try {

            return (MapPoint$Orders[]) RequestExecutor.getSignsStaff(GET_EXECUTED_ORDERS_MAP_POINT, new MapPoint$Orders());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get  orders with date of execution is null
     *
     * @return {@link MapPoint$Orders} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws DAOException {
        try {

            return (MapPoint$Orders[]) RequestExecutor.getSignsStaff(GET_UNEXECUTED_ORDERS_MAP_POINT, new MapPoint$Orders());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }
}
