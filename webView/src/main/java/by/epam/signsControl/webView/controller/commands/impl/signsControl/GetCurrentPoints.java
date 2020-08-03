package by.epam.signsControl.webView.controller.commands.impl.signsControl;


import by.epam.signsControl.bean.MapPoint$LocalSign;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCurrentPoints implements Command {

    private static final Logger logger = LogManager.getLogger(GetCurrentPoints.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        response.setContentType("application/json");

        try {
            MapPoint$LocalSign[] mapPoint$LocalSign = ServiceFactory.getINSTANCE().getLocalSignsControlService().getAllMapPoints$LocalSigns();
            response.getWriter().write(ResponseCreator.createPointsJSON(mapPoint$LocalSign, true));
        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }

    }
}
