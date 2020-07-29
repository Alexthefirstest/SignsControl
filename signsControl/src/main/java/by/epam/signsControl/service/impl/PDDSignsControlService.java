package by.epam.signsControl.service.impl;

import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.dao.IPDDSignsControl;
import by.epam.signsControl.dao.exceptions.DAOException;
import by.epam.signsControl.dao.exceptions.DAOValidationException;
import by.epam.signsControl.dao.factory.DaoFactory;
import by.epam.signsControl.service.IPDDSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;

import java.io.InputStream;

/**
 * class for communicate with dao and validate input
 */
public class PDDSignsControlService implements IPDDSignsControlService {

    /**
     * {@link IPDDSignsControl} instance
     */
    private final IPDDSignsControl pddSignsControl = DaoFactory.getINSTANCE().getPddSignsControl();

    /**
     * add sign to table
     *
     * @param section     sign section
     * @param number      sign number
     * @param kind        sign kind
     * @param name        sign name
     * @param description sign description
     * @return object if success or null if not
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public Sign addSign(int section, int number, int kind, String name, String description) throws ServiceException {

        InputValidation.nullCheck(name);
        InputValidation.nullCheck(description);

        try {

            return pddSignsControl.addSign(section, number, kind, name, description);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param kind    sign kind
     * @param name    sign name
     * @return object if success or null if not
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public Sign addSign(int section, int number, int kind, String name) throws ServiceException {

        InputValidation.nullCheck(name);

        try {

            return pddSignsControl.addSign(section, number, kind, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * add sign to table
     *
     * @param section sign section
     * @param number  sign number
     * @param name    sign name
     * @return object if success or null if not
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public Sign addSign(int section, int number, String name) throws ServiceException {

        InputValidation.nullCheck(name);

        try {

            return pddSignsControl.addSign(section, number, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * remove sign from table
     *
     * @param id sign id to remove
     * @return null if success
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean removeSign(int id) throws ServiceException {
        try {

            return pddSignsControl.removeSign(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id      sign id where set
     * @param section section to set
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean updateSection(int id, int section) throws ServiceException {
        try {

            return pddSignsControl.updateSection(id, section);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id     sign id where set
     * @param number of sign in section to set
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean updateNumber(int id, int number) throws ServiceException {
        try {

            return pddSignsControl.updateNumber(id, number);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id   sign id where set
     * @param kind to set
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean updateKind(int id, int kind) throws ServiceException {
        try {

            return pddSignsControl.updateKind(id, kind);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id          sign id where set
     * @param inputStream of picture to set
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean setPicture(int id, InputStream inputStream, long imageSize) throws ServiceException {

        InputValidation.nullCheck(inputStream);

        try {

            return pddSignsControl.setPicture(id, inputStream, imageSize);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id sign id where get
     * @return picture of sign if success or null
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public byte[] getPicture(int id) throws ServiceException {
        try {

            return pddSignsControl.getPicture(id);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id   sign id where set
     * @param name to set
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean updateName(int id, String name) throws ServiceException {

        InputValidation.nullCheck(name);

        try {

            return pddSignsControl.updateName(id, name);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * @param id   sign id where set
     * @param info to set
     * @return true if success or false in other case
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     *                                    data invalid {@link InputValidation}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public boolean updateDescription(int id, String info) throws ServiceException {

        InputValidation.nullCheck(info);

        try {

            return pddSignsControl.updateDescription(id, info);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get all signs
     *
     * @return {@link Sign} array
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public Sign[] getPddSigns() throws ServiceException {
        try {

            return pddSignsControl.getPddSigns();
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @return {@link Sign} array
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public Sign[] getPddSigns(int section) throws ServiceException {
        try {

            return pddSignsControl.getPddSigns(section);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    /**
     * get signs with section number
     *
     * @param section to find signs
     * @param number  to find signs
     * @return {@link Sign} array
     * @throws ServiceValidationException when {@link IPDDSignsControl} throw {@link DAOValidationException}
     * @throws ServiceException           ex when {@link IPDDSignsControl} throw {@link DAOException}
     */
    @Override
    public Sign[] getPddSigns(int section, int number) throws ServiceException {
        try {

            return pddSignsControl.getPddSigns(section, number);
        } catch (DAOValidationException ex) {
            throw new ServiceValidationException(ex.getMessage());
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
