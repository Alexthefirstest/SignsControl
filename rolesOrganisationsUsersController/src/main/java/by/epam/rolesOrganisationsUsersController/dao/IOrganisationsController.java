package by.epam.rolesOrganisationsUsersController.dao;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.dao.exceptions.DAOException;

/**
 * JDBC organisation table redactor
 *
 * @author Bulgak Alexander
 * @see Organisation
 * @see DAOException
 */
public interface IOrganisationsController {

    /**
     * add organisation to jdbc table
     *
     * @param name organisation name
     * @param role organisation members role
     * @return {@link Organisation} if successfully added
     * @throws DAOException when get an exception during adding
     */
    Organisation addOrganisation(String name, int role) throws DAOException;

    /**
     * set name for jdbc organisation
     *
     * @param id   organisation id in jdbc
     * @param name new organisation name in jdbc
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setName(int id, String name) throws DAOException;

    /**
     * set organisation members role
     *
     * @param id   organisation id in jdbc
     * @param role new organisation members role
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setRole(int id, int role) throws DAOException;

//    /**
//     * @param id organisation id in jdbc
//     * @return organisation member role
//     * @throws DAOException when get an exception during execution
//     */
//    int getRole(int id) throws DAOException;

    /**
     * set info in jdbc organisation info field
     *
     * @param id   organisation id in jdbc
     * @param info new organisation info in jdbc
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setInfo(int id, String info) throws DAOException;

    /**
     * @param id organisation id in jdbc
     * @return organisation info in jdbc
     * @throws DAOException when get an exception during execution
     */
    String getInfo(int id) throws DAOException;

    /**
     * @param id organisation id in jdbc
     * @return block condition
     * @throws DAOException when get an exception during execution
     */
    boolean getBlock(int id) throws DAOException;

    /**
     * set block in organisation table
     *
     * @param id    organisation id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws DAOException when get an exception during execution
     */
    boolean setBlock(int id, boolean block) throws DAOException;

    /**
     * @return organisations array
     * @throws DAOException when get an exception during execution
     */
    Organisation[] getOrganisations() throws DAOException;

    /**
     * @param roleID - id of role in jdbc
     * @return organisations array
     * @throws DAOException when get an exception during execution
     */
    Organisation[] getOrganisations(int roleID) throws DAOException;

    /**
     * @param roleID - id of role in jdbc
     * @return unblocked organisations array
     * @throws DAOException when get an exception during execution
     */
    Organisation[] getUnblockedOrganisations(int roleID) throws DAOException;

}
