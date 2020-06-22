package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.signsControl.bean.MapPoint;
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

public class GetCurrentPoints implements Command {

    private static final Logger logger = LogManager.getLogger(GetCurrentPoints.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            MapPoint$LocalSign[] mapPoint$LocalSign = ServiceFactory.getINSTANCE().getLocalSignsControlService().getAllMapPoints$LocalSigns();
            response.getWriter().write(JsonCreator.createPointsJSON(mapPoint$LocalSign));
        } catch (ServiceException e) {
            logger.warn(e);
        }

    }
}
