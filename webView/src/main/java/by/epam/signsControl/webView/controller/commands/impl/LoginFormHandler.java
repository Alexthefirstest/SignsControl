package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.filters.URLFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(LoginFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");


        User user = ServiceFactory.getINSTANCE().getUsersControllerService()
                .checkLoginPassword(request.getParameter("login"), request.getParameter("password"));

        logger.warn("USER: " + user);

        HttpSession session = request.getSession();

        if (user == null) {
            logger.warn("USER IF: " + user);
            session.setAttribute("second_form", true);
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            logger.warn("USER ELSE: " + user);


            session.setAttribute("role", user.getRole());
            session.setAttribute("organisation", user.getOrganisationID());
            session.setAttribute("userID", user.getId());
            session.setAttribute("username", user.getLogin());


            response.sendRedirect(request.getContextPath() + "/");
        }

    }
}
