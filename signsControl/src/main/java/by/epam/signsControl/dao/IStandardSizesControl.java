package by.epam.signsControl.dao;

import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.dao.exceptions.DAOException;

public interface IStandardSizesControl {

    StandardSize addStandardSize(int size) throws DAOException;

    StandardSize removeStandardSize(int size) throws DAOException;

    boolean setInfo(int id, String info) throws DAOException;

    String getInfo(int id) throws DAOException;

    StandardSize[] getStandardSizes() throws DAOException;

}
