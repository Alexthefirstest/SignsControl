package by.epam.signsControl.dao;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;

import java.util.Date;

/**
 * interface for jdbc Local sign fields
 */
public interface ILocalSignsControl {

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws DAOException when get an exception during execution
     */
    LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws DAOException;

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param dateOfAdd    date of add for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws DAOValidationException if wrong sign ist, wong sign, wrong standard size, String is null
     * @throws DAOException           when get an exception during execution
     */
    LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String annotation) throws DAOException;

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param dateOfAdd    date of add for local sign
     * @param dateOfRemove date of remove for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws DAOValidationException if wrong sign ist, wong sign, wrong standard size, String is null
     * @throws DAOException           when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String dateOfRemove, String annotation) throws DAOException;

    /**
     * @param signId sign id
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean deleteSign(int signId) throws DAOException;

    /**
     * @return {@link MapPoint$LocalSign} where date of remove is null
     * @throws DAOException when get an exception during execution
     */
    MapPoint$LocalSign[] getActualMapPoints$LocalSigns() throws DAOException;

    /**
     * @return all {@link MapPoint$LocalSign}
     * @throws DAOException when get an exception during execution
     */
    MapPoint$LocalSign[] getAllMapPoints$LocalSigns() throws DAOException;

    /**
     * @param date required date
     * @return {@link MapPoint$LocalSign} where date besides date of add and date of remove
     * @throws DAOException when get an exception during execution
     */
    MapPoint$LocalSign[] getMapPoints$LocalSignsByDate(String date) throws DAOException;

    /**
     * @param coordinates point coordinates
     * @return {@link LocalSign} with coordinates param
     * @throws DAOException when get an exception during execution
     */
    LocalSign[] getSigns(String coordinates) throws DAOException;

    /**
     * @return {@link LocalSign}
     * @throws DAOException when get an exception during execution
     */
    LocalSign[] getActualSigns() throws DAOException;

    /**
     * @param signsListID sign lists to find
     * @param date        required date
     * @return {@link LocalSign} where date param besides date of add and date of remove
     * @throws DAOException when get an exception during execution
     */
    LocalSign[] getActualSigns(int signsListID, String date) throws DAOException;

    /**
     * @return all signs list
     * @throws DAOException when get an exception during execution
     */
    LocalSign[] getSignsLists() throws DAOException;


    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignId  local sign id in jdbc
     * @param dateOfAdd    date of add to set
     * @param dateOfRemove date of remove to set
     * @param annotation   annotation to set
     * @return true if at least one was set
     * @throws DAOException when get an exception during execution
     */
    boolean setParameters(int localSignId, String dateOfAdd, String dateOfRemove, String annotation) throws DAOException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of add to set
     * @return true if at least one was set
     * @throws DAOException when get an exception during execution
     */
    boolean setDateOfAdd(int localSignID, String date) throws DAOException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of add to set
     * @return true if at least one was set
     * @throws DAOException when get an exception during execution
     */
    boolean setDateOfRemove(int localSignID, String date) throws DAOException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of remove to set
     * @return true if at least one was set
     * @throws DAOException when get an exception during execution
     */
    boolean setDateOfAdd(int localSignID, Date date) throws DAOException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of remove to set
     * @return true if at least one was set
     * @throws DAOException when get an exception during execution
     */
    boolean setDateOfRemove(int localSignID, Date date) throws DAOException;

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param annotation  annotation to set
     * @return true if at least one was set
     * @throws DAOException when get an exception during execution
     */
    boolean setAnnotation(int localSignID, String annotation) throws DAOException;


}
