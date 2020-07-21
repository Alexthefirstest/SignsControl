package by.epam.orders.dao.impl;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class WorkersCrewControl implements IWorkersCrewControl {

    Logger logger = LogManager.getLogger(WorkersCrew.class);

    private static final String SQL_SELECT_USE_LAST_INSERTED_ID =
            "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
                    "  ui.name, ui.surname, ui.info,  " +
                    " c.id, c.creation_date, c.remove_date, c.info,   " +
                    "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
                    " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
                    "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
                    " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
                    " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where c.id=last_insert_id();";

    private static final String SQL_SELECT_USE_ID = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,   " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
            " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where c.id=";

    private static final String SQL_ADD_CREW = "INSERT INTO `crews` (`creation_date`, organisation) VALUES (?,?);";
    private static final String SQL_ADD_CREW_WITH_INFO = "INSERT INTO `crews` (`creation_date`, info, organisation) VALUES (?, ?,?);";
    private static final String SQL_DELETE_CREW = "DELETE FROM `crews` WHERE (`id` = ?);";
    private static final String SQL_DELETE_WORKER = "DELETE FROM workers_crews WHERE (workers_crew_id=? AND worker=?);";

    private static final String SET_INFO = "UPDATE `crews` SET `info` = ? WHERE (`id` = ?);";
    private static final String SET_DATE_OF_REMOVE = "UPDATE `crews` SET `remove_date` = ? WHERE (`id` = ?);";
    private static final String ADD_WORKER = "INSERT INTO `workers_crews` (`workers_crew_id`, worker) VALUES (?,?); ";
    private static final String GET_WORKERS_CREWS = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,   " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id order by c.id DESC, c.remove_date";


    private static final String GET_ACTIVE_WORKERS_CREWS = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,  " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id where c.remove_date is null order by c.id DESC";
    private static final String GET_WORKERS_CREWS_BY_ORGANISATION = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,  " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id where c.organisation=? order by c.id DESC, c.remove_date";

    private static final String GET_WORKERS_CREWS_BY_USER = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info,   " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            "  join users as u on u.id=wc.worker  join user_info as ui on u.id=ui.id  join organisations as o on o.id=u.organisation  join user_roles as   " +
            " ur on u.role=ur.id  join organisation_roles as orr on o.role=orr.id where u.id = ? order by c.id DESC, c.remove_date";

    private static final String GET_EMPTY_WORKERS_CREWS = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info, " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
            " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where ui.name is null order by c.id DESC, c.remove_date";

    private static final String GET_EMPTY_WORKERS_CREWS_BY_ORGANISATION = "SELECT u.id, u.login, u.role, ur.role, o.id, o.name, o.role, orr.role, o.is_blocked, o.info,  u.is_blocked,  " +
            "  ui.name, ui.surname, ui.info,  " +
            " c.id, c.creation_date, c.remove_date, c.info, " +
            "o1.id, o1.name, o1.role, orr1.role,  o1.is_blocked, o1.info " +
            " FROM workers_crews as wc right join crews as c on wc.workers_crew_id=c.id  " +
            "join organisations as o1 on o1.id=c.organisation join organisation_roles as orr1 on o1.role=orr1.id " +
            " left join users as u on u.id=wc.worker left join user_info as ui on u.id=ui.id left join organisations as o on o.id=u.organisation left join user_roles as   " +
            " ur on u.role=ur.id left join organisation_roles as orr on o.role=orr.id  where ui.name is null AND c.organisation=? order by c.id DESC, c.remove_date";

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

    @Override
    public boolean removeWorkersCrew(int id) throws DAOException {
        try {

            return RequestExecutor.createFieldUseDifferentParameters
                    (SQL_DELETE_CREW, SQL_SELECT_USE_LAST_INSERTED_ID, new WorkersCrew(), id) == null;

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public boolean setDateOfRemove(int workersCrewId, String date) throws DAOException {

        try {
            return RequestExecutor.setField(SET_DATE_OF_REMOVE, workersCrewId, date);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public boolean setInfo(int workersCrewId, String info) throws DAOException {

        try {
            return RequestExecutor.setField(SET_INFO, workersCrewId, info);
        } catch (SQLException ex) {

            throw new DAOException(ex);
        }
    }

    @Override
    public WorkersCrew addWorker(int workersCrewId, int workerId) throws DAOException {

        try {

            return (WorkersCrew) RequestExecutor.createField
                    (ADD_WORKER, SQL_SELECT_USE_ID + workersCrewId, new WorkersCrew(), workersCrewId, workerId);

        } catch (SQLException ex) {

            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew removeWorker(int workersCrewId, int workersId) throws DAOException {

        try {

            return (WorkersCrew) RequestExecutor.createField
                    (SQL_DELETE_WORKER, SQL_SELECT_USE_ID + workersCrewId, new WorkersCrew(), workersCrewId, workersId);

        } catch (SQLException ex) {


            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew[] getWorkersCrews() throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_WORKERS_CREWS, new WorkersCrew());

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew[] getActiveWorkersCrews() throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_ACTIVE_WORKERS_CREWS, new WorkersCrew());

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew[] getWorkersCrews(int organisationID) throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_WORKERS_CREWS_BY_ORGANISATION, new WorkersCrew(), organisationID);

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew[] getWorkersCrewsByUser(int userID) throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_WORKERS_CREWS_BY_USER, new WorkersCrew(), userID);

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew[] getEmptyWorkersCrews() throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_EMPTY_WORKERS_CREWS, new WorkersCrew());

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }

    @Override
    public WorkersCrew[] getEmptyWorkersCrews(int organisationID) throws DAOException {
        try {

            return (WorkersCrew[]) RequestExecutor.getSignsStaff(GET_EMPTY_WORKERS_CREWS_BY_ORGANISATION, new WorkersCrew(), organisationID);

        } catch (SQLException ex) {

            logger.warn("select all use organisation fail");
            throw new DAOException(ex);

        }
    }
}
