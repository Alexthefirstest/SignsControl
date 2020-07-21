package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUser implements Command {

    private static final Logger logger = LogManager.getLogger(AddUser.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");
        //qq

        request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations());
        request.setAttribute("roles", ServiceFactory.getINSTANCE().getRolesControllerService().getUsersRoles());


        request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/add_user.jsp").forward(request, response);
    }
}
