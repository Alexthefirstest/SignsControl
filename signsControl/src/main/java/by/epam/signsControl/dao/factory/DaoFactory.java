package by.epam.signsControl.dao.factory;

import by.epam.signsControl.dao.IDirectionsControl;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.impl.DirectionsControl;
import by.epam.signsControl.dao.impl.LocalSingsControl;
import by.epam.signsControl.dao.impl.MapPointsControl;
import by.epam.signsControl.dao.impl.PDDSignsControl;
import by.epam.signsControl.dao.impl.StandardSizesControl;

/**
 * dao layer factory
 */
public class DaoFactory {

    /**
     * this class instance
     */
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * {@link ILocalSignsControl} instance
     */
    private final ILocalSignsControl localSignsControl = new LocalSingsControl();

    /**
     * {@link IMapPointsControl} instance
     */
    private final IMapPointsControl mapPointsControl = new MapPointsControl();

    /**
     * {@link IPDDSignsControl} instance
     */
    private final IPDDSignsControl pddSignsControl = new PDDSignsControl();

    /**
     * {@link IStandardSizesControl} instance
     */
    private final IStandardSizesControl standardSizesControl = new StandardSizesControl();

    /**
     * {@link IDirectionsControl} instance
     */
    private final IDirectionsControl directionsControl = new DirectionsControl();

    /*
     * private constructor
     */
    private DaoFactory() {

    }

    /**
     * @return dao factory instance
     */
    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * @return {@link IDirectionsControl} instance
     */
    public IDirectionsControl getDirectionsControl() {
        return directionsControl;
    }

    /**
     * @return {@link ILocalSignsControl} instance
     */
    public ILocalSignsControl getLocalSignsControl() {
        return localSignsControl;
    }

    /**
     * @return {@link IMapPointsControl} instance
     */
    public IMapPointsControl getMapPointsControl() {
        return mapPointsControl;
    }

    /**
     * @return {@link IPDDSignsControl} instance
     */
    public IPDDSignsControl getPddSignsControl() {
        return pddSignsControl;
    }

    /**
     * @return {@link IStandardSizesControl} instance
     */
    public IStandardSizesControl getStandardSizesControl() {
        return standardSizesControl;
    }
}
