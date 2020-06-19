package rolesOrganisationsUsersController.service;

import rolesOrganisationsUsersController.bean.Role;
import rolesOrganisationsUsersController.service.exceptions.ServiceException;

public interface IRolesControllerService {

    Role addRole(String name) throws ServiceException;

    boolean deleteRole(int id) throws ServiceException;

    Role[] getRoles() throws ServiceException; //видимо единственный нужный

    boolean setName(int id, String name) throws ServiceException;

}
