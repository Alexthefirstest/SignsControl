package by.epam.signsControl.service.exceptions;

/**
 * exception for dad layer
 * <p>
 * extend {@link ServiceException}
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
