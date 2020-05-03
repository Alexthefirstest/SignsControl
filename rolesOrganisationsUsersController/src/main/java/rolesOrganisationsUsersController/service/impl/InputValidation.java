package rolesOrganisationsUsersController.service.impl;

import rolesOrganisationsUsersController.service.exceptions.ServiceException;
import rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;

import java.util.regex.Pattern;

public class InputValidation {

    private InputValidation() {

    }

    public static void nullCheck(String string) throws ServiceValidationException {

        if (string == null) {
            throw new ServiceValidationException("variable can't be null");
        }
    }

    public static void validateString(String string) throws ServiceValidationException {

        if (!Pattern.matches("\\w+", string)) {
            throw new ServiceValidationException("only Latin letters and '_' can be used");
        }

    }

    public static void validateStringRus(String string) throws ServiceValidationException {

        if (!Pattern.matches("[\\wА-Яа-я]+", string)) {
            throw new ServiceValidationException("only Latin letters, Cyrillic letters and '_' can be used");
        }

    }

    public static void validateStringRusForText(String string) throws ServiceValidationException {

        if (!Pattern.matches("[\\wА-Яа-я\\s:!.,)(-?\\d]+", string)) {
            throw new ServiceValidationException("only Latin letters, Cyrillic letters and '_' can be used");
        }

    }

    public static void nullAndSymbolsCheck(String string) throws ServiceValidationException {
        nullCheck(string);
        validateString(string);

    }

    public static void nullAndSymbolsCheckWithRus(String string) throws ServiceValidationException {
        nullCheck(string);
        validateStringRus(string);
    }

}
