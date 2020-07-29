package by.epam.signsControl.service.impl;

import by.epam.signsControl.service.exceptions.ServiceValidationException;

/**
 * string transformer class
 */
public class StringTransformer {

    private StringTransformer() {
    }

    /**
     * transform coordinates to mysql point format
     *
     * @param coordinates coordinates
     * @return mysql point format string
     * @throws ServiceValidationException if null or date invalid {@link InputValidation#coordinatesValidation(String)}
     */
    public static String coordinatesToPointWithCheck(String coordinates) throws ServiceValidationException {

        InputValidation.nullCheck(coordinates);

        InputValidation.coordinatesValidation(coordinates);

        return "Point(" + coordinates.replace(",", " ") + ')';
    }

    /**
     * coordinates or mysql point format to mysql point format
     *
     * @param coordinates coordinates
     * @return point format
     * @throws ServiceValidationException if coordinates not invalid with {@link InputValidation#pointCheck(String)} or {@link InputValidation#coordinatesValidation(String)}
     */
    public static String coordinatesOrPointToPointWithCheck(String coordinates) throws ServiceValidationException {

        InputValidation.nullCheck(coordinates);

        try {
            InputValidation.pointCheck(coordinates);
            return coordinates;
        } catch (ServiceValidationException ex) {

            InputValidation.coordinatesValidation(coordinates);
            return "Point(" + coordinates.replace(",", " ") + ')';
        }


    }

    /**
     * dash to null
     *
     * @param text text
     * @return '-' if text is null
     */
    public static String dashFromNull(String text) {


        return text == null ? "-" : text;
    }

}
