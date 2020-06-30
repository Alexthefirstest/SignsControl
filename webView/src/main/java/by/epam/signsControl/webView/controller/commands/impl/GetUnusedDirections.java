package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUnusedDirections implements Command {


    private static final Logger logger = LogManager.getLogger(GetUnusedDirections.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            String json = (ResponseCreator.createDirectionsJSON
                    (ServiceFactory.getINSTANCE().getDirectionsControlService().getUnusedDirections(request.getParameter("pointCoordinates"))));

            response.getWriter().write(json);

        } catch (ServiceException e) {
            logger.warn(e);
        }
    }
}