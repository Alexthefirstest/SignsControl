package by.epam.orders.service.factory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceFactoryTest {

    @Test
    public void notNullInstances() {

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();

        Assert.assertNotNull(serviceFactory.getOrdersControlService());
        Assert.assertNotNull(serviceFactory.getTypeOfWorkControlService());
        Assert.assertNotNull(serviceFactory.getWorkersCrewControlService());

    }
}