package by.epam.signsControl.service;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.service.exceptions.ServiceException;

/**
 * service for working with {@link Direction}, get, add, set parameters of directions
 */
public interface IDirectionsControlService {

    /**
     * get directions
     *
     * @return all directions
     * @throws ServiceException when get an exception during execution
     */
    Direction[] getDirections() throws ServiceException;

    /**
     * @param coordinates coordinates to search
     * @return unused direction for point with coordinates param
     * @throws ServiceException when get an exception during execution
     */
    Direction[] getUnusedDirections(String coordinates) throws ServiceException;

    /**
     * @param coordinates coordinates to search
     * @return jdbc sign list like {@link Direction id param} and direction like {@link Direction direction param}
     * @throws ServiceException when get an exception during execution
     */
    Direction[] getPointDirectionsSignListID(String coordinates) throws ServiceException;
}
