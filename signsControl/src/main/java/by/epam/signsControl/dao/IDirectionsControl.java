package by.epam.signsControl.dao;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.dao.exceptions.DAOException;

/**
 * Interface for jdbc direction fields
 */
public interface IDirectionsControl {

    /**
     * @return all directions
     * @throws DAOException when get an exception during execution
     */
    Direction[] getDirections() throws DAOException;

    /**
     * @param coordinates point coordinates in jdbc table
     * @return unused directions for coordinates
     * @throws DAOException when get an exception during execution
     */
    Direction[] getUnusedDirections(String coordinates) throws DAOException;

    /**
     * @param coordinates point coordinates in jdbc table
     * @return jdbc sign list like {@link Direction id param} and direction like {@link Direction direction param} with coordinates param
     * @throws DAOException when get an exception during execution
     */
    Direction[] getPointDirectionsSignListID(String coordinates) throws DAOException;
}
