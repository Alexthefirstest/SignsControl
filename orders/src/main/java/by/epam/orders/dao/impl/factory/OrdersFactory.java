package by.epam.orders.dao.impl.factory;


import by.epam.orders.bean.FactoryType;
import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.bean.Sign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * factory to produce {@link FactoryType} objects
 */
public class OrdersFactory {

    /**
     * generated by IDEA
     */
    private static Logger logger = LogManager.getLogger(OrdersFactory.class);

    /**
     * this class instance
     */
    private static final OrdersFactory INSTANCE = new OrdersFactory();

    /**
     * private constructor
     */
    private OrdersFactory() {
    }

    /**
     * create an {@link FactoryType} object from result statement
     *
     * @param rs         result statement
     * @param signsStaff object to set it with parameters from rs
     * @return {@link FactoryType} object or null if can't find object constructor
     * @throws SQLException           when {@link ResultSet} throw it
     * @throws DAOValidationException if rs.next=false
     */

    public FactoryType createSignStaff(ResultSet rs, FactoryType signsStaff) throws SQLException, DAOValidationException {


        if (!rs.next()) {
            logger.info("rs.next = false");
            throw new DAOValidationException("can't find new object or it's wasn't created");
        }

        if (signsStaff instanceof WorkersCrew) {

            WorkersCrew workersCrew = (WorkersCrew) signsStaff;

            workersCrew.setId(rs.getInt(15));
            workersCrew.setCreationDate(rs.getDate(16));
            workersCrew.setRemoveDate(rs.getDate(17));
            workersCrew.setInfo(rs.getString(18));
            workersCrew.setOrganisation(new Organisation(rs.getInt(19), rs.getString(20),
                    new Role(rs.getInt(21), rs.getString(22)), rs.getBoolean(23),
                    rs.getString(24)));

            if (rs.getInt(1) > 0) {

                do {
                    workersCrew.addWorker(new User(rs.getInt(1), rs.getString(2), new Role(rs.getInt(3), rs.getString(4)),
                            new Organisation(rs.getInt(5), rs.getString(6),
                                    new Role(rs.getInt(7), rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                            rs.getBoolean(11), rs.getString(12), rs.getString(13), rs.getString(14)));
                } while (rs.next());
            }
            return workersCrew;

        }

        if (signsStaff instanceof TypeOfWork) {

            TypeOfWork typeOfWork = (TypeOfWork) signsStaff;

            typeOfWork.setId(rs.getInt(1));
            typeOfWork.setTypeOfWork(rs.getString(2));
            typeOfWork.setPrice(rs.getDouble(3));
            typeOfWork.setBlock(rs.getBoolean(4));

            return typeOfWork;

        }

        if (signsStaff instanceof Order) {

            Order order = (Order) signsStaff;

            order.setId(rs.getInt(1));
            order.setSignList(rs.getInt(2));
            order.setDateOfOrder(rs.getTimestamp(3));
            order.setDateOfExecution(rs.getTimestamp(4));
            order.setInfo(rs.getString(5));
            order.setStandardSize(rs.getInt(6));
            order.setWorkersCrew(rs.getInt(7));
            order.setSign(new Sign(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
                    rs.getBytes(12), rs.getString(13), rs.getString(14)));
            order.setCustomerOrganisation(new Organisation(rs.getInt(15), rs.getString(16),
                    new Role(rs.getInt(17), rs.getString(18)), rs.getBoolean(19),
                    rs.getString(20)));
            order.setTransactionID(rs.getInt(21));
            order.setTransactionMoney(rs.getDouble(22));
            order.setTypeOfWork(new TypeOfWork(rs.getInt(23), rs.getString(24),
                    rs.getDouble(25), rs.getBoolean(26)));

            return order;

        }


        logger.warn("did't find if: createSignsStaff method, signStaff class: " + signsStaff.getClass());
        return null;

    }


    /**
     * create an array of {@link FactoryType} objects from result statement
     *
     * @param rs         result statement
     * @param signsStaff object to know concrete type
     * @return an array of {@link FactoryType} objects or null if can't find object constructor
     * @throws SQLException when {@link ResultSet} throw it
     */
    public FactoryType[] createSignStaffArr(ResultSet rs, FactoryType signsStaff) throws SQLException {


        if (signsStaff instanceof WorkersCrew) {

            if (!rs.next()) {
                return null;
            }

            ArrayList<FactoryType> signsStaffArr = new ArrayList<>();
            WorkersCrew workersCrew;
            boolean next;

            do {


                workersCrew = new WorkersCrew();

                workersCrew.setId(rs.getInt(15));
                workersCrew.setCreationDate(rs.getDate(16));
                workersCrew.setRemoveDate(rs.getDate(17));
                workersCrew.setInfo(rs.getString(18));
                workersCrew.setOrganisation(new Organisation(rs.getInt(19), rs.getString(20),
                        new Role(rs.getInt(21), rs.getString(22)), rs.getBoolean(23),
                        rs.getString(24)));

                workersCrew.setNewWorkersArr();

                if (rs.getInt(1) > 0) {

                    do {
                        workersCrew.addWorker(new User(rs.getInt(1), rs.getString(2), new Role(rs.getInt(3), rs.getString(4)),
                                new Organisation(rs.getInt(5), rs.getString(6),
                                        new Role(rs.getInt(7), rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                                rs.getBoolean(11), rs.getString(12), rs.getString(13), rs.getString(14)));
                    } while ((next = rs.next()) && workersCrew.getId() == rs.getInt(15));

                } else {
                    next = rs.next();
                }

                signsStaffArr.add(workersCrew);

            } while (next);

            return signsStaffArr.toArray(new WorkersCrew[0]);
        }


        if (signsStaff instanceof MapPoint$Orders) {

            ArrayList<FactoryType> mapPoint$OrdersArr = new ArrayList<>();

            MapPoint mapPoint;

            ArrayList<Order> orders;


            boolean stillNext = rs.next();

            while (stillNext) {

                orders = new ArrayList<>();
                mapPoint = new MapPoint();

                mapPoint.setCoordinates(rs.getString(25));

                do {


                    mapPoint.addAddress(rs.getString(26));
                    mapPoint.addAngle(rs.getString(27).charAt(0));
                    mapPoint.addAnnotation(rs.getString(28));
                    mapPoint.addSignsList(rs.getInt(2));


                    do {
                        orders.add((new Order(rs.getInt(1), rs.getInt(2),
                                new Sign(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
                                        rs.getBytes(12), rs.getString(13), rs.getString(14)), rs.getInt(6),
                                new Organisation(rs.getInt(15), rs.getString(16),
                                        new Role(rs.getInt(17), rs.getString(18)), rs.getBoolean(19),
                                        rs.getString(20)),
                                rs.getInt(21), rs.getDouble(22),
                                new TypeOfWork(rs.getInt(23), rs.getString(24), rs.getDouble(29), rs.getBoolean(30)),
                                rs.getInt(7), rs.getTimestamp(3),
                                rs.getTimestamp(4), rs.getString(5))));

                    } while ((stillNext = rs.next()) && rs.getInt(2) == orders.get(0).getSignList());


                } while (stillNext && rs.getString(25).equals(mapPoint.getCoordinates()));

                mapPoint$OrdersArr.add(new MapPoint$Orders(mapPoint, orders));
            }

            return mapPoint$OrdersArr.toArray(new MapPoint$Orders[0]);


        }

        if (signsStaff instanceof TypeOfWork) {

            ArrayList<TypeOfWork> tow = new ArrayList<>();

            while (rs.next()) {
                tow.add(new TypeOfWork(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4)));
            }

            return tow.toArray(new TypeOfWork[0]);

        }

        if (signsStaff instanceof Order) {

            ArrayList<Order> orders = new ArrayList<>();

            while (rs.next()) {

                orders.add
                        (new Order(rs.getInt(1), rs.getInt(2),
                                new Sign(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11),
                                        rs.getBytes(12), rs.getString(13), rs.getString(14)), rs.getInt(6),
                                new Organisation(rs.getInt(15), rs.getString(16),
                                        new Role(rs.getInt(17), rs.getString(18)), rs.getBoolean(19),
                                        rs.getString(20)),
                                rs.getInt(21), rs.getDouble(22),
                                new TypeOfWork(rs.getInt(23), rs.getString(24), rs.getDouble(25), rs.getBoolean(26)),
                                rs.getInt(7), rs.getTimestamp(3),
                                rs.getTimestamp(4), rs.getString(5)));
            }


            return orders.toArray(new Order[0]);

        }


        logger.warn("did't find if: createSignsStaff[] method, signStaff class: " + signsStaff.getClass());
        return null;

    }

    /**
     * @return {@link OrdersFactory#INSTANCE}
     */
    public static OrdersFactory getINSTANCE() {
        return INSTANCE;
    }
}
