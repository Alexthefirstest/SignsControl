package by.epam.signsControl.webView.exceptions;

/**
 * exception for view layer
 *
 * @see Exception
 */
public class CommandControllerException extends Exception {

    /**
     * empty constructor
     */
    public CommandControllerException() {
    }

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public CommandControllerException(String message) {
        super(message);
    }

    /**
     * call super(message, cause)
     *
     * @param message exception message
     * @param cause   message cause
     */
    public CommandControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * call super( cause)
     *
     * @param cause exception cause
     */
    public CommandControllerException(Throwable cause) {
        super(cause);
    }

}
