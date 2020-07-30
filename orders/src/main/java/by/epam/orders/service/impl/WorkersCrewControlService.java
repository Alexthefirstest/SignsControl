package by.epam.orders.service.impl;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.dao.factory.DaoFactory;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;

/**
 * service for working with {@link WorkersCrew}, get, add, set parameters of workers crew
 */
public class WorkersCrewControlService implements IWorkersCrewControlService {

    /**
     * {@link IWorkersCrewControl} instance
     */
    private static final IWorkersCrewControl workersCrewControl = DaoFactory.getINSTANCE().getWorkersCrewControl();

    /**
     * add workers crew
     *
     * @param creationDate   creation date
     * @param organisationID organisation id to create
     * @return {@link WorkersCrew} if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew addWorkersCrew(String creationDate, int organisationID) throws ServiceException {

        InputValidation.nullAndDateCheck(creationDate);

        try {

            return workersCrewControl.addWorkersCrew(creationDate, organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add workers crew
     *
     * @param creationDate   creation date
     * @param organisationID organisation id to create
     * @param info           info to create
     * @return {@link WorkersCrew} if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew addWorkersCrew(String creationDate, String info, int organisationID) throws ServiceException {

        InputValidation.nullAndDateCheck(creationDate);
        InputValidation.nullCheck(info);

        try {

            return workersCrewControl.addWorkersCrew(creationDate, info, organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * delete crew
     *
     * @return true if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public boolean removeWorkersCrew(int id) throws ServiceException {
        try {

            return workersCrewControl.removeWorkersCrew(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param workersCrewID where set
     * @param date          to set
     * @return true if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfRemove(int workersCrewID, String date) throws ServiceException {

        InputValidation.nullAndDateCheck(date);

        try {

            return workersCrewControl.setDateOfRemove(workersCrewID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param workersCrewId where set
     * @param info          to set
     * @return true if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public boolean setInfo(int workersCrewId, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return workersCrewControl.setInfo(workersCrewId, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add worker to workers crew
     *
     * @param workersCrewId where set
     * @param workersId     to set
     * @return {@link WorkersCrew}
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew addWorker(int workersCrewId, int workersId) throws ServiceException {
        try {

            return workersCrewControl.addWorker(workersCrewId, workersId);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * remove worker
     *
     * @param workersCrewId where set
     * @param workersId     to set
     * @return {@link WorkersCrew}
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew removeWorker(int workersCrewId, int workersId) throws ServiceException {

        try {

            return workersCrewControl.removeWorker(workersCrewId, workersId);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get all workers crews
     *
     * @return {@link WorkersCrew} array
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew[] getWorkersCrews() throws ServiceException {
        try {

            return workersCrewControl.getWorkersCrews();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get workers crews where block condition is false
     *
     * @return {@link WorkersCrew} array
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew[] getActiveWorkersCrews() throws ServiceException {

        try {

            return workersCrewControl.getActiveWorkersCrews();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get workers crews with organisation id
     *
     * @param organisationID to find
     * @return {@link WorkersCrew} array
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew[] getWorkersCrews(int organisationID) throws ServiceException {
        try {

            return workersCrewControl.getWorkersCrews(organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get workers crews by that have user with id
     *
     * @param userID user id to find
     * @return {@link WorkersCrew} array
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */

    @Override
    public WorkersCrew[] getWorkersCrewsByUser(int userID) throws ServiceException {
        try {

            return workersCrewControl.getWorkersCrewsByUser(userID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get workers crews without users
     *
     * @return {@link WorkersCrew} array
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */

    @Override
    public WorkersCrew[] getEmptyWorkersCrews() throws ServiceException {
        try {

            return workersCrewControl.getEmptyWorkersCrews();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get workers crews with organisation id param and without users
     *
     * @param organisationID to find
     * @return {@link WorkersCrew} array
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew[] getEmptyWorkersCrews(int organisationID) throws ServiceException {
        try {

            return workersCrewControl.getEmptyWorkersCrews();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
