package by.epam.orders.service.impl;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.dao.factory.DaoFactory;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;

public class WorkersCrewControlService implements IWorkersCrewControlService {

    private static final IWorkersCrewControl workersCrewControl = DaoFactory.getINSTANCE().getWorkersCrewControl();

    @Override
    public WorkersCrew addWorkersCrew(String creationDate, int organisationID) throws ServiceException {
        try {

            return workersCrewControl.addWorkersCrew(creationDate, organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public WorkersCrew addWorkersCrew(String creationDate, String info, int organisationID) throws ServiceException {
        try {

            return workersCrewControl.addWorkersCrew(creationDate, info, organisationID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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

    @Override
    public boolean setDateOfRemove(int workersCrewID, String date) throws ServiceException {
        try {

            return workersCrewControl.setDateOfRemove(workersCrewID, date);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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
}
