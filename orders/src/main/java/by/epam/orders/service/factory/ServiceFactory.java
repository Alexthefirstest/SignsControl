package by.epam.orders.service.factory;

import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.ITypeOfWorkControlService;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.impl.OrdersControlService;
import by.epam.orders.service.impl.TypeOfWorkControlService;
import by.epam.orders.service.impl.WorkersCrewControlService;

/**
 * Service layer factory
 */
public class ServiceFactory {

    /**
     * this class instance
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * private constructor
     */
    private ServiceFactory() {

    }

    /**
     * {@link IOrdersControlService} instance
     */
    private IOrdersControlService ordersControlService = new OrdersControlService();

    /**
     * {@link ITypeOfWorkControlService} instance
     */
    private ITypeOfWorkControlService typeOfWorkControlService = new TypeOfWorkControlService();

    /**
     * {@link IWorkersCrewControlService} instance
     */
    private IWorkersCrewControlService workersCrewControlService = new WorkersCrewControlService();


    /**
     * @return {@link ServiceFactory#INSTANCE
     */
    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }


    /**
     * @return {@link ServiceFactory#ordersControlService
     */
    public IOrdersControlService getOrdersControlService() {
        return ordersControlService;
    }


    /**
     * @return {@link ServiceFactory#typeOfWorkControlService
     */
    public ITypeOfWorkControlService getTypeOfWorkControlService() {
        return typeOfWorkControlService;
    }


    /**
     * @return {@link ServiceFactory#workersCrewControlService
     */
    public IWorkersCrewControlService getWorkersCrewControlService() {
        return workersCrewControlService;
    }

}
