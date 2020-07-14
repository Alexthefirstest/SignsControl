package by.epam.orders.dao.impl.factory;


import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.bean.Order;
import by.epam.orders.bean.TypeOfWork;
import by.epam.orders.bean.WorkersCrew;
import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.orders.bean.FactoryType;

import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.bean.Sign;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersFactory {

    private static Logger logger = LogManager.getLogger(OrdersFactory.class);

    private OrdersFactory() {
    }

    private static final OrdersFactory INSTANCE = new OrdersFactory();

    public FactoryType createSignStaff(ResultSet rs, FactoryType signsStaff) throws SQLException, DAOValidationException {


        if (!rs.next()) {
            logger.info("rs.next = false");
            throw new DAOValidationException("cant find new object or it's wasn't created");
        }

        if (signsStaff instanceof WorkersCrew) {

            WorkersCrew workersCrew = (WorkersCrew) signsStaff;

            workersCrew.setId(rs.getInt(15));
            workersCrew.setCreationDate(rs.getDate(16));
            workersCrew.setRemoveDate(rs.getDate(17));
            workersCrew.setInfo(rs.getString(18));

            workersCrew.setNewWorkersArr();

            do {
                workersCrew.addWorker(new User(rs.getInt(1), rs.getString(2), new Role(rs.getInt(3), rs.getString(4)),
                        new Organisation(rs.getInt(5), rs.getString(6),
                                new Role(rs.getInt(7), rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                        rs.getBoolean(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            } while (rs.next());

            return workersCrew;

        }

        if (signsStaff instanceof TypeOfWork) {

            TypeOfWork typeOfWork = (TypeOfWork) signsStaff;

            typeOfWork.setId(rs.getInt(1));
            typeOfWork.setTypeOfWork(rs.getString(2));

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
            order.setTypeOFWork(new TypeOfWork(rs.getInt(23), rs.getString(24)));

            return order;

        }


        logger.warn("did't find if: createSignsStaff method, signStaff class: " + signsStaff.getClass());
        return null;

    }

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

                workersCrew.setNewWorkersArr();

                do {
                    workersCrew.addWorker(new User(rs.getInt(1), rs.getString(2), new Role(rs.getInt(3), rs.getString(4)),
                            new Organisation(rs.getInt(5), rs.getString(6),
                                    new Role(rs.getInt(7), rs.getString(8)), rs.getBoolean(9), rs.getString(10)),
                            rs.getBoolean(11), rs.getString(12), rs.getString(13), rs.getString(14)));
                } while ((next = rs.next()) && workersCrew.getId() == rs.getInt(15));

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
                                new TypeOfWork(rs.getInt(23), rs.getString(24)),
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
                tow.add(new TypeOfWork(rs.getInt(1), rs.getString(2)));
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
                                new TypeOfWork(rs.getInt(23), rs.getString(24)),
                                rs.getInt(7), rs.getTimestamp(3),
                                rs.getTimestamp(4), rs.getString(5)));
            }


            return orders.toArray(new Order[0]);

        }


        logger.warn("did't find if: createSignsStaff[] method, signStaff class: " + signsStaff.getClass());
        return null;

    }


    public static OrdersFactory getINSTANCE() {
        return INSTANCE;
    }
}