package by.epam.rolesOrganisationsUsersController.dao.exceptions;

/**
 * Dao layer exception
 *
 * @author Bulgak Alexander
 * @see DAOException
 */
public class DAOValidationException extends DAOException {

    /**
     * empty constructor
     */
    public DAOValidationException() {
    }

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public DAOValidationException(String message) {
        super(message);
    }

    /**
     * call super(message, cause)
     *
     * @param message exception message
     * @param cause   {@link Throwable}
     */
    public DAOValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
