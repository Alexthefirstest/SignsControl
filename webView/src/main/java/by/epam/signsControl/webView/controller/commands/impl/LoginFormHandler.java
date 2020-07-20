package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(LoginFormHandler.class);

    public static final String USER_ID = "userID";
    public static final String USER_ROLE = "role";
    public static final String USERNAME ="username" ;
    public static final String ORGANISATION_ID = "organisationID";
    public static final String ORGANISATION_ROLE = "organisationRole";

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


            session.setAttribute(LoginFormHandler.USER_ID, user.getId());
            session.setAttribute(LoginFormHandler.USER_ROLE, user.getRole().getId());
            session.setAttribute(LoginFormHandler.USERNAME, user.getLogin());
            session.setAttribute(LoginFormHandler.ORGANISATION_ID, user.getOrganisation().getId());
            session.setAttribute(LoginFormHandler.ORGANISATION_ROLE, user.getOrganisation().getRole().getId());


            logger.info(user.getId());
            logger.info(user.getRole().getId());
            logger.info(user.getLogin());
            logger.info(user.getOrganisation().getId());
            logger.info(user.getOrganisation().getRole().getId());

            response.sendRedirect(request.getContextPath() + "/");
        }

    }
}
