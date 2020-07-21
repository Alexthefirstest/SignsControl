package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLoginPasswordFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeLoginPasswordFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");
        //qq

        IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();

        int id = (Integer) request.getSession().getAttribute(LoginFormHandler.USER_ID);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String action = RequestParser.getSecondCommandFromURI(request);

        switch (action) {

            case "login":
                String newLogin = request.getParameter("newLogin");
                if (!newLogin.isEmpty()) {
                    usersControllerService.setLogin(id, login, password, newLogin);
                }
                break;

            case "password":
                String newPassword = request.getParameter("newPassword");
                if (!newPassword.isEmpty()) {
                    usersControllerService.setPassword(id, login, password, newPassword);
                }
        }

        response.sendRedirect(request.getContextPath() + "/user_profile");
    }
}
