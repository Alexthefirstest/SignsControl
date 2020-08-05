package by.epam.orders.dao.impl;

import by.epam.orders.bean.FactoryType;
import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * sql workers crew control
 */
public class WorkersCrewControl implements IWorkersCrewControl {

    /**
     * logger
     */
    private static Logger logger = LogManager.getLogger(WorkersCrew.class);

    /**
     * select use last inserted id
     */
    private static final String SQL_SELECT_USE_LAST_INSERTED_ID =
            "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
                    "  ui.name, ui.surname, ui.info,  " +
                    " c.id, c.creation_date, c.remove_date, c.info,   " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
                    " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
                    "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
                    " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
                    " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where c.id=last_insert_id();";

    /**
     * sql select user id
     */
    private static final String SQL_SELECT_USE_ID = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,   " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
            " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where c.id=";

    /**
     * sql add crew to table
     */
    private static final String SQL_ADD_CREW = "INSERT INTO `crews` (`creation_date`, organisation) VALUES (?,?);";

    /**
     * sql add crew with info
     */
    private static final String SQL_ADD_CREW_WITH_INFO = "INSERT INTO `crews` (`creation_date`, info, organisation) VALUES (?, ?,?);";

    /**
     * sql selete crew
     */
    private static final String SQL_DELETE_CREW = "DELETE FROM `crews` WHERE (`id` = ?);";

    /**
     * sql delete worker from crew
     */
    private static final String SQL_DELETE_WORKER = "DELETE FROM workers_crews WHERE (workers_crew_id=? AND worker=?);";

    /**
     * sql set crew info
     */
    private static final String SET_INFO = "UPDATE `crews` SET `info` = ? WHERE (`id` = ?);";

    /**
     * sql set date of remove
     */
    private static final String SET_DATE_OF_REMOVE = "UPDATE `crews` SET `remove_date` = ? WHERE (`id` = ?);";

    /**
     * sql add worker to crew
     */
    private static final String ADD_WORKER = "INSERT INTO `workers_crews` (`workers_crew_id`, worker) VALUES (?,?); ";

    /**
     * sql get workers crew organisation
     */
    private static final String GET_WORKERS_CREW_ORGANISATION_ID = "SELECT organisation FROM crews where id=?";

    /**
     * sql workers crew date of remove
     */
    private static final String GET_WORKERS_CREW_DATE_OF_REMOVE = "SELECT remove_date FROM crews where id=?";

    /**
     * sql get workers
     */
    private static final String GET_WORKERS_CREWS = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,   " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id order by c.id DESC, c.remove_date";

    /**
     * sql get unblocked workers crews
     */
    private static final String GET_ACTIVE_WORKERS_CREWS = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,  " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id where c.remove_date is null order by c.id DESC";

    /**
     * sql get workers crews by organisation id
     */
    private static final String GET_WORKERS_CREWS_BY_ORGANISATION = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,  " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id where c.organisation=? order by c.id DESC, c.remove_date";

    /**
     * sql get workers crews by user id
     */
    private static final String GET_WORKERS_CREWS_BY_USER = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,   " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id where u.id = ? order by c.id DESC, c.remove_date";

    /**
     * sql get workers crews without users
     */
    private static final String GET_EMPTY_WORKERS_CREWS = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info, " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
            " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where ui.name is null order by c.id DESC, c.remove_date";

    /**
     * sql get workers crews without users by organisation id
     */
    private static final String GET_EMPTY_WORKERS_CREWS_BY_ORGANISATION = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info, " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
            " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where ui.name is null AND c.organisation=? order by c.id DESC, c.remove_date";

