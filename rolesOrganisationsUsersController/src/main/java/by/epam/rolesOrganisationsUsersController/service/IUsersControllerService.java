package by.epam.rolesOrganisationsUsersController.service;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.bean.User;

public interface IUsersControllerService {

    User addUser(String login, String password, int organisation, String name, String surname) throws ServiceException;

    boolean getBlock(int id) throws ServiceException;

    boolean setBlock(int id, boolean block) throws ServiceException;

    User checkLoginPassword(String login, String password) throws ServiceException;

    boolean setLogin(int id, String login, String password, String newLogin) throws ServiceException;

    boolean setPassword(int id, String login, String password, String newPassword) throws ServiceException;

    boolean setOrganisation(int id, int organisation) throws ServiceException;

    int getOrganisation(int userId) throws ServiceException;

    User[] getUsers() throws ServiceException;

    User[] getUsers(int organisation) throws ServiceException;

    User getUser(int id) throws ServiceException;

    boolean setInfo(int id, String info) throws ServiceException;

    boolean setName(int id, String name) throws ServiceException;

    boolean setSurname(int id, String surname) throws ServiceException;

}
