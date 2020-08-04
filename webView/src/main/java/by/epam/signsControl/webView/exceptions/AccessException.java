package by.epam.signsControl.webView.exceptions;

/**
 * CommandController layer exception for wrong access rules
 */
public class AccessException extends CommandControllerException {


    private static final long serialVersionUID = 2799191527340101829L;

    /**
     * call super(message)
     *
     * @param message exception message
     */
    public AccessException(String message) {
        super(message);
    }

    /**
     * call super(message, cause)
     *
     * @param message exception message
     * @param cause   message cause
     */
    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * call super( cause)
     *
     * @param cause exception cause
     */
    public AccessException(Throwable cause) {
        super(cause);
    }


}
