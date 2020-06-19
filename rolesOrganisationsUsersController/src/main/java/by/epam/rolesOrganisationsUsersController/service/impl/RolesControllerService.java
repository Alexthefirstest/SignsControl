package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.dao.IRolesController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.IRolesControllerService;

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
