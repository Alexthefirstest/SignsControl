package rolesOrganisationsUsersController.service;

import rolesOrganisationsUsersController.bean.Organisation;
import rolesOrganisationsUsersController.service.exceptions.ServiceException;

public interface IOrganisationsControllerService {


    Organisation addOrganisation(String name, int role) throws ServiceException;

    boolean setName(int id, String name) throws ServiceException;

    boolean setRole(int id, int role) throws ServiceException;

    int getRole(int id) throws ServiceException;

    boolean setInfo(int id, String info) throws ServiceException;

    String getInfo(int id) throws ServiceException;

    boolean getBlock(int id) throws ServiceException;

    boolean setBlock(int id, boolean block) throws ServiceException;

    Organisation[] getOrganisations() throws ServiceException;


}
