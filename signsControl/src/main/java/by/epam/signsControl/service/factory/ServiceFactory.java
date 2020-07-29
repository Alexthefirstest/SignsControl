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

/**
 * service factory
 */
public class ServiceFactory {

    /**
     * this class instance
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * {@link ILocalSignsControlService} instance
     */
    private final ILocalSignsControlService localSignsControlService = new LocalSignsControlService();

    /**
     * {@link IMapPointsControlService} instance
     */
    private final IMapPointsControlService mapPointsControlService = new MapPointsControlService();

    /**
     * {@link IPDDSignsControlService} instance
     */
    private final IPDDSignsControlService pddSignsControlService = new PDDSignsControlService();

    /**
     * {@link IStandardSizesControlService} instance
     */
    private IStandardSizesControlService standardSizesControlService = new StandardSizesControlService();

    /**
     * {@link IDirectionsControlService} instance
     */
    private IDirectionsControlService directionsControlService = new DirectionsControlService();

    /**
     * private constructor
     */
    private ServiceFactory() {
    }

    /**
     * @return {@link ServiceFactory#directionsControlService}
     */
    public IDirectionsControlService getDirectionsControlService() {
        return directionsControlService;
    }

    /**
     * @return {@link ServiceFactory#INSTANCE}
     */
    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link ServiceFactory#localSignsControlService}
     */
    public ILocalSignsControlService getLocalSignsControlService() {
        return localSignsControlService;
    }

    /**
     * @return {@link ServiceFactory#mapPointsControlService}
     */
    public IMapPointsControlService getMapPointsControlService() {
        return mapPointsControlService;
    }

    /**
     * @return {@link ServiceFactory#pddSignsControlService}
     */
    public IPDDSignsControlService getPddSignsControlService() {
        return pddSignsControlService;
    }

    /**
     * @return {@link ServiceFactory#standardSizesControlService}
     */
    public IStandardSizesControlService getStandardSizesControlService() {
        return standardSizesControlService;
    }
}
