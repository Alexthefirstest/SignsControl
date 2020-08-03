package by.epam.orders.service;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.service.exceptions.ServiceException;

/**
 * service for working with {@link WorkersCrew}, get, add, set parameters of workers crew
 */
public interface IWorkersCrewControlService {

    /**
     * add workers crew
     *
     * @param creationDate   creation date
     * @param organisationID organisation id to create
     * @return {@link WorkersCrew} if success
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew addWorkersCrew(String creationDate, int organisationID) throws ServiceException;


    /**
     * add workers crew
     *
     * @param creationDate   creation date
     * @param organisationID organisation id to create
     * @param info           info to create
     * @return {@link WorkersCrew} if success
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew addWorkersCrew(String creationDate, String info, int organisationID) throws ServiceException;

    /**
     * delete crew
     *
     * @param id                       to delete
     * @param deletingUserOrganisation organisation of user that delete crew
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean removeWorkersCrew(int id, int deletingUserOrganisation) throws ServiceException;

    /**
     * @param workersCrewID           where set
     * @param date                    to set
     * @param settingUserOrganisation organisation of user that set date
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */
    boolean setDateOfRemove(int workersCrewID, String date, int settingUserOrganisation) throws ServiceException;

    /**
     * @param workersCrewId           where set
     * @param info                    to set
     * @param settingUserOrganisation organisation of user that set info
     * @return true if success
     * @throws ServiceException when get an exception during execution
     */

    boolean setInfo(int workersCrewId, String info, int settingUserOrganisation) throws ServiceException;

    /**
     * add worker to workers in case of worker organisation = workers crew organisation
     *
     * @param workersCrewId          where set
     * @param workerId               to set
     * @param addingUserOrganisation user that add new worker organisation
     * @return {@link WorkersCrew}
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew addWorker(int workersCrewId, int workerId, int addingUserOrganisation) throws ServiceException;

    /**
     * remove worker
     *
     * @param workersCrewId where set
     * @param workersId     to set
     * @param deletingUserOrganisation organisation of user that delete
     * @return {@link WorkersCrew}
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew removeWorker(int workersCrewId, int workersId, int deletingUserOrganisation) throws ServiceException;

    /**
     * get all workers crews
     *
     * @return {@link WorkersCrew} array
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew[] getWorkersCrews() throws ServiceException;

    /**
     * get workers crews where block condition is false
     *
     * @return {@link WorkersCrew} array
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew[] getActiveWorkersCrews() throws ServiceException;

    /**
     * get workers crews with organisation id
     *
     * @param organisationID to find
     * @return {@link WorkersCrew} array
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew[] getWorkersCrews(int organisationID) throws ServiceException;

    /**
     * get workers crews by that have user with id
     *
     * @param userID user id to find
     * @return {@link WorkersCrew} array
     * @throws ServiceException when get an exception during execution
     */

    WorkersCrew[] getWorkersCrewsByUser(int userID) throws ServiceException;

    /**
     * get workers crews without users
     *
     * @return {@link WorkersCrew} array
     * @throws ServiceException when get an exception during execution
     */

    WorkersCrew[] getEmptyWorkersCrews() throws ServiceException;

    /**
     * get workers crews with organisation id param and without users
     *
     * @param organisationID to find
     * @return {@link WorkersCrew} array
     * @throws ServiceException when get an exception during execution
     */
    WorkersCrew[] getEmptyWorkersCrews(int organisationID) throws ServiceException;

}
