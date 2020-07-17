package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.service.IPDDSignsControlService;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePDDSign implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePDDSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        logger.info("inside execute");


        try {
            int signID = Integer.parseInt(request.getParameter("sign_id"));

            IPDDSignsControlService signsControlService = ServiceFactory.getINSTANCE().getPddSignsControlService();

            signsControlService.updateName(signID, request.getParameter("name"));
            signsControlService.updateDescription(signID, request.getParameter("description"));


        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        response.sendRedirect(request.getContextPath() + "/pdd_signs");

    }
}
