package by.epam.signsControl.service.impl;

import by.epam.signsControl.service.exceptions.ServiceValidationException;

public class StringTransformer {

    private StringTransformer() {
    }

    public static String coordinatesToPointWithCheck(String firstCoordinate, String secondCoordinate) throws ServiceValidationException {

        InputValidation.nullCheck(firstCoordinate);
        InputValidation.nullCheck(secondCoordinate);
        InputValidation.coordinatesValidation(firstCoordinate);
        InputValidation.coordinatesValidation(secondCoordinate);
        return "Point("+firstCoordinate+' '+secondCoordinate+')';
    }
}
