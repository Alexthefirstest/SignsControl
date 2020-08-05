package by.epam.orders.service.impl;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.dao.IWorkersCrewControl;
import by.epam.orders.dao.exceptions.DAOException;
import by.epam.orders.dao.exceptions.DAOValidationException;
import by.epam.orders.dao.factory.DaoFactory;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;

/**
 * service for working with {@link WorkersCrew}, get, add, set parameters of workers crew
 */
public class WorkersCrewControlService implements IWorkersCrewControlService {

    /**
     * {@link IWorkersCrewControl} instance
     */
    private final IWorkersCrewControl workersCrewControl;

    /**
     * {@link IUsersControllerService} instance
     */
    private final IUsersControllerService usersControllerService;


    /**
     * empty constructor
     */
    public WorkersCrewControlService() {
        workersCrewControl = DaoFactory.getINSTANCE().getWorkersCrewControl();
        usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();
    }

    /**
     * constructor with set dao for working
     *
     * @param workersCrewControlDao {@link IWorkersCrewControl}
     */
    WorkersCrewControlService(IWorkersCrewControl workersCrewControlDao) {
        usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();
        workersCrewControl = workersCrewControlDao;
    }

    /**
     * @param usersControllerServiceDao {@link IUsersControllerService}
     */
    WorkersCrewControlService(IUsersControllerService usersControllerServiceDao) {
        usersControllerService = usersControllerServiceDao;
        workersCrewControl = DaoFactory.getINSTANCE().getWorkersCrewControl();
    }


    /**
     * @param usersControllerServiceDao {@link IUsersControllerService}
     * @param workersCrewControlDao     {@link IWorkersCrewControl}
     */
    WorkersCrewControlService(IWorkersCrewControl workersCrewControlDao, IUsersControllerService usersControllerServiceDao) {
        usersControllerService = usersControllerServiceDao;
        workersCrewControl = workersCrewControlDao;
    }

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
     * delete crew if organisation of removing user equal crew role
     *
     * @param id                       to delete
     * @param deletingUserOrganisation organisation or user that delete crew
     * @return true if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public boolean removeWorkersCrew(int id, int deletingUserOrganisation) throws ServiceException {

        checkOrganisation(deletingUserOrganisation, id);

        try {

            return workersCrewControl.removeWorkersCrew(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set date of remove if organisation of removing user equal crew role
     *
     * @param workersCrewID           where set
     * @param date                    to set
     * @param settingUserOrganisation organisation of user that set
     * @return true if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public boolean setDateOfRemove(int workersCrewID, String date, int settingUserOrganisation) throws ServiceException {

        checkOrganisation(settingUserOrganisation, workersCrewID);

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
     * set info if organisation of removing user equal crew role
     *
     * @param workersCrewId           where set
     * @param info                    to set
     * @param settingUserOrganisation organisation of user that set info
     * @return true if success
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     *                                    or data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public boolean setInfo(int workersCrewId, String info, int settingUserOrganisation) throws ServiceException {


        checkOrganisation(settingUserOrganisation, workersCrewId);

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
     * add worker to workers crew in case worker organisation = workers crew organisation
     *
     * @param workersCrewId where set
     * @param workerId      to set
     * @return {@link WorkersCrew}
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew addWorker(int workersCrewId, int workerId, int addingUserOrganisation) throws ServiceException {
        try {

            checkOrganisation(addingUserOrganisation, workersCrewId);

            User user = usersControllerService.getUser(workerId);


            if (user == null || workersCrewControl.getWorkersCrewRemoveDate(workersCrewId) != null) {


                throw new ServiceValidationException("wrong user or crew");
            }

            checkOrganisation(user.getOrganisation().getId(), workersCrewId);

            return workersCrewControl.addWorker(workersCrewId, workerId);
        } catch (DAOValidationException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * remove worker from workers crew in case worker organisation = workers crew organisation
     *
     * @param workersCrewId            where set
     * @param workersId                to set
     * @param deletingUserOrganisation organisation of user that delete
     * @return {@link WorkersCrew}
     * @throws ServiceValidationException when {@link IWorkersCrewControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IWorkersCrewControl} throw {@link DAOException}
     */
    @Override
    public WorkersCrew removeWorker(int workersCrewId, int workersId, int deletingUserOrganisation) throws ServiceException {

        checkOrganisation(deletingUserOrganisation, workersCrewId);

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


    /*
     * throw exception in case userOrganisation != found by id organisation
     */
    private void checkOrganisation(int userOrganisation, int workersCrewId) throws ServiceException {
        try {

            int organisation = workersCrewControl.getWorkersCrewOrganisationID(workersCrewId);

            if (organisation != userOrganisation) {
                throw new ServiceValidationException("wrong organisation or crew");
            }


        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }

    }
}
