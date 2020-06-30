package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEmptyPoints implements Command {
    private static final Logger logger = LogManager.getLogger(GetEmptyPoints.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            MapPoint[] mapPoints = ServiceFactory.getINSTANCE().getMapPointsControlService().getEmptyMapPoints();
            response.getWriter().write(ResponseCreator.createPointsJSON(mapPoints));
        } catch (ServiceException e) {
            logger.warn(e);
        }

    }
}
