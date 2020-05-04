package by.epam.signsControl.service;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.service.exceptions.ServiceException;

public interface IStandardSizesControlService {

    StandardSize addStandardSize(int size) throws ServiceException;

    boolean removeStandardSize(int size) throws ServiceException;

    boolean setInfo(int id, String info) throws ServiceException;

    String getInfo(int id) throws ServiceException;

    StandardSize[] getStandardSizes() throws ServiceException;

}
