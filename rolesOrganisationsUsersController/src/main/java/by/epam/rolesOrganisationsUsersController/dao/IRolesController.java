package by.epam.rolesOrganisationsUsersController.dao;

import by.epam.rolesOrganisationsUsersController.bean.Role;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;

public interface IRolesController {

    Role addRole(String name) throws DAOException;

    boolean deleteRole(int id) throws DAOException;

    Role[] getRoles() throws DAOException;

    boolean setName(int id, String name) throws DAOException;

}
