package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetLocale implements Command {

    private static final Logger logger = LogManager.getLogger(SetLocale.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, by.epam.orders.service.exceptions.ServiceException {

        logger.info("inside execute");

        String locale=RequestParser.getSecondCommandFromURI(request);

        logger.info("uri: " + locale);

        request.getSession().setAttribute("locale", locale);
        response.sendRedirect(request.getContextPath() + "/");

    }
}
