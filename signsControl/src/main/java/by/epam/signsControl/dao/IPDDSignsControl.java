package by.epam.signsControl.dao;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.exceptions.DAOException;

import java.io.InputStream;

/**
 * Interface for jdbc direction fields
 */
public interface IPDDSignsControl {


    /**
     * add sign to table
     *
     * @param section     sign section
     * @param number      sign number
     * @param kind        sign kind
     * @param name        sign name
     * @param description sign description
     * @return object if success or null if not
     * @throws DAOException when get an exception during execution
     */
    Sign addSign(int section, int number, int kind, String name, String description) throws DAOException;

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param kind    sign kind
     * @param name    sign name
     * @return object if success or null if not
     * @throws DAOException when get an exception during execution
     */
    Sign addSign(int section, int number, int kind, String name) throws DAOException;

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param name    sign name
     * @return object if success or null if not
     * @throws DAOException when get an exception during execution
     */
    Sign addSign(int section, int number, String name) throws DAOException;

    /**
     * remove sign from table
     *
     * @param id sign id to remove
     * @return null if success
     * @throws DAOException when get an exception during execution
     */
    boolean removeSign(int id) throws DAOException;

    /**
     * @param id      sign id where set
     * @param section section to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean updateSection(int id, int section) throws DAOException;

    /**
     * @param id     sign id where set
     * @param number of sign in section to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean updateNumber(int id, int number) throws DAOException;

    /**
     * @param id   sign id where set
     * @param kind to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean updateKind(int id, int kind) throws DAOException;

    /**
     * @param id   sign id where set
     * @param name to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean updateName(int id, String name) throws DAOException;

    /**
     * @param id   sign id where set
     * @param info to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean updateDescription(int id, String info) throws DAOException;


    /**
     * @param id          sign id where set
     * @param inputStream of picture to set
     * @return true if success or false in other case
     * @throws DAOException when get an exception during execution
     */
    boolean setPicture(int id, InputStream inputStream, long imageSize) throws DAOException;

    /**
     * @param id sign id where get
     * @return picture of sign if success or null
     * @throws DAOException when get an exception during execution
     */
    byte[] getPicture(int id) throws DAOException;

    /**
     * get all signs
     *
     * @return {@link Sign} array
     * @throws DAOException when get an exception during execution
     */
    Sign[] getPddSigns() throws DAOException;

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @return {@link Sign} array
     * @throws DAOException when get an exception during execution
     */
    Sign[] getPddSigns(int section) throws DAOException;

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @param number  to find signs
     * @return {@link Sign} array
     * @throws DAOException when get an exception during execution
     */
    Sign[] getPddSigns(int section, int number) throws DAOException;

}
