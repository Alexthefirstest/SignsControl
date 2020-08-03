package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.bean.Direction;
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

public class GetDirectionChangeInfo implements Command {


    private static final Logger logger = LogManager.getLogger(GetDirectionChangeInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        response.setContentType("text/plain");

        ServiceFactory sf = ServiceFactory.getINSTANCE();

        try {

            Direction[] oldDirections = sf.getDirectionsControlService().getPointDirectionsSignListID(request.getParameter("pointCoordinates"));
            Direction[] newDirections = sf.getDirectionsControlService().getUnusedDirections(request.getParameter("pointCoordinates"));

            String oldDirectionsJSON = ResponseCreator.createDirectionsJSON(oldDirections);
            String newDirectionsJSON = ResponseCreator.createDirectionsJSON(newDirections);

            String resultJSON = "{\"oldDir\":" + oldDirectionsJSON + ",\"newDir\":" + newDirectionsJSON + "}";

            logger.info(resultJSON);

            response.getWriter().write(resultJSON);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}