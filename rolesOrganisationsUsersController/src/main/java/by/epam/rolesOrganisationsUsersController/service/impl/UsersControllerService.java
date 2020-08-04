package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.dao.IUsersController;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOValidationException;
import by.epam.rolesOrganisationsUsersController.dao.factory.DaoFactory;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IUsersController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see by.epam.rolesOrganisationsUsersController.bean.Organisation
 * @see by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException
 */
public class UsersControllerService implements IUsersControllerService {

    /**
     * {@link IUsersController} realisation
     */
    private final IUsersController usersController = DaoFactory.getINSTANCE().getUsersController();

    /**
     * add user to jdbc table
     *
     * @param login        user login
     * @param password     user password
     * @param role         user role
     * @param organisation user organisation
     * @param name         user name
     * @param surname      user surname
     * @return {@link User} if success
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)} throw it
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#addUser(String, String, int, int, String, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#addUser(String, String, int, int, String, String)}
     */
    @Override
    public User addUser(String login, String password, int role, int organisation, String name, String surname) throws ServiceException {

        InputValidation.nullAndSymbolsCheck(login);
        InputValidation.nullAndSymbolsCheck(password);
        InputValidation.nullAndSymbolsCheck(name);
        InputValidation.nullAndSymbolsCheck(surname);


        try {

            return usersController.addUser(login, BCrypt.hashpw(password, BCrypt.gensalt(8)), role, organisation, name, surname);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id user id in jdbc
     * @return block condition
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IUsersController#getBlock(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getBlock(int)}
     */
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

    /**
     * set block for user
     *
     * @param id    user id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setBlock(int, boolean)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#setBlock(int, boolean)}
     */
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

    /**
     * find user with login in data base and check their password
     *
     * @param login    user login from to check
     * @param password user password from to check
     * @return {@link User} if user with this login and password exist or null in other case
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)} throw it
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#getUser(String)} or {@link IUsersController#getPassword(String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getUser(String)} or {@link IUsersController#getPassword(String)}
     */
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

    /**
     * set new login for user
     *
     * @param id    user id in jdbc
     * @param login new user login
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setLogin(int, String)} or {@link IUsersController#getPassword(String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getPassword(String)} or {@link IUsersController#setLogin(int, String)}
     */
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

    /**
     * set new user password
     *
     * @param id          user id in jdbc
     * @param login       user login in jdbc
     * @param password    user password in jdbc
     * @param newPassword new user password
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#getPassword(String)} or {@link IUsersController#setPassword(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getPassword(String)} or {@link IUsersController#setPassword(int, String)}
     */
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

    /**
     * set new user organisation
     *
     * @param id           user id
     * @param organisation new organisation id
     * @return true if success, false - if no
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setOrganisation(int, int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#setOrganisation(int, int)}
     */
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

//    /**
//     * @param userId user id
//     * @return organisation of this user id or -1 if user do not exist
//     * @throws ServiceValidationException when catch {@link DAOValidationException}
//     *                                    from {@link IUsersController#getOrganisation(int)}
//     * @throws ServiceException           when catch {@link DAOException}
//     *                                    from {@link IUsersController#getOrganisation(int)}
//     */
//    @Override
//    public int getOrganisation(int userId) throws ServiceException {
//
//        try {
//
//            return usersController.getOrganisation(userId);
//        } catch (DAOValidationException ex) {
//            throw new ServiceValidationException(ex.getMessage());
//        } catch (DAOException ex) {
//            throw new ServiceException(ex);
//        }
//    }

    /**
     * get all users from data base
     *
     * @return {@link User} array
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IUsersController#getUsers()}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getUsers()}
     */
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

    /**
     * get all users from data base with organisation id param
     *
     * @param organisation organisation id
     * @return {@link User} array
     * @throws ServiceValidationException when  catch {@link DAOValidationException}
     *                                    from {@link IUsersController#getUsers(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getUsers(int)}
     */
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

    /**
     * get user by id
     *
     * @param id user id
     * @return {@link User} with param id
     * @throws ServiceValidationException when catch {@link DAOValidationException}
     *                                    from {@link IUsersController#getUser(int)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#getUser(int)}
     */
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

    /**
     * set info for user
     *
     * @param id   user id in jdbc
     * @param info new  user info in jdbc
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setInfo(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#setInfo(int, String)}
     */
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

    /**
     * set new name for user
     *
     * @param id   user id in jdbc
     * @param name new user name
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setName(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#setName(int, String)}
     */
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

    /**
     * set new surname for user
     *
     * @param id      user id in jdbc
     * @param surname new  user surname
     * @return true if success, false - if no
     * @throws ServiceValidationException when {@link InputValidation#nullAndSymbolsCheck(String)}
     *                                    or catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setSurname(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#setSurname(int, String)}
     */
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

    /**
     * set new surname for user
     *
     * @param id   user id in jdbc
     * @param role new  user role
     * @return true if success, false - if no
     * @throws ServiceValidationException catch {@link DAOValidationException}
     *                                    from {@link IUsersController#setSurname(int, String)}
     * @throws ServiceException           when catch {@link DAOException}
     *                                    from {@link IUsersController#setSurname(int, String)}
     */
    @Override
    public boolean setRole(int id, int role) throws ServiceException {


        try {

            return usersController.setRole(id, role);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }


}