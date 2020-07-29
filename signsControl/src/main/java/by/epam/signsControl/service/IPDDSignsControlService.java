package by.epam.signsControl.service;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.service.exceptions.ServiceException;

import java.io.InputStream;

public interface IPDDSignsControlService {

    /**
     * add sign to table
     *
     * @param section     sign section
     * @param number      sign number
     * @param kind        sign kind
     * @param name        sign name
     * @param description sign description
     * @return object if success or null if not
     * @throws ServiceException when get an exception during execution
     */
    Sign addSign(int section, int number, int kind, String name, String description) throws ServiceException;

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param kind    sign kind
     * @param name    sign name
     * @return object if success or null if not
     * @throws ServiceException when get an exception during execution
     */
    Sign addSign(int section, int number, int kind, String name) throws ServiceException;

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param name    sign name
     * @return object if success or null if not
     * @throws ServiceException when get an exception during execution
     */
    Sign addSign(int section, int number, String name) throws ServiceException;

    /**
     * remove sign from table
     *
     * @param id sign id to remove
     * @return null if success
     * @throws ServiceException when get an exception during execution
     */
    boolean removeSign(int id) throws ServiceException;

    /**
     * @param id      sign id where set
     * @param section section to set
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean updateSection(int id, int section) throws ServiceException;

    /**
     * @param id     sign id where set
     * @param number of sign in section to set
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean updateNumber(int id, int number) throws ServiceException;

    /**
     * @param id   sign id where set
     * @param kind to set
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean updateKind(int id, int kind) throws ServiceException;

    /**
     * @param id          sign id where set
     * @param inputStream of picture to set
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean setPicture(int id, InputStream inputStream, long imageSize) throws ServiceException;

    /**
     * @param id sign id where get
     * @return picture of sign if success or null
     * @throws ServiceException when get an exception during execution
     */
    byte[] getPicture(int id) throws ServiceException;

    /**
     * @param id   sign id where set
     * @param name to set
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean updateName(int id, String name) throws ServiceException;

    /**
     * @param id   sign id where set
     * @param info to set
     * @return true if success or false in other case
     * @throws ServiceException when get an exception during execution
     */
    boolean updateDescription(int id, String info) throws ServiceException;

    /**
     * get all signs
     *
     * @return {@link Sign} array
     * @throws ServiceException when get an exception during execution
     */
    Sign[] getPddSigns() throws ServiceException;

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @return {@link Sign} array
     * @throws ServiceException when get an exception during execution
     */
    Sign[] getPddSigns(int section) throws ServiceException;

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @param number  to find signs
     * @return {@link Sign} array
     * @throws ServiceException when get an exception during execution
     */
    Sign[] getPddSigns(int section, int number) throws ServiceException;

}
