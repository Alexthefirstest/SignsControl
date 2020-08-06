package by.epam.signsControl.service.factory;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceFactoryTest {

    @Test
    public void getINSTANCE() {

        ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();

        Assert.assertNotNull(serviceFactory.getDirectionsControlService());
        Assert.assertNotNull(serviceFactory.getLocalSignsControlService());
        Assert.assertNotNull(serviceFactory.getMapPointsControlService());
        Assert.assertNotNull(serviceFactory.getPddSignsControlService());
        Assert.assertNotNull(serviceFactory.getStandardSizesControlService());

    }
}