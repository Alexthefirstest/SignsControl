package by.epam.signsControl.service;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.service.exceptions.ServiceException;

import java.util.Date;

public interface ILocalSignsControlService {

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws ServiceException when get an exception during execution
     */
    LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws ServiceException;

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param dateOfAdd    date of add for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws ServiceException when get an exception during execution
     */
    LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String annotation) throws ServiceException;

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param dateOfAdd    date of add for local sign
     * @param dateOFRemove date of remove for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws ServiceException when get an exception during execution
     */
    LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String dateOFRemove, String annotation) throws ServiceException;

    /**
     * @param signId sign id
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean deleteSign(int signId) throws ServiceException;

    /**
     * @return {@link MapPoint$LocalSign} where date of remove is null
     * @throws ServiceException when get an exception during execution
     */
    MapPoint$LocalSign[] getActualMapPoints$LocalSigns() throws ServiceException;

    /**
     * @return all {@link MapPoint$LocalSign}
     * @throws ServiceException when get an exception during execution
     */
    MapPoint$LocalSign[] getAllMapPoints$LocalSigns() throws ServiceException;

    /**
     * @param date required date
     * @return {@link MapPoint$LocalSign} where date besides date of add and date of remove
     * @throws ServiceException when get an exception during execution
     */
    MapPoint$LocalSign[] getMapPoints$LocalSignsByDate(String date) throws ServiceException;

    /**
     * @param coordinates point coordinates
     * @return {@link LocalSign} with coordinates param
     * @throws ServiceException when get an exception during execution
     */
    LocalSign[] getSigns(String coordinates) throws ServiceException;

    /**
     * @return {@link LocalSign}
     * @throws ServiceException when get an exception during execution
     */
    LocalSign[] getActualSigns() throws ServiceException;

    /**
     * @param signsListID sign lists to find
     * @param date        required date
     * @return {@link LocalSign} where date param besides date of add and date of remove
     * @throws ServiceException when get an exception during execution
     */
    LocalSign[] getActualSigns(int signsListID, String date) throws ServiceException;

    /**
     * @return all signs list
     * @throws ServiceException when get an exception during execution
     */
    LocalSign[] getSignsLists() throws ServiceException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignId  local sign id in jdbc
     * @param dateOfAdd    date of add to set
     * @param dateOfRemove date of remove to set
     * @param annotation   annotation to set
     * @return true if at least one was set
     * @throws ServiceException when get an exception during execution
     */
    boolean setParameters(int localSignId, String dateOfAdd, String dateOfRemove, String annotation) throws ServiceException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of add to set
     * @return true if at least one was set
     * @throws ServiceException when get an exception during execution
     */
    boolean setDateOfAdd(int localSignID, String date) throws ServiceException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of add to set
     * @return true if at least one was set
     * @throws ServiceException when get an exception during execution
     */
    boolean setDateOfRemove(int localSignID, String date) throws ServiceException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of remove to set
     * @return true if at least one was set
     * @throws ServiceException when get an exception during execution
     */
    boolean setDateOfAdd(int localSignID, Date date) throws ServiceException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of remove to set
     * @return true if at least one was set
     * @throws ServiceException when get an exception during execution
     */
    boolean setDateOfRemove(int localSignID, Date date) throws ServiceException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param annotation  annotation to set
     * @return true if at least one was set
     * @throws ServiceException when get an exception during execution
     */
    boolean setAnnotation(int localSignID, String annotation) throws ServiceException;

}
