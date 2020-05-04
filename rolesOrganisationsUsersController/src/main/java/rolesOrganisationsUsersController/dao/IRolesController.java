package rolesOrganisationsUsersController.dao;

import rolesOrganisationsUsersController.bean.Role;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;

public interface IRolesController {

    Role addRole(String name) throws DAOException;

    boolean deleteRole(int id) throws DAOException;

    Role[] getRoles() throws DAOException;

    boolean setName(int id, String name) throws DAOException;

}
