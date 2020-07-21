package by.epam.orders.service;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.service.exceptions.ServiceException;

public interface IWorkersCrewControlService {

    WorkersCrew addWorkersCrew(String creationDate, int organisationID) throws ServiceException;

    WorkersCrew addWorkersCrew(String creationDate, String info, int organisationID) throws ServiceException;

    boolean removeWorkersCrew(int id) throws ServiceException;

    boolean setDateOfRemove(int workersCrewID, String date) throws ServiceException;
    boolean setInfo(int workersCrewId, String info) throws ServiceException;

    WorkersCrew addWorker(int workersCrewId, int workersId) throws ServiceException;

    WorkersCrew removeWorker(int workersCrewId, int workersId) throws ServiceException;

    WorkersCrew[] getWorkersCrews() throws ServiceException;

    WorkersCrew[] getActiveWorkersCrews() throws ServiceException;

    WorkersCrew[] getWorkersCrews(int organisationID) throws ServiceException;

    WorkersCrew[] getWorkersCrewsByUser(int userID) throws ServiceException;

    WorkersCrew[] getEmptyWorkersCrews() throws ServiceException;
    WorkersCrew[] getEmptyWorkersCrews(int organisationID) throws ServiceException;

}
