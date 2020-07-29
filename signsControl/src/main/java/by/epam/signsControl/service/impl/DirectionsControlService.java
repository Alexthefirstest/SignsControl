package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.dao.IDirectionsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.IDirectionsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * service for working with {@link Direction}, get, add, set parameters of directions
 */
public class DirectionsControlService implements IDirectionsControlService {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(DirectionsControlService.class);

    /**
     * {@link IDirectionsControl} instance
     */
    private final IDirectionsControl directionsControl = DaoFactory.getINSTANCE().getDirectionsControl();

    /**
     * get directions
     *
     * @return all directions
     * @throws ServiceValidationException when {@link IDirectionsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IDirectionsControl} throw {@link DAOException}
     */
    @Override
    public Direction[] getDirections() throws ServiceException {
        try {

            return directionsControl.getDirections();

        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return unused direction for point with coordinates param
     * @throws ServiceValidationException when {@link IDirectionsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IDirectionsControl} throw {@link DAOException}
     */
    @Override
    public Direction[] getUnusedDirections(String coordinates) throws ServiceException {

        String point = StringTransformer.coordinatesOrPointToPointWithCheck(coordinates);
        logger.info(point);

        try {

            return directionsControl.getUnusedDirections(point);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return jdbc sign list like {@link Direction id param} and direction like {@link Direction direction param}
     * @throws ServiceValidationException when {@link IDirectionsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IDirectionsControl} throw {@link DAOException}
     */
    @Override
    public Direction[] getPointDirectionsSignListID(String coordinates) throws ServiceException {

        InputValidation.pointCheck(coordinates);

        try {

            return directionsControl.getPointDirectionsSignListID(coordinates);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
