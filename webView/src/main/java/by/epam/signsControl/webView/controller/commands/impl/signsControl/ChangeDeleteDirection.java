package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.IMapPointsControlService;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeDeleteDirection implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeDeleteDirection.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("inside execute");

        IMapPointsControlService mps = ServiceFactory.getINSTANCE().getMapPointsControlService();

        int signList = Integer.parseInt(request.getParameter("old_direction"));
        int newDirection = Integer.parseInt(request.getParameter("new_direction"));
        String annotation = request.getParameter("annotation");
        String address = request.getParameter("address");

        logger.info("params" + "sl" + signList + "newDir" + newDirection + "annot" + annotation + "address" + address);

        try {
            if (newDirection == -2) {
                mps.removeMapPoint(signList);
            } else if (newDirection == -1) {
                mps.setParameters(signList, address, annotation);
            } else {
                mps.setParameters(signList, newDirection, address, annotation);

            }


        } catch (ServiceException ex) {
            logger.warn(ex);
        }


        response.sendRedirect(request.getContextPath() + "/");
    }
}