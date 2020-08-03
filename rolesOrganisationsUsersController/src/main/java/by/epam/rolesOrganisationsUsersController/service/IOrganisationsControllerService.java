package by.epam.rolesOrganisationsUsersController.service;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;

/**
 * Use {@link by.epam.rolesOrganisationsUsersController.dao.IOrganisationsController} for working with dao
 * include parameters validation
 *
 * @author Bulgak Alexander
 * @see by.epam.rolesOrganisationsUsersController.bean.Organisation
 * @see by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException
 */
public interface IOrganisationsControllerService {

    /**
     * add organisation to jdbc table
     *
     * @param name organisation name
     * @param role organisation members role
     * @return {@link Organisation} if successfully added
     * @throws ServiceException when get an exception during adding
     */
    Organisation addOrganisation(String name, int role) throws ServiceException;

    /**
     * set name for jdbc organisation
     *
     * @param id   organisation id in jdbc
     * @param name new organisation name in jdbc
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setName(int id, String name) throws ServiceException;

    /**
     * set organisation members role
     *
     * @param id   organisation id in jdbc
     * @param role new organisation members role
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setRole(int id, int role) throws ServiceException;

//    /**
//     * @param id organisation id in jdbc
//     * @return organisation member role
//     * @throws ServiceException when get an exception during execution
//     */
//    int getRole(int id) throws ServiceException;


    /**
     * set info in jdbc organisation info field
     *
     * @param id   organisation id in jdbc
     * @param info new organisation info in jdbc
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setInfo(int id, String info) throws ServiceException;

    /**
     * @param id organisation id in jdbc
     * @return organisation info in jdbc
     * @throws ServiceException when get an exception during execution
     */
    String getInfo(int id) throws ServiceException;


    /**
     * @param id organisation id in jdbc
     * @return block condition
     * @throws ServiceException when get an exception during execution
     */
    boolean getBlock(int id) throws ServiceException;

    /**
     * set block in organisation table
     *
     * @param id    organisation id in jdbc
     * @param block new block condition
     * @return true if success, false - if no
     * @throws ServiceException when get an exception during execution
     */
    boolean setBlock(int id, boolean block) throws ServiceException;

    /**
     * @return organisations array
     * @throws ServiceException when get an exception during execution
     */
    Organisation[] getOrganisations() throws ServiceException;

    /**
     * @param id to find
     * @return organisation like first element of array with id or empty array
     * @throws ServiceException when get an exception during execution
     */
    Organisation[] getOrganisation(int id) throws ServiceException;

    /**
     * @param id to not show
     * @return organisations array without organisation with id param
     * @throws ServiceException when get an exception during execution
     */
    Organisation[] getOrganisationsBeside(int id) throws ServiceException;

    /**
     * @param roleID - id of role in jdbc
     * @return organisations array
     * @throws ServiceException when get an exception during execution
     */
    Organisation[] getOrganisations(int roleID) throws ServiceException;

    /**
     * @param roleID - id of role in jdbc
     * @return unblocked organisations array
     * @throws ServiceException when get an exception during execution
     */
    Organisation[] getUnblockedOrganisations(int roleID) throws ServiceException;

}
