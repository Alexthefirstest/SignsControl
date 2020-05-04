package rolesOrganisationsUsersController.service.impl;

import rolesOrganisationsUsersController.bean.Role;
import rolesOrganisationsUsersController.dao.IRolesController;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import rolesOrganisationsUsersController.dao.factory.DaoFactory;
import rolesOrganisationsUsersController.service.IRolesControllerService;
import rolesOrganisationsUsersController.service.exceptions.ServiceException;
import rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

public class RolesControllerService implements IRolesControllerService {

    private final IRolesController rolesController = DaoFactory.getINSTANCE().getRolesController();


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
