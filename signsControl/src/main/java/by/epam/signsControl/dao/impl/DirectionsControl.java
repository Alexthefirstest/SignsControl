package by.epam.signsControl.dao.impl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.FactoryType;
import by.epam.signsControl.dao.IDirectionsControl;
import by.epam.signsControl.dao.exceptions.DAOException;

import java.sql.SQLException;

/**
 * class for jdbc communication
 */
public class DirectionsControl implements IDirectionsControl {

    /*
     *select all request to sql table
     */
    private static final String SQL_SELECT_ALL = "SELECT id, direction FROM road_signs_control.directions order by id";

    /*
     *select signs_lists and directions for point with coorditantes
     */
    private static final String SQL_SELECT_POINT_DIRECTIONS_SIGN_LIST_ID = "SELECT mp.signs_list, dir.direction FROM directions as dir join map_points as mp on dir.id=mp.direction where mp.coordinates=st_geomfromtext(?)";

    /*
     * sql select unused directions for coordinates
     */
    private static final String SQL_GET_UNUSED_DIRECTION = "SELECT dir.id, dir.direction from (SELECT direction FROM map_points where map_points.coordinates=st_geomfromtext(?) ) as mp " +
            "right join directions as dir on mp.direction=dir.id " +
            "where mp.direction is null " +
            "order by id";

    /**
     * @return all directions
     * @throws DAOException when {@link RequestExecutor#getSignsStaff(String, FactoryType, int...) throw exception}
     */
    @Override
    public Direction[] getDirections() throws DAOException {

        try {

            return (Direction[]) RequestExecutor.getSignsStaff(SQL_SELECT_ALL, new Direction());

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }

    }

    /**
     * @return unused direction for point with coordinates param
     * @throws DAOException when {@link RequestExecutor#getSignsStaff(String, FactoryType, int...) throw exception}
     */
    @Override
    public Direction[] getUnusedDirections(String coordinates) throws DAOException {

        try {

            return (Direction[]) RequestExecutor.getSignsStaffWithDifferentParameters(SQL_GET_UNUSED_DIRECTION, new Direction(), coordinates);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }

    }

    /**
     * @return jdbc sign list like {@link Direction id param} and direction like {@link Direction direction param}
     * @throws DAOException when {@link RequestExecutor#getSignsStaff(String, FactoryType, int...) throw exception} with coordinates param
     */
    @Override
    public Direction[] getPointDirectionsSignListID(String coordinates) throws DAOException {

        try {

            return (Direction[]) RequestExecutor.getSignsStaffWithDifferentParameters(SQL_SELECT_POINT_DIRECTIONS_SIGN_LIST_ID, new Direction(), coordinates);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }

    }
}
