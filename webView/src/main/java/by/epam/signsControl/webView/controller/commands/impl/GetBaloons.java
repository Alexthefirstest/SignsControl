package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetBaloons implements Command {

    private static final Logger logger = LogManager.getLogger(GetBaloons.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        logger.info("inside execute");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = "[{\"balloonContent\": \"Baloon0\"}]"; //после } запятая, если больше 1
        logger.warn(json);
        response.getWriter().write(json);

    }
}
