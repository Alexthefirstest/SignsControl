package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.IRolesControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUser implements Command {

    private static final Logger logger = LogManager.getLogger(AddUser.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        try {

            IRolesControllerService rolesControllerService = ServiceFactory.getINSTANCE().getRolesControllerService();
            IOrganisationsControllerService organisationsControllerService = ServiceFactory.getINSTANCE().getOrganisationsControllerService();

            if (AccessRulesChecker.organisationRoleCheckBool(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE)) {

                if (AccessRulesChecker.userRoleCheckBool(request, Constants.ADMINISTRATOR_ROLE)) {

                    logger.info("if 1");

                    request.setAttribute("roles", rolesControllerService.getUsersRolesBeside(Constants.USER_ANONYM_ROLE));
                    request.setAttribute("organisations", organisationsControllerService.getOrganisations());

                } else {
                    logger.info("if 2");
                    request.setAttribute("roles", rolesControllerService.getUsersRole(Constants.ADMINISTRATOR_ROLE));
                    request.setAttribute("organisations", organisationsControllerService.getOrganisationsBeside(Constants.ADMINISTRATORS_ORGANISATION_ID));

                }


            } else {
                logger.info("if 3");

                AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

                request.setAttribute("roles", rolesControllerService.getUsersRolesBeside(Constants.USER_ANONYM_ROLE));
                request.setAttribute("organisations",
                        organisationsControllerService.getOrganisation((Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID)));


            }

            request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/add_user.jsp").forward(request, response);
        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
