package rolesOrganisationsUsersController.service.impl;

import rolesOrganisationsUsersController.bean.User;
import rolesOrganisationsUsersController.dao.IUsersController;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;
import rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import rolesOrganisationsUsersController.dao.factory.DaoFactory;
import rolesOrganisationsUsersController.service.IUsersControllerService;
import rolesOrganisationsUsersController.service.exceptions.ServiceException;
import rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

public class UsersControllerService implements IUsersControllerService {

    private static final IUsersController usersController = DaoFactory.getINSTANCE().getUsersController();

    @Override
    public User addUser(String login, String password, int organisation, String name, String surname) throws ServiceException {
        InputValidation.nullAndSymbolsCheck(login);
        InputValidation.nullAndSymbolsCheck(password);
        InputValidation.nullAndSymbolsCheck(name);
        InputValidation.nullAndSymbolsCheck(surname);


        try {

            return usersController.addUser(login, password, organisation, name, surname);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean getBlock(int id) throws ServiceException {
        try {

            return usersController.getBlock(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setBlock(int id, boolean block) throws ServiceException {
        try {

            return usersController.setBlock(id, block);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public User checkLoginPassword(String login, String password) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(login);
        InputValidation.nullAndSymbolsCheck(password);

        try {
            return usersController.checkLoginPassword(login, password);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setLogin(int id, String login, String password, String newLogin) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(login);
        InputValidation.nullAndSymbolsCheck(password);
        InputValidation.nullAndSymbolsCheck(newLogin);

        try {

            return usersController.setLogin(id, login, password, newLogin);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setPassword(int id, String login, String password, String newPassword) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(login);
        InputValidation.nullAndSymbolsCheck(password);
        InputValidation.nullAndSymbolsCheck(newPassword);

        try {

            return usersController.setPassword(id, login, password, newPassword);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setOrganisation(int id, int organisation) throws ServiceException {

        try {

            return usersController.setOrganisation(id, organisation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public int getOrganisation(int userId) throws ServiceException {
        try {

            return usersController.getOrganisation(userId);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public User[] getUsers() throws ServiceException {
        try {

            return usersController.getUsers();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public User[] getUsers(int organisation) throws ServiceException {
        try {

            return usersController.getUsers(organisation);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public User getUser(int id) throws ServiceException {
        try {

            return usersController.getUser(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setInfo(int id, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return usersController.setInfo(id, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setName(int id, String name) throws ServiceException {
        InputValidation.nullAndSymbolsCheckWithRus(name);

        try {

            return usersController.setName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setSurname(int id, String surname) throws ServiceException {
        InputValidation.nullAndSymbolsCheckWithRus(surname);

        try {

            return usersController.setSurname(id, surname);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


}