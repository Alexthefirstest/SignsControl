package by.epam.signsControl.webView.controller.commands.impl.signsControl;

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

/**
 * get info for change sign in json format
 */
public class GetSignsChangeInfo implements Command {


    private static final Logger logger = LogManager.getLogger(GetSignsChangeInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        response.setContentType("application/json");


        try {

            String responseJSON =//"{\"signs\":" +
                    ResponseCreator.createJSON(
                            (ServiceFactory.getINSTANCE().getLocalSignsControlService().getSigns(request.getParameter("pointCoordinates"))));// +"}";


            logger.info(responseJSON);

            response.getWriter().write(responseJSON);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
