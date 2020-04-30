package by.epam.signsControl.dao.factory;

import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.IMapPointsControl;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.IStandardSizesControl;
import by.epam.signsControl.dao.impl.LocalSingsControl;
import by.epam.signsControl.dao.impl.MapPointsControl;
import by.epam.signsControl.dao.impl.PDDSignsControl;
import by.epam.signsControl.dao.impl.StandardSizesControl;

public class DaoFactory {

    private DaoFactory() {

    }

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final ILocalSignsControl localSignsControl = new LocalSingsControl();
    private final IMapPointsControl mapPointsControl = new MapPointsControl();
    private final IPDDSignsControl pddSignsControl = new PDDSignsControl();
    private final IStandardSizesControl standardSizesControl = new StandardSizesControl();

    public static DaoFactory getINSTANCE() {
        return INSTANCE;
    }

    public ILocalSignsControl getLocalSignsControl() {
        return localSignsControl;
    }

    public IMapPointsControl getMapPointsControl() {
        return mapPointsControl;
    }

    public IPDDSignsControl getPddSignsControl() {
        return pddSignsControl;
    }

    public IStandardSizesControl getStandardSizesControl() {
        return standardSizesControl;
    }
}
