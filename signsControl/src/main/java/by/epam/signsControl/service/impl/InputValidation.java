package by.epam.signsControl.service.impl;

import by.epam.signsControl.service.exceptions.ServiceValidationException;

import java.util.regex.Pattern;

public class InputValidation {

    private InputValidation() {

    }

    public static void coordinatesValidation(String coordinate) throws ServiceValidationException {

        nullCheck(coordinate);

        if (!Pattern.matches("\\d+(\\.\\d+)?", coordinate)) {
            throw new ServiceValidationException("wrong coordinate format");
        }
    }

    public static void pointCheck(String coordinate) throws ServiceValidationException {

        nullCheck(coordinate);

        if (!Pattern.matches("POINT\\(\\d+(\\.\\d*)?\\s\\d+(\\.\\d*)?\\)", coordinate)) {
            throw new ServiceValidationException("wrong coordinate point format");
        }
    }

    public static void dateCheck(String string) throws ServiceValidationException {

        if (!Pattern.matches("\\d{4}(\\.\\d{2}(\\.\\d{2})?)?", string)) {
            throw new ServiceValidationException("wrong date format, use yyyy or yyyy.mm or yyyy.mm.dd");
        }
    }

    public static void nullCheck(Object o) throws ServiceValidationException {

        if (o == null) {
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

    public static void nullAndDateCheck(String date) throws ServiceValidationException {
        nullCheck(date);
        dateCheck(date);

    }

    public static void nullAndSymbolsCheckWithRus(String string) throws ServiceValidationException {
        nullCheck(string);
        validateStringRus(string);
    }

}
