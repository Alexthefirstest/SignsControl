package by.epam.orders.dao.impl;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.dao.IOrdersControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class OrdersControl implements IOrdersControl {

    private static final String ADD_ORDER =
            "INSERT INTO `orders` (`sign_list`, `sign`, `sign_standard_size`, `customer`, `type_of_work`, info) VALUES (?,?,?,?,?,?);";
    private static final String ADD_ORDER_WITH_TRANSACTION =
            "INSERT INTO `orders` (`sign_list`, `sign`, `sign_standard_size`, `customer`, `type_of_work`, info, transaction) VALUES (?,?,?,?,?,?, ?);";

    private static final String SET_TRANSACTION = "UPDATE `orders` SET `transaction` = ? WHERE (`id` = ?)";
    private static final String SET_WORKERS_CREW = "UPDATE `orders` SET `workers_crew` = ? WHERE (`id` = ?)";
    private static final String SET_DATE_OF_EXECUTION = "UPDATE `orders` SET `date_of_execution` = ? WHERE (`id` = ?)";
    private static final String REMOVE_ORDER = "DELETE FROM `orders` WHERE (`id` = ?);";
    private static final String SET_INFO = "UPDATE `orders` SET `info` = ? WHERE (`id` = ?)";
private static final String GET_INFO = "SELECT info FROM orders where id = ?";

    private static final String GET_ORDERS =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "order by ord.id DESC";


    private static final String GET_ORDERS_BY_ID =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.id =";

    private static final String GET_UNEXECUTED_ORDERS_BY_COORDINATES =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "join map_points as mp on mp.signs_list = ord.sign_list " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where  mp.coordinates=st_geomfromtext(?) AND ord.date_of_execution is null " +
                    "order by ord.id DESC";

    private static final String GET_ORDERS_MAP_POINT =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew,  " +
                    " ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description,  " +
                    " o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info,  " +
                    " tr.id, tr.money,  " +
                    "tow.id,  " +
                    "tow.type, " +
                    "ST_AsText(mp.coordinates) as coords, mp.address, directions.direction, mp.annotation " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign  " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id  " +
                    "join map_points as mp on mp.signs_list = ord.sign_list join directions on mp.direction=directions.id  " +
                    " left join transactions as tr on ord.transaction = tr.id  " +
                    "join type_of_work as tow on tow.id = ord.type_of_work  " +
                    "order by mp.coordinates, mp.signs_list, ord.id DESC";

    private static final String GET_EXECUTED_ORDERS =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.date_of_execution is not null " +
                    "order by ord.id DESC";

    private static final String GET_EXECUTED_ORDERS_MAP_POINT =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew,  " +
                    " ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description,  " +
                    " o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info,  " +
                    " tr.id, tr.money,  " +
                    "tow.id,  " +
                    "tow.type, " +
                    "ST_AsText(mp.coordinates) as coords, mp.address, directions.direction, mp.annotation " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign  " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id  " +
                    "join map_points as mp on mp.signs_list = ord.sign_list join directions on mp.direction=directions.id  " +
                    " left join transactions as tr on ord.transaction = tr.id  " +
                    "join type_of_work as tow on tow.id = ord.type_of_work  " +
                    "where ord.date_of_execution is not null " +
                    "order by mp.coordinates, mp.signs_list, ord.id DESC";

    private static final String GET_UNEXECUTED_ORDERS =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.date_of_execution is null " +
                    "order by ord.id DESC";

    private static final String GET_UNEXECUTED_ORDERS_MAP_POINT =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew,  " +
                    " ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description,  " +
                    " o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info,  " +
                    " tr.id, tr.money,  " +
                    "tow.id,  " +
                    "tow.type, " +
                    "ST_AsText(mp.coordinates) as coords, mp.address, directions.direction, mp.annotation " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign  " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id  " +
                    "join map_points as mp on mp.signs_list = ord.sign_list join directions on mp.direction=directions.id  " +
                    " left join transactions as tr on ord.transaction = tr.id  " +
                    "join type_of_work as tow on tow.id = ord.type_of_work  " +
                    "where ord.date_of_execution is null " +
                    "order by mp.coordinates, mp.signs_list, ord.id DESC";

    private static final String GET_ORDERS_LAST_INSERTED_ID =
            "SELECT ord.id, ord.sign_list, ord.date_of_order, ord.date_of_execution, ord.info, ord.sign_standard_size, ord.workers_crew, " +
                    "ps.id, pdd_section, pdd_sign, pdd_kind, picture, ps.name, ps.description, " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info, " +
                    "tr.id, tr.money, " +
                    "tow.id, " +
                    "tow.type " +
                    "FROM orders as ord  " +
                    "join pdd_signs as ps on ps.id = ord.sign " +
                    "join organisations as o1 on o1.id=ord.customer join organisation_roles as orr1 on o1.role=orr1.id " +
                    "left join transactions as tr on ord.transaction = tr.id " +
                    "join type_of_work as tow on tow.id = ord.type_of_work " +
                    "where ord.id=LAST_INSERT_ID()";

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


    @Override
    public Order addOrder(int signList, int sign, int sign_standard_size, int customer, int typeOfWork, String info, int transaction) throws DAOException {
        try {

            return (Order) RequestExecutor.createFieldUseDifferentParameters
                    (ADD_ORDER_WITH_TRANSACTION, GET_ORDERS_LAST_INSERTED_ID, new Order(), signList, sign, sign_standard_size, customer, typeOfWork, info, transaction);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {

                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

    @Override
    public Boolean setTransaction(int orderID, int transactionID) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_TRANSACTION, orderID, transactionID);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public Boolean setWorkersCrew(int orderID, int workersCrewID) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_WORKERS_CREW, orderID, workersCrewID);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public Boolean setDateOfExecution(int orderID, String dateOfExecution) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_DATE_OF_EXECUTION, orderID, dateOfExecution);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public Boolean removeOrder(int orderID) throws DAOException {
        try {

            return (Order) RequestExecutor.createField
                    (REMOVE_ORDER, GET_ORDERS_BY_ID + orderID, new Order(), orderID) == null;

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {

                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

    @Override
    public Boolean setInfo(int orderID, String info) throws DAOException {
        try {
            return by.epam.orders.dao.impl.RequestExecutor.setField(SET_INFO, orderID, info);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public Order[] getOrders() throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_ORDERS, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public Order[] getExecutedOrders() throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_EXECUTED_ORDERS, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public String getInfo(int id) throws DAOException {
        try {

            return RequestExecutor.getString(GET_INFO, id);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public Order[] getUnExecutedOrders() throws DAOException {
        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_UNEXECUTED_ORDERS, new Order());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint$Orders[] getOrdersMapPoint() throws DAOException {
        try {

            return (MapPoint$Orders[]) RequestExecutor.getSignsStaff(GET_ORDERS_MAP_POINT, new MapPoint$Orders());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public Order[] getUnExecutedOrders(String coordinates) throws DAOException {

        try {

            return (Order[]) RequestExecutor.getSignsStaff(GET_UNEXECUTED_ORDERS_BY_COORDINATES, new Order(), coordinates);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint$Orders[] getExecutedOrdersMapPoint() throws DAOException {
        try {

            return (MapPoint$Orders[]) RequestExecutor.getSignsStaff(GET_EXECUTED_ORDERS_MAP_POINT, new MapPoint$Orders());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public MapPoint$Orders[] getUnExecutedOrdersMapPoint() throws DAOException {
        try {

            return (MapPoint$Orders[]) RequestExecutor.getSignsStaff(GET_UNEXECUTED_ORDERS_MAP_POINT, new MapPoint$Orders());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }
}