    /**
     * add workers crew
     *
     * @param creationDate   date when created
     * @param organisationID id of organisation workers_crew created
     * @return object if success
     * @throws DAOValidationException if creationDate is null
     * @throws DAOException           when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew addWorkersCrew(String creationDate, int organisationID) throws DAOException {
        try {

            return (WorkersCrew) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_ADD_CREW, SQL_SELECT_USE_LAST_INSERTED_ID, new WorkersCrew(), creationDate, organisationID);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.warn("add fail: string is null ", ex);
                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

    /**
     * add workers crew
     *
     * @param creationDate   date when created
     * @param organisationID id of organisation workers_crew created
     * @param info           info to set in workers crew
     * @return object if success
     * @throws DAOValidationException if creationDate or info is null
     * @throws DAOException           when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew addWorkersCrew(String creationDate, String info, int organisationID) throws DAOException {
        try {

            return (WorkersCrew) RequestExecutor.createFieldUseDifferentParameters
                    (SQL_ADD_CREW_WITH_INFO, SQL_SELECT_USE_LAST_INSERTED_ID, new WorkersCrew(), creationDate, info, organisationID);

        } catch (SQLException ex) {

            if ((Pattern.matches(".*cannot be null.*", ex.getMessage()))) {
                logger.warn("add fail: string is null ", ex);
                throw new DAOValidationException("string is null");
            }


            throw new DAOException(ex);

        }
    }

    /**
     * add workers crew
     *
     * @param id to delete
     * @return true if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createFieldUseDifferentParameters(String, String, FactoryType, Object...)}
     */
    @Override
    public boolean removeWorkersCrew(int id) throws DAOException {
        try {

            return RequestExecutor.createFieldUseDifferentParameters
                    (SQL_DELETE_CREW, SQL_SELECT_USE_LAST_INSERTED_ID, new WorkersCrew(), id) == null;

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * @param workersCrewId id where set
     * @param date          date of remove to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setDateOfRemove(int workersCrewId, String date) throws DAOException {

        try {
            return RequestExecutor.setField(SET_DATE_OF_REMOVE, workersCrewId, date);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * @param workersCrewId id where set
     * @param info          to set
     * @return true if success or false in other case
     * @throws DAOException when catch exception from {@link RequestExecutor#setField(String, int, String)}
     */
    @Override
    public boolean setInfo(int workersCrewId, String info) throws DAOException {

        try {
            return RequestExecutor.setField(SET_INFO, workersCrewId, info);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    /**
     * add worker to crew
     *
     * @param workersCrewId to set
     * @param workerId      to add
     * @return {@link WorkersCrew} where set if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public WorkersCrew addWorker(int workersCrewId, int workerId) throws DAOException {

        try {

            return (WorkersCrew) RequestExecutor.createField
                    (ADD_WORKER, SQL_SELECT_USE_ID + workersCrewId, new WorkersCrew(), workersCrewId, workerId);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    /**
     * remove worker from crew
     *
     * @param workersCrewId from delete
     * @param workersId     to delete
     * @return null if success
     * @throws DAOException when catch exception from {@link RequestExecutor#createField(String, String, FactoryType, int...)}
     */
    @Override
    public WorkersCrew removeWorker(int workersCrewId, int workersId) throws DAOException {

        try {

            return (WorkersCrew) RequestExecutor.createField
                    (SQL_DELETE_WORKER, SQL_SELECT_USE_ID + workersCrewId, new WorkersCrew(), workersCrewId, workersId);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }


    /**
     * @param id id of workers crew
     * @return organisation of workers crew or -1 if can't find
     * @throws DAOException when catch exception from {@link RequestExecutor#getInt(String, int)}
     */
    @Override
    public int getWorkersCrewOrganisationID(int id) throws DAOException {

        try {

            return RequestExecutor.getInt
                    (GET_WORKERS_CREW_ORGANISATION_ID, id);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * @param id id of workers crew
     * @return remove date of workers crew or null if can't find
     * @throws DAOException when catch exception from {@link RequestExecutor#getString(String, int)}
     */
    @Override
    public String getWorkersCrewRemoveDate(int id) throws DAOException {

        try {

            return RequestExecutor.getString
                    (GET_WORKERS_CREW_DATE_OF_REMOVE, id);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }


    /**
     * get all workers crews
     *
     * @return {@link WorkersCrew} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew[] getWorkersCrews() throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_WORKERS_CREWS, new WorkersCrew());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get not blocked workers crews
     *
     * @return {@link WorkersCrew} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew[] getActiveWorkersCrews() throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_ACTIVE_WORKERS_CREWS, new WorkersCrew());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get workers crews with organisation id
     *
     * @param organisationID organisation to find
     * @return {@link WorkersCrew} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew[] getWorkersCrews(int organisationID) throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_WORKERS_CREWS_BY_ORGANISATION, new WorkersCrew(), organisationID);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    /**
     * get workers crews have user with id
     *
     * @param userID id of user to find
     * @return {@link WorkersCrew} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew[] getWorkersCrewsByUser(int userID) throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_WORKERS_CREWS_BY_USER, new WorkersCrew(), userID);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }


    /**
     * get workers crews without users
     *
     * @return {@link WorkersCrew} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew[] getEmptyWorkersCrews() throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_EMPTY_WORKERS_CREWS, new WorkersCrew());

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }


    /**
     * get workers crews without users of by organisation id
     *
     * @param organisationID to find
     * @return {@link WorkersCrew} array
     * @throws DAOException when catch exception from {@link RequestExecutor#getSignsStaff(String, FactoryType, Object...)}
     */
    @Override
    public WorkersCrew[] getEmptyWorkersCrews(int organisationID) throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_EMPTY_WORKERS_CREWS_BY_ORGANISATION, new WorkersCrew(), organisationID);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }
}
