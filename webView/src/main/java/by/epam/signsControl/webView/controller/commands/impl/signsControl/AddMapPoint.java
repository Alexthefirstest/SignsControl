package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * add map point to db
 */
public class AddMapPoint implements Command {

    private static final Logger logger = LogManager.getLogger(AddMapPoint.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);



        String coordinates = ((request.getParameter("coordinatesToSend")));
        String address = ((request.getParameter("address")));
        int direction = Integer.parseInt(request.getParameter("direction"));
        String annotation = ((request.getParameter("annotation")));


        try {
            if (annotation != null) {

                ServiceFactory.getINSTANCE().getMapPointsControlService().addMapPoint(coordinates, address, direction, annotation);
            } else {
                ServiceFactory.getINSTANCE().getMapPointsControlService().addMapPoint(coordinates, address, direction);
            }

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }


        response.sendRedirect(request.getContextPath() + "/");
    }
}
