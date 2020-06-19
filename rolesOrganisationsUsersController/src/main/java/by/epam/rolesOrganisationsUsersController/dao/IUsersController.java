package by.epam.rolesOrganisationsUsersController.dao;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;

public interface IUsersController {

    User addUser(String login, String password, int organisation, String name, String surname) throws DAOException;

    boolean getBlock(int id) throws DAOException;

    boolean setBlock(int id, boolean block) throws DAOException;

    boolean setLogin(int id, String login) throws DAOException;

    boolean setPassword(int id, String password) throws DAOException;

    String getPassword(String login) throws DAOException;

    boolean setOrganisation(int id, int organisation) throws DAOException;

    int getOrganisation(int userId) throws DAOException;

    User[] getUsers() throws DAOException;

    User[] getUsers(int organisation) throws DAOException;

    User getUser(int id) throws DAOException;

    User getUser(String login) throws DAOException;

    boolean setInfo(int id, String info) throws DAOException;

    boolean setName(int id, String name) throws DAOException;

    boolean setSurname(int id, String surname) throws DAOException;


}
