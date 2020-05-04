package rolesOrganisationsUsersController.service.impl;

import rolesOrganisationsUsersController.bean.Organisation;
import rolesOrganisationsUsersController.dao.IOrganisationsController;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import rolesOrganisationsUsersController.dao.factory.DaoFactory;
import rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import rolesOrganisationsUsersController.service.exceptions.ServiceException;
import rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

public class OrganisationsControllerService implements IOrganisationsControllerService {

    private final IOrganisationsController organisationsController = DaoFactory.getINSTANCE().getOrganisationsController();


    @Override
    public Organisation addOrganisation(String name, int role) throws ServiceException {
        InputValidation.nullAndSymbolsCheck(name);

        try {

            return organisationsController.addOrganisation(name, role);
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

            return organisationsController.setName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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

    @Override
    public int getRole(int id) throws ServiceException {
        try {

            return organisationsController.getRole(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setInfo(int id, String info) throws ServiceException {
        InputValidation.nullAndSymbolsCheck(info);

        try {

            return organisationsController.setName(id, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

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
}