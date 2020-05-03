package rolesOrganisationsUsersController.service.exceptions;

public class ServiceValidationException extends ServiceException {

    public ServiceValidationException() {
    }

    public ServiceValidationException(String message) {
        super(message);
    }

}
