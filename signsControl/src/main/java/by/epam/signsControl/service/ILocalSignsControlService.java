package by.epam.signsControl.service;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.service.exceptions.ServiceException;

import java.util.Date;

public interface ILocalSignsControlService {

    LocalSign addSign(int signListId, int pddSignId, int standardSize) throws ServiceException;

    LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws ServiceException;

    boolean deleteSign(int signId) throws ServiceException;


    LocalSign[] getActualSigns(int signsListID) throws ServiceException;

    LocalSign[] getActualSigns(int signsListID, String date) throws ServiceException;

    LocalSign[] getSignsLists() throws ServiceException;


    boolean setDateOfAdd(int localSignID, Date date) throws ServiceException;

    boolean setDateOfRemove(int localSignID, Date date) throws ServiceException;

    boolean setAnnotation(int localSignID, String annotation) throws ServiceException;

}