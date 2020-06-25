package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetPointsByDate implements Command {

    private static final Logger logger = LogManager.getLogger(GetPointsByDate.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        logger.info(request.getParameter("chosenDate"));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            MapPoint$LocalSign[] mapPoint$LocalSign = ServiceFactory.getINSTANCE().getLocalSignsControlService().
                    getMapPoints$LocalSignsByDate((request.getParameter("chosenDate")).replaceAll("-", "."));
            response.getWriter().write(ResponseCreator.createPointsJSON(mapPoint$LocalSign, false));
        } catch (ServiceException e) {
            logger.warn(e);
        }

    }
}
