package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
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

public class UsersList implements Command {

    private static final Logger logger = LogManager.getLogger(UsersList.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {


        try {

            IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();
            User[] users;

            String orgId = request.getParameter("id");

            if (AccessRulesChecker.organisationRoleCheckBool(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE)) {

                if (orgId == null) {

                    users = usersControllerService.getUsers();

                } else {
                    users = usersControllerService.getUsers(Integer.parseInt(orgId));
                }
            } else {
                AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);
                users = usersControllerService.getUsers((Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID));
            }

            request.setAttribute("users", users);
            request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations());


            logger.info("inside execute");
            //qq

            request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/users_list.jsp").forward(request, response);
        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}


