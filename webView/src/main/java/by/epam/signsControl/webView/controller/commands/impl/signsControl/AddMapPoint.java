package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMapPoint implements Command {

    private static final Logger logger = LogManager.getLogger(AddMapPoint.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        int role;

        logger.info("inside execute");

        if (1 == (role = (Integer) request.getSession().getAttribute("role"))) {

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

            } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
                logger.warn(e);
            }

        } else {
            logger.warn("wrong role " + role);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
