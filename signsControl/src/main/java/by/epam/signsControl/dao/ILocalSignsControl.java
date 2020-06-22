package by.epam.signsControl.dao;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.dao.exceptions.DAOException;

import java.util.Date;

public interface ILocalSignsControl {

    LocalSign addSign(int signListId, int pddSignId, int standardSize) throws DAOException;

    LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws DAOException;

    boolean deleteSign(int signId) throws DAOException;

    MapPoint$LocalSign[] getActualMapPoints$LocalSigns() throws DAOException;
    MapPoint$LocalSign[] getAllMapPoints$LocalSigns() throws DAOException;

    LocalSign[] getActualSigns() throws DAOException;

    LocalSign[] getActualSigns(int signsListID, String date) throws DAOException;

    LocalSign[] getSignsLists() throws DAOException;


    boolean setDateOfAdd(int localSignID, Date date) throws DAOException;
    boolean setDateOfRemove(int localSignID, Date date) throws DAOException;

    boolean setAnnotation(int localSignID, String annotation) throws DAOException;


}
