package by.epam.orders.dao;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.exceptions.DAOException;

public interface IWorkersCrewControl {

    WorkersCrew addWorkersCrew(String creationDate) throws DAOException;

    WorkersCrew addWorkersCrew(String creationDate, String info) throws DAOException;

    boolean removeWorkersCrew(int id) throws DAOException;

    boolean setDateOfRemove(int workersCrewID, String date) throws DAOException;

    WorkersCrew addWorker(int workersCrewId, int workersId) throws DAOException;

    WorkersCrew removeWorker(int workersCrewId, int workersId) throws DAOException;

    WorkersCrew[] getWorkersCrews() throws DAOException;

    WorkersCrew[] getActiveWorkersCrews() throws DAOException;

    WorkersCrew[] getWorkersCrews(int organisationID) throws DAOException;

    WorkersCrew[] getEmptyWorkersCrews() throws DAOException;

}
