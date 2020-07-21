package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(AddUserFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");
        //qq

        IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));
        int organisation = Integer.parseInt(request.getParameter("organisation"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String info = request.getParameter("info");

        User user = usersControllerService.addUser(login, password, role, organisation, name, surname);

        if (!info.isEmpty()) {
            usersControllerService.setInfo(user.getId(), info);
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }
}
