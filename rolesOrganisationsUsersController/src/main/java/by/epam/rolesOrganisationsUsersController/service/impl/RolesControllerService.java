package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.IRolesControllerService;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see Role
 * @see ServiceException
 */
public class RolesControllerService implements IRolesControllerService {

    /**
     * {@link IRolesController} realisation
     */
    private final IRolesController rolesController = DaoFactory.getINSTANCE().getRolesController();

    /**
     * add role to jdbc table
     *
     * @param name role name
     * @return {@link Role} if successfully added
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IRolesController#addRole(String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#addRole(String)}
     */
    @Override
    public Role addRole(String name) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(name);

        try {

            return rolesController.addRole(name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * delete role from jdbc table
     *
     * @param id role id
     * @return true if success, false - if no
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IRolesController#deleteRole(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#deleteRole(int)}
     */
    @Override
    public boolean deleteRole(int id) throws ServiceException {

        try {

            return rolesController.deleteRole(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);

        }
    }

    /**
     * get array of all roles
     *
     * @return {@link Role} array
     * @throws ServiceValidationException when { catch {@link DAOValidationException}
     *                                    from {@link IRolesController#getRoles()}  }
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#getRoles()}
     */
    @Override
    public Role[] getRoles() throws ServiceException {

        try {

            return rolesController.getRoles();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id   role id
     * @param name new role name
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IRolesController#setName(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IRolesController#setName(int, String)}
     */
    @Override
    public boolean setName(int id, String name) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(name);

        try {

            return rolesController.setName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
