package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IRolesController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see by.epam.rolesOrganisationsUsersController.bean.Organisation
 * @see by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException
 */
public class OrganisationsControllerService implements IOrganisationsControllerService {

    /**
     * {@link IOrganisationsController} realisation
     */
    private final IOrganisationsController organisationsController;

    /**
     * constructor with basic {@link IOrganisationsController}
     */
    public OrganisationsControllerService() {
        organisationsController = DaoFactory.getINSTANCE().getOrganisationsController();
    }

    /**
     * constructor with set {@link IOrganisationsController}
     *
     * @param daoOrganisationsController to set
     */
    public OrganisationsControllerService(IOrganisationsController daoOrganisationsController) {
        organisationsController = daoOrganisationsController;
    }

    /**
     * add organisation to jdbc table
     *
     * @param name organisation name
     * @param role organisation members role
     * @return {@link Organisation} if successfully added
     * @throws ServiceValidationException when {@link InputValidation#nullCheck(String)} (String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#addOrganisation(String, int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#addOrganisation(String, int)}
     */
    @Override
    public Organisation addOrganisation(String name, int role) throws ServiceException {

        InputValidation.nullCheck(name);

        try {

            return organisationsController.addOrganisation(name, role);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set name for jdbc organisation
     *
     * @param id   organisation id in jdbc
     * @param name new organisation name in jdbc
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#setName(int, String)} }
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#setName(int, String)}
     */
    @Override
    public boolean setName(int id, String name) throws ServiceException {

        InputValidation.nullCheck(name);

        try {

            return organisationsController.setName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set organisation members role
     *
     * @param id   organisation id in jdbc
     * @param role new organisation members role
     * @return true if success, false - if no
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#setRole(int, int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from  {@link IOrganisationsController#setRole(int, int)}
     */
    @Override
    public boolean setRole(int id, int role) throws ServiceException {

        try {

            return organisationsController.setRole(id, role);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id organisation id in jdbc
     * @return organisation member role or -1 in if can't find user with this id
     * @throws ServiceValidationException when catch  {@link DAOValidationException(String)}
     *                                    from  {@link IOrganisationsController#getRole(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getRole(int)}
     */
//    @Override
//    public int getRole(int id) throws ServiceException {
//
//        try {
//
//            return organisationsController.getRole(id);
//        } catch (DAOValidationException ex) {
//            throw new ServiceValidationException(ex.getMessage());
//        } catch (DAOException ex) {
//            throw new ServiceException(ex);
//        }
//    }

    /**
     * set info in jdbc organisation info field
     *
     * @param id   organisation id in jdbc
     * @param info new organisation info in jdbc
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from  {@link IOrganisationsController#setInfo(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#setInfo(int, String)}
     */
    @Override
    public boolean setInfo(int id, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return organisationsController.setInfo(id, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id organisation id in jdbc
     * @return organisation info in jdbc or null in if can't find user with this id
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getInfo(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getInfo(int)}
     */
    @Override
    public String getInfo(int id) throws ServiceException {

        try {

            return organisationsController.getInfo(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id organisation id in jdbc
     * @return block condition
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getBlock(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getBlock(int)}
     */
    @Override
    public boolean getBlock(int id) throws ServiceException {

        try {

            return organisationsController.getBlock(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * set block in organisation table
     *
     * @param id    organisation id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#setBlock(int, boolean)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#setBlock(int, boolean)}
     */
    @Override
    public boolean setBlock(int id, boolean block) throws ServiceException {

        try {

            return organisationsController.setBlock(id, block);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return organisations array
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getOrganisations()}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getOrganisations()}
     */
    @Override
    public Organisation[] getOrganisations() throws ServiceException {

        try {

            return organisationsController.getOrganisations();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id to find
     * @return organisation like first element of array with id or empty array
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getOrganisation(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getOrganisation(int)}
     */
    @Override
    public Organisation[] getOrganisation(int id) throws ServiceException {
        try {

            return organisationsController.getOrganisation(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id to not hsow
     * @return organisations array without organisation with id param
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getOrganisationsBeside(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getOrganisationsBeside(int)}
     */
    @Override
    public Organisation[] getOrganisationsBeside(int id) throws ServiceException {
        try {

            return organisationsController.getOrganisationsBeside(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return organisations array
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getOrganisations()}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getOrganisations()}
     * @poram roleID
     */
    @Override
    public Organisation[] getOrganisations(int roleID) throws ServiceException {
        try {

            return organisationsController.getOrganisations(roleID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @return unblocked organisations array
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IOrganisationsController#getOrganisations()}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IOrganisationsController#getOrganisations()}
     * @poram roleID
     */
    @Override
    public Organisation[] getUnblockedOrganisations(int roleID) throws ServiceException {
        try {

            return organisationsController.getUnblockedOrganisations(roleID);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


}
