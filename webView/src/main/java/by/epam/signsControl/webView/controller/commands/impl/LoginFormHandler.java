package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * login form jsp handler
 * check user existing check and set attribute  special attributes to the session
 * return to the form with attention if wrong login or password
 */
public class LoginFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(LoginFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        try {

            User user = ServiceFactory.getINSTANCE().getUsersControllerService()
                    .checkLoginPassword(request.getParameter("login"), request.getParameter("password"));


            HttpSession session = request.getSession();

            if (user == null) {
                logger.warn("USER IF: " + user);
                session.setAttribute("second_form", true);
                response.sendRedirect(request.getContextPath() + "/login");
            } else {

                if(user.isBlock()){
                    session.setAttribute("second_form_block", true);
                    response.sendRedirect(request.getContextPath() + "/login");
                }

                session.setAttribute(Constants.USER_ID, user.getId());
                session.setAttribute(Constants.USER_ROLE, user.getRole().getId());
                session.setAttribute(Constants.LOGIN, user.getLogin());
                session.setAttribute(Constants.ORGANISATION_ID, user.getOrganisation().getId());
                session.setAttribute(Constants.ORGANISATION_ROLE, user.getOrganisation().getRole().getId());

                response.sendRedirect(request.getContextPath() + "/");
            }


        } catch (ServiceValidationException ex) {
            throw new CommandControllerValidationException(ex);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

    }
}
