package by.epam.orders.service.exceptions;

/**
 * Service layer exception
 *
 * @author Bulgak Alexander
 * @see by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory
 */
public class ServiceValidationException extends ServiceException {


    /**
     * empty constructor
     */
    public ServiceValidationException() {
    }

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public ServiceValidationException(String message) {
        super(message);
    }

}
