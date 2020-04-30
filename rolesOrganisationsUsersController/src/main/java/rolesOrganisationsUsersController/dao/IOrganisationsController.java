package rolesOrganisationsUsersController.dao;

import rolesOrganisationsUsersController.bean.Organisation;
import rolesOrganisationsUsersController.dao.exceptions.DAOException;

public interface IOrganisationsController {

    Organisation addOrganisation(String name, int role) throws DAOException;

    boolean setName(int id, String name) throws DAOException;

    boolean setRole(int id, int role) throws DAOException;

    int getRole(int id) throws DAOException;

    boolean setInfo(int id, String info) throws DAOException;

    String getInfo(int id) throws DAOException;

    boolean getBlock(int id) throws DAOException;

    boolean setBlock(int id, boolean block) throws DAOException;

    Organisation[] getOrganisations() throws DAOException;

}
