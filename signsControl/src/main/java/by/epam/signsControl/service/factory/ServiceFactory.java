package by.epam.signsControl.service.factory;

import by.epam.signsControl.service.IDirectionsControlService;
import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.IMapPointsControlService;
import by.epam.signsControl.service.IPDDSignsControlService;
import by.epam.signsControl.service.IStandardSizesControlService;
import by.epam.signsControl.service.impl.DirectionsControlService;
import by.epam.signsControl.service.impl.LocalSignsControlService;
import by.epam.signsControl.service.impl.MapPointsControlService;
import by.epam.signsControl.service.impl.PDDSignsControlService;
import by.epam.signsControl.service.impl.StandardSizesControlService;

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private final ILocalSignsControlService localSignsControlService = new LocalSignsControlService();
    private final IMapPointsControlService mapPointsControlService = new MapPointsControlService();
    private final IPDDSignsControlService pddSignsControlService = new PDDSignsControlService();
    private IStandardSizesControlService standardSizesControlService = new StandardSizesControlService();
    private IDirectionsControlService directionsControlService = new DirectionsControlService();

    public IDirectionsControlService getDirectionsControlService() {
        return directionsControlService;
    }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public ILocalSignsControlService getLocalSignsControlService() {
        return localSignsControlService;
    }

    public IMapPointsControlService getMapPointsControlService() {
        return mapPointsControlService;
    }

    public IPDDSignsControlService getPddSignsControlService() {
        return pddSignsControlService;
    }

    public IStandardSizesControlService getStandardSizesControlService() {
        return standardSizesControlService;
    }
}
