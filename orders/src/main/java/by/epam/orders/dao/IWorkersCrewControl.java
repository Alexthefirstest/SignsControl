package by.epam.orders.dao;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.exceptions.DAOException;

public interface IWorkersCrewControl {

    /**
     * add workers crew
     *
     * @param creationDate   date when created
     * @param organisationID id of organisation workers_crew created
     * @return object if success
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew addWorkersCrew(String creationDate, int organisationID) throws DAOException;


    /**
     * add workers crew
     *
     * @param creationDate   date when created
     * @param organisationID id of organisation workers_crew created
     * @param info           info to set in workers crew
     * @return object if success
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew addWorkersCrew(String creationDate, String info, int organisationID) throws DAOException;


    /**
     * add workers crew
     *
     * @param id to delete
     * @return true if success
     * @throws DAOException when get an exception during execution
     */
    boolean removeWorkersCrew(int id) throws DAOException;

    /**
     * @param workersCrewID id where set
     * @param date          date of remove to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean setDateOfRemove(int workersCrewID, String date) throws DAOException;

    /**
     * @param workersCrewId id where set
     * @param info          to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean setInfo(int workersCrewId, String info) throws DAOException;

    /**
     * add worker to crew
     *
     * @param workersCrewId to set
     * @param workerId      to add
     * @return {@link WorkersCrew} where set if success
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew addWorker(int workersCrewId, int workerId) throws DAOException;

    /**
     * remove worker from crew
     *
     * @param workersCrewId from delete
     * @param workersId     to delete
     * @return null if success
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew removeWorker(int workersCrewId, int workersId) throws DAOException;


    /**
     * @param id id of workers crew
     * @return organisation of workers crew or -1 if can't find
     * @throws DAOException when get an exception during execution
     */
     int getWorkersCrewOrganisationID(int id) throws DAOException;


    /**
     * @param id id of workers crew
     * @return remove date of workers crew or null if can't find
     * @throws DAOException when get an exception during execution
     */

     String getWorkersCrewRemoveDate(int id) throws DAOException;

    /**
     * get all workers crews
     *
     * @return {@link WorkersCrew} array
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew[] getWorkersCrews() throws DAOException;

    /**
     * get not blocked workers crews
     *
     * @return {@link WorkersCrew} array
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew[] getActiveWorkersCrews() throws DAOException;

    /**
     * get workers crews with organisation id
     *
     * @param organisationID organisation to find
     * @return {@link WorkersCrew} array
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew[] getWorkersCrews(int organisationID) throws DAOException;

    /**
     * get workers crews have user with id
     *
     * @param userID id of user to find
     * @return {@link WorkersCrew} array
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew[] getWorkersCrewsByUser(int userID) throws DAOException;

    /**
     * get workers crews without users
     *
     * @return {@link WorkersCrew} array
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew[] getEmptyWorkersCrews() throws DAOException;


    /**
     * get workers crews without users of by organisation id
     *
     * @param organisationID to find
     * @return {@link WorkersCrew} array
     * @throws DAOException when get an exception during execution
     */
    WorkersCrew[] getEmptyWorkersCrews(int organisationID) throws DAOException;

}
