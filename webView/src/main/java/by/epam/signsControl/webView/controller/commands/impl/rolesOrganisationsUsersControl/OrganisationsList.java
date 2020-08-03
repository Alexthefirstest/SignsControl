package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
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

public class OrganisationsList implements Command {

    private static final Logger logger = LogManager.getLogger(OrganisationsList.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        try {


            AccessRulesChecker.notAnonymCheck(request);

            IOrganisationsControllerService organisationsControllerService = ServiceFactory.getINSTANCE().getOrganisationsControllerService();

            Organisation[] organisations;

            String role = request.getParameter("role");

            if (role == null) {
                organisations = organisationsControllerService.getOrganisations();
            } else {
                logger.info(role);
                organisations = organisationsControllerService.getOrganisations(Integer.parseInt(role));
            }

            request.setAttribute("organisations", organisations);
            request.setAttribute("roles", ServiceFactory.getINSTANCE().getRolesControllerService().getOrganisationsRoles());

            logger.info("inside execute");
            //qq

            request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/organisations_list.jsp").forward(request, response);
        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}