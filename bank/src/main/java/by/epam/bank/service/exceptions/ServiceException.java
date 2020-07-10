package by.epam.bank.service.exceptions;

/**
 * Service layer exception
 *
 * @author Bulgak Alexander
 * @see Exception
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
     * @param cause {@link Throwable}
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }


}
