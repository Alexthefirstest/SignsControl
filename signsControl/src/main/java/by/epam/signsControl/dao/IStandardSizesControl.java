package by.epam.signsControl.dao;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.exceptions.DAOException;

public interface IStandardSizesControl {

    StandardSize addStandardSize(int size) throws DAOException;

    StandardSize addStandardSize(int size, String info) throws DAOException;

    boolean removeStandardSize(int size) throws DAOException;

    boolean setInfo(int id, String info) throws DAOException;
    boolean setSize(int oldSize, int newSize) throws DAOException;

    String getInfo(int id) throws DAOException;

    StandardSize[] getStandardSizes() throws DAOException;

}
