package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.exceptions.AccessException;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProfile implements Command {

    private static final Logger logger = LogManager.getLogger(UserProfile.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute: ");


        try {

            String command=RequestParser.getSecondCommandFromURI(request);

            if (command==null){
                throw new CommandControllerValidationException("wrong user");
            }

            int id = Integer.parseInt(command);
            IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();

            User user = usersControllerService.getUser(id);

            if (id != (Integer) request.getSession().getAttribute(Constants.USER_ID)) {

                if (AccessRulesChecker.organisationRoleCheckBool(request, Constants.ADMINISTRATORS_ORGANISATION_ID)) {

                    if (user.getOrganisation().getId() != Constants.ADMINISTRATORS_ORGANISATION_ID
                            && user.getRole().getId() != Constants.ADMINISTRATOR_ROLE
                            || user.getOrganisation().getId() == Constants.ADMINISTRATORS_ORGANISATION_ID
                            && !AccessRulesChecker.userRoleCheckBool(request, Constants.ADMINISTRATOR_ROLE)) {

                        logger.warn("wrong action if");
                        throw new AccessException("can't show this user");
                    }

                } else {
                    AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);
                    if (user.getOrganisation().getId() != (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID)) {
                        throw new AccessException("wrong action else");
                    }
                }
            }
            request.setAttribute("user", user);
            request.setAttribute("roles", ServiceFactory.getINSTANCE().getRolesControllerService().getUsersRoles());
            request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations());


            //qq

            request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/user_profile.jsp").forward(request, response);
        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
