package by.epam.signsControl.service.exceptions;

/**
 * exception for service layer
 */
public class ServiceException extends Exception {

    /**
     * empty constructor
     */
    public ServiceException() {
    }

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * call super(cause)
     *
     * @param cause exception message
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }


}
