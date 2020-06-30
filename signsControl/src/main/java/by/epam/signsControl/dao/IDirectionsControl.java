package by.epam.signsControl.dao;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.dao.exceptions.DAOException;

public interface IDirectionsControl {

    Direction[] getDirections() throws DAOException;

    Direction[] getUnusedDirections(String coordinates) throws DAOException;

    Direction[] getPointDirections(String coordinates) throws DAOException;
}
