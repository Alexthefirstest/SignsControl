package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.LocalSign;
import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.dao.ILocalSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

import java.util.Date;

/**
 * class to connect with {@link ILocalSignsControl}
 */
public class LocalSignsControlService implements ILocalSignsControlService {

    /**
     * {@link ILocalSignsControl} instance
     */
    private final ILocalSignsControl localSignsControl = DaoFactory.getINSTANCE().getLocalSignsControl();

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String annotation) throws ServiceException {

        InputValidation.nullCheck(annotation);


        try {

            return localSignsControl.addSign(signListId, pddSignId, standardSize, StringTransformer.dashFromNull(annotation));
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param dateOfAdd    date of add for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String annotation) throws ServiceException {
        InputValidation.nullCheck(annotation);
        InputValidation.nullAndDateCheck(dateOfAdd);
        try {

            return localSignsControl.addSign(signListId, pddSignId, standardSize, dateOfAdd, StringTransformer.dashFromNull(annotation));
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param signListId   sign list to set local sign
     * @param pddSignId    pdd sign id for local sign
     * @param standardSize standard size for local sign
     * @param dateOfAdd    date of add for local sign
     * @param dateOFRemove date of remove for local sign
     * @param annotation   local sign annotation
     * @return object if success
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign addSign(int signListId, int pddSignId, int standardSize, String dateOfAdd, String dateOFRemove, String annotation) throws ServiceException {
        InputValidation.nullCheck(annotation);
        InputValidation.nullAndDateCheck(dateOfAdd);
        InputValidation.nullAndDateCheck(dateOFRemove);

        try {

            return localSignsControl.addSign(signListId, pddSignId, standardSize, dateOfAdd, dateOFRemove, StringTransformer.dashFromNull(annotation));
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param signId sign id
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean deleteSign(int signId) throws ServiceException {
        try {

            return localSignsControl.deleteSign(signId);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return {@link MapPoint$LocalSign} where date of remove is null
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint$LocalSign[] getActualMapPoints$LocalSigns() throws ServiceException {
        try {

            return localSignsControl.getActualMapPoints$LocalSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return all {@link MapPoint$LocalSign}
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint$LocalSign[] getAllMapPoints$LocalSigns() throws ServiceException {
        try {

            return localSignsControl.getAllMapPoints$LocalSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param date required date
     * @return {@link MapPoint$LocalSign} where date besides date of add and date of remove
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public MapPoint$LocalSign[] getMapPoints$LocalSignsByDate(String date) throws ServiceException {

        InputValidation.nullAndDateCheck(date);

        try {

            return localSignsControl.getMapPoints$LocalSignsByDate(date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param coordinates point coordinates
     * @return {@link LocalSign} with coordinates param
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign[] getSigns(String coordinates) throws ServiceException {
        InputValidation.pointCheck(coordinates);

        try {

            return localSignsControl.getSigns(coordinates);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return {@link LocalSign}
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign[] getActualSigns() throws ServiceException {
        try {

            return localSignsControl.getActualSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param signsListID sign lists to find
     * @param date        required date
     * @return {@link LocalSign} where date param besides date of add and date of remove
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign[] getActualSigns(int signsListID, String date) throws ServiceException {

        InputValidation.nullAndDateCheck(date);

        try {

            return localSignsControl.getActualSigns(signsListID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return all signs list
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public LocalSign[] getSignsLists() throws ServiceException {
        try {

            return localSignsControl.getSignsLists();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignId  local sign id in jdbc
     * @param dateOfAdd    date of add to set
     * @param dateOfRemove date of remove to set
     * @param annotation   annotation to set
     * @return true if at least one was set
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setParameters(int localSignId, String dateOfAdd, String dateOfRemove, String annotation) throws ServiceException {

        InputValidation.nullCheck(annotation);
        InputValidation.nullAndDateCheck(dateOfAdd);
        InputValidation.nullAndDateCheck(dateOfRemove);

        try {

            return localSignsControl.setParameters(localSignId, dateOfAdd, dateOfRemove, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of add to set
     * @return true if at least one was set
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfAdd(int localSignID, String date) throws ServiceException {

        InputValidation.nullAndDateCheck(date);

        try {

            return localSignsControl.setDateOfAdd(localSignID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of add to set
     * @return true if at least one was set
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfRemove(int localSignID, String date) throws ServiceException {
        InputValidation.nullAndDateCheck(date);

        try {

            return localSignsControl.setDateOfRemove(localSignID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of remove to set
     * @return true if at least one was set
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfAdd(int localSignID, Date date) throws ServiceException {

        InputValidation.nullCheck(date);

        try {

            return localSignsControl.setDateOfAdd(localSignID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param date        date of remove to set
     * @return true if at least one was set
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfRemove(int localSignID, Date date) throws ServiceException {
        InputValidation.nullCheck(date);

        try {

            return localSignsControl.setDateOfRemove(localSignID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set parameters to local sign where id = id param
     *
     * @param localSignID local sign id in jdbc
     * @param annotation  annotation to set
     * @return true if at least one was set
     * @throws ServiceValidationException when {@link ILocalSignsControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link ILocalSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setAnnotation(int localSignID, String annotation) throws ServiceException {

        InputValidation.nullCheck(annotation);

        try {

            return localSignsControl.setAnnotation(localSignID, annotation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
