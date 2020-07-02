package by.epam.signsControl.service.impl;

import by.epam.signsControl.service.exceptions.ServiceValidationException;

public class StringTransformer {

    private StringTransformer() {
    }

    public static String coordinatesToPointWithCheck(String coordinates) throws ServiceValidationException {

        InputValidation.nullCheck(coordinates);

        InputValidation.coordinatesValidation(coordinates);

        return "Point(" + coordinates.replace(",", " ") + ')';
    }

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

    public static String dashFromNull(String text) throws ServiceValidationException {


        return text == null ? "-" : text;
    }

}
