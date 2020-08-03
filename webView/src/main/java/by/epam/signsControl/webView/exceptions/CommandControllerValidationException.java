package by.epam.signsControl.webView.exceptions;

/**
 * exception for view layer
 *
 * @see Exception
 */
public class CommandControllerValidationException extends CommandControllerException {

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public CommandControllerValidationException(String message) {
        super(message);
    }

    /**
     * call super(message, cause)
     *
     * @param message exception message
     * @param cause   message cause
     */
    public CommandControllerValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * call super( cause)
     *
     * @param cause exception cause
     */
    public CommandControllerValidationException(Throwable cause) {
        super(cause);
    }

}
