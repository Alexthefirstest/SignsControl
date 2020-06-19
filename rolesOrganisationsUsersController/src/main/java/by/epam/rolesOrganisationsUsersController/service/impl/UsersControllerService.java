package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.dao.IUsersController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import org.mindrot.jbcrypt.BCrypt;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;

public class UsersControllerService implements IUsersControllerService {

    private final IUsersController usersController = DaoFactory.getINSTANCE().getUsersController();

    private boolean checkPassword() {

        return false;
    }

    private String hashPassword() {

        return null;
    }

    @Override
    public User addUser(String login, String password, int organisation, String name, String surname) throws ServiceException {
        InputValidation.nullAndSymbolsCheck(login);
        InputValidation.nullAndSymbolsCheck(password);
        InputValidation.nullAndSymbolsCheck(name);
        InputValidation.nullAndSymbolsCheck(surname);


        try {

            return usersController.addUser(login, BCrypt.hashpw(password, BCrypt.gensalt(8)), organisation, name, surname);
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

            String dbpass = usersController.getPassword(login);

            if (dbpass == null) {
                return null;
            }

            return BCrypt.checkpw(password, dbpass) ? usersController.getUser(login) : null;

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

            String oldPassword = usersController.getPassword(login);

            if (oldPassword == null) {
                return false;
            }

            return BCrypt.checkpw(password, oldPassword) && usersController.setLogin(id, newLogin);
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

            String oldPassword = usersController.getPassword(login);

            if (oldPassword == null) {
                return false;
            }

            return BCrypt.checkpw(password, oldPassword) && usersController.setPassword(id, BCrypt.hashpw(newPassword, BCrypt.gensalt(8)));
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