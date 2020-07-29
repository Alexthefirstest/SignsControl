package by.epam.signsControl.service.impl;

import by.epam.signsControl.service.exceptions.ServiceValidationException;
import org.apache.logging.log4j.LogManager;

import java.util.regex.Pattern;

/**
 * validation class
 */
public class InputValidation {

    /**
     * private constructor
     */
    private InputValidation() {

    }

    /**
     * validate coordinate
     *
     * @param coordinate coordinates
     * @throws ServiceValidationException when invalid
     */
    public static void coordinatesValidation(String coordinate) throws ServiceValidationException {

        nullCheck(coordinate);

        if (!Pattern.matches("\\d+(\\.\\d*)?,\\d+(\\.\\d*)?", coordinate)) {
            throw new ServiceValidationException("wrong coordinate format: " + coordinate);
        }
    }

    /**
     * validate coordinates on mysql pattern
     *
     * @param coordinate coordinates
     * @throws ServiceValidationException when invalid
     */
    public static void pointCheck(String coordinate) throws ServiceValidationException {

        nullCheck(coordinate);

        if (!Pattern.matches("POINT\\(\\d+(\\.\\d*)?\\s\\d+(\\.\\d*)?\\)", coordinate)) {
            throw new ServiceValidationException("wrong coordinate point format: " + coordinate);
        }
    }

    /**
     * date validate format yyyy or yyyy.mm or yyyy.mm.dd
     *
     * @param string date
     * @throws ServiceValidationException when invalid
     */
    public static void dateCheck(String string) throws ServiceValidationException {

        if (!Pattern.matches("\\d{4}(\\.\\d{2}(\\.\\d{2})?)?", string)) {
            throw new ServiceValidationException("wrong date format, use yyyy or yyyy.mm or yyyy.mm.dd - " + string);
        }
    }

    /**
     * null check
     *
     * @param o object
     * @throws ServiceValidationException if null
     */
    public static void nullCheck(Object o) throws ServiceValidationException {

        if (o == null) {
            throw new ServiceValidationException("variable can't be null");
        }
    }

    /**
     * latin letters and _ validation
     *
     * @param string string
     * @throws ServiceValidationException if invalid
     */
    public static void validateString(String string) throws ServiceValidationException {

        if (!Pattern.matches("\\w+", string)) {
            throw new ServiceValidationException("only Latin letters and '_' can be used");
        }

    }

    /**
     * not null and only latin symbols and _ validation
     *
     * @param string string
     * @throws ServiceValidationException if invalid
     */
    public static void nullAndSymbolsCheck(String string) throws ServiceValidationException {
        nullCheck(string);
        validateString(string);
    }

    /**
     * date validate format yyyy or yyyy.mm or yyyy.mm.dd and not null
     *
     * @param date date
     * @throws ServiceValidationException in invalid
     */
    public static void nullAndDateCheck(String date) throws ServiceValidationException {
        nullCheck(date);
        dateCheck(date);

    }


}
