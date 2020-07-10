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

public class RemovePDDSign implements Command {
    private static final Logger logger = LogManager.getLogger(RemovePDDSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        logger.info("inside execute");

        try {
            ServiceFactory.getINSTANCE().getPddSignsControlService().
                    removeSign(Integer.parseInt(request.getParameter("signID")));

        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        response.sendRedirect(request.getContextPath() + "/pdd_signs");
    }
}
