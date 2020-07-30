package by.epam.orders.dao.factory;

import by.epam.orders.dao.IOrdersControl;
import by.epam.orders.dao.ITypeOfWorkControl;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.impl.OrdersControl;
import by.epam.orders.dao.impl.TypeOfWorkControl;
import by.epam.orders.dao.impl.WorkersCrewControl;

/**
 * factory for dao layer
 *
 * @author Bulgak Alexander
 */
public class DaoFactory {

    /*
     *empty constructor
     */
    private DaoFactory() {

    }

    /**
     * this class instance
     */
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * {@link IOrdersControl} realisation
     */
    private final IOrdersControl ordersControl = new OrdersControl();

    /**
     * {@link ITypeOfWorkControl} realisation
     */
    private final ITypeOfWorkControl typeOfWorkControl = new TypeOfWorkControl();

    /**
     * {@link IWorkersCrewControl} realisation
     */
    private final IWorkersCrewControl workersCrewControl = new WorkersCrewControl();

    /**
     * @return {@link DaoFactory#INSTANCE}
     */
    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link IOrdersControl} realisation
     */
    public IOrdersControl getOrdersControl() {
        return ordersControl;
    }

    /**
     * @return {@link ITypeOfWorkControl} instance
     */
    public ITypeOfWorkControl getTypeOfWorkControl() {
        return typeOfWorkControl;
    }

    /**
     * @return {@link IWorkersCrewControl} instance
     */
    public IWorkersCrewControl getWorkersCrewControl() {
        return workersCrewControl;
    }
}
