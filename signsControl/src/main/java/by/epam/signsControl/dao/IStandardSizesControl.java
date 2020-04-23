package by.epam.signsControl.dao;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.exceptions.DAOException;

public interface IStandardSizesControl {

    boolean addStandardSize(int size) throws DAOException;

    boolean removeStandardSize(int size) throws DAOException;

    boolean setInfo(int id, String info) throws DAOException;

    String getInfo(int id) throws DAOException;

    Integer[] getSizesInt() throws DAOException;

    StandardSize[] getStandardSizes() throws DAOException;

}
