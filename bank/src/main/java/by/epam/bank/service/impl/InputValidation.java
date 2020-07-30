package by.epam.bank.service.impl;

import by.epam.bank.service.exceptions.ServiceValidationException;

import java.util.regex.Pattern;

/**
 * static class for validate input parameters
 *
 * @author Bulgak Alexander
 */
public class InputValidation {

    /*
     *empty constructor
     */
    private InputValidation() {

    }

    /**
     * date validate format yyyy or yyyy.mm or yyyy.mm.dd
     *
     * @param string date
     * @throws ServiceValidationException when invalid
     */
    public static void dateCheck(String string) throws ServiceValidationException {

        if (!Pattern.matches("\\d{4}(\\.\\d{2}(\\.\\d{2})?)?", string)) {
            throw new ServiceValidationException("wrong date format, use yyyy or yyyy.mm or yyyy.mm.dd - "+string);
        }
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

    /**
     * @param string input string
     * @throws ServiceValidationException if string ==null
     */
    public static void nullCheck(String string) throws ServiceValidationException {

        if (string == null) {
            throw new ServiceValidationException("variable can't be null");
        }
    }

    /**
     * use pattern "\\w+" (latin letters, digits and '_') to validate string
     *
     * @param string input string
     * @throws ServiceValidationException if string do not match pattern
     */
    public static void validateString(String string) throws ServiceValidationException {

        if (!Pattern.matches("\\w+", string)) {
            throw new ServiceValidationException("only Latin letters and '_' can be used");
        }

    }



    /**
     * call {@link InputValidation#nullCheck(String)} and {@link InputValidation#validateString(String)}
     *
     * @param string input string
     * @throws ServiceValidationException if {@link InputValidation#nullCheck(String)} or
     *                                    {@link InputValidation#validateString(String)} throw it
     */
    public static void nullAndSymbolsCheck(String string) throws ServiceValidationException {
        nullCheck(string);
        validateString(string);

    }


}
