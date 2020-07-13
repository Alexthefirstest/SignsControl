package by.epam.orders.service.factory;

import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.ITypeOfWorkControlService;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.impl.OrdersControlService;
import by.epam.orders.service.impl.TypeOfWorkControlService;
import by.epam.orders.service.impl.WorkersCrewControlService;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private ServiceFactory() {

    }

    private IOrdersControlService ordersControlService = new OrdersControlService();
    private ITypeOfWorkControlService typeOfWorkControlService = new TypeOfWorkControlService();
    private WorkersCrewControlService workersCrewControlService = new WorkersCrewControlService();

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public IOrdersControlService getOrdersControlService() {
        return ordersControlService;
    }

    public ITypeOfWorkControlService getTypeOfWorkControlService() {
        return typeOfWorkControlService;
    }

    public IWorkersCrewControlService getWorkersCrewControlService() {
        return workersCrewControlService;
    }
}
