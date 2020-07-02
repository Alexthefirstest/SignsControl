package by.epam.signsControl.service;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.service.exceptions.ServiceException;

public interface IDirectionsControlService {

    Direction[] getDirections() throws ServiceException;

    Direction[] getUnusedDirections(String coordinates) throws ServiceException;

    Direction[] getPointDirectionsSignListID(String coordinates) throws ServiceException;
}
