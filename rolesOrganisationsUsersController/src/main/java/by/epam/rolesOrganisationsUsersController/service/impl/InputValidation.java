package by.epam.rolesOrganisationsUsersController.service.impl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

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
     * use pattern "[\\wА-Яа-я]+" (latin and cyrillic letters, digits and '_') to validate string
     *
     * @param string input string
     * @throws ServiceValidationException if string do not match pattern
     */
    public static void validateStringRus(String string) throws ServiceValidationException {

        if (!Pattern.matches("[\\wА-Яа-я]+", string)) {
            throw new ServiceValidationException("only Latin letters, Cyrillic letters and '_' can be used");
        }

    }

    /**
     * use pattern "[\\wА-Яа-я\\s:!.,)(-?\\d\\n]+" (latin and cyrillic letters, digits, '_', space,
     * '!',':','!','.',')','(','-','?','paragraph') to validate string
     *
     * @param string input string
     * @throws ServiceValidationException if string do not match pattern
     */
    public static void validateStringRusForText(String string) throws ServiceValidationException {

        if (!Pattern.matches("[\\wА-Яа-я\\s:!.,)(-?\\d\\n]+", string)) {
            throw new ServiceValidationException("only Latin letters, Cyrillic letters and '_' can be used");
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

    /**
     * call {@link InputValidation#nullCheck(String)} and {@link InputValidation#validateStringRus(String)}
     *
     * @param string input string
     * @throws ServiceValidationException if {@link InputValidation#nullCheck(String)} or
     *                                    {@link InputValidation#validateStringRus(String)} throw it
     */
    public static void nullAndSymbolsCheckWithRus(String string) throws ServiceValidationException {
        nullCheck(string);
        validateStringRus(string);
    }

}
