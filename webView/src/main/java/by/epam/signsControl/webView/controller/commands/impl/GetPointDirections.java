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

public class GetPointDirections implements Command {


    private static final Logger logger = LogManager.getLogger(GetPointDirections.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {

            response.getWriter().write(ResponseCreator.createDirectionsJSON
                    (ServiceFactory.getINSTANCE().getDirectionsControlService().getPointDirections(request.getParameter("pointCoordinates"))));

        } catch (ServiceException e) {
            logger.warn(e);
        }
    }
}