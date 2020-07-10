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

public class PDDSigns implements Command {

    private static final Logger logger = LogManager.getLogger(PDDSigns.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {



        logger.info("inside execute");

            try {
                    request.setAttribute("pdd_signs", ServiceFactory.getINSTANCE().getPddSignsControlService().getPddSigns());

            } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
                logger.warn(e);
            }


        request.getRequestDispatcher("/WEB-INF/jsp/pdd_signs.jsp").forward(request, response);
    }
}
