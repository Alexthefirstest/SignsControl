package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class MainPage implements Command {

    private static final Logger logger = LogManager.getLogger(MainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");


        try {

            //if (request.getSession().getAttribute("role") != null) {

            request.setAttribute("types_of_work", ServiceFactory.getINSTANCE().getTypeOfWorkControlService().getUnblockedTypesOfWork());
            request.setAttribute("organisations",
                    by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory.getINSTANCE().getOrganisationsControllerService()
            .getUnblockedOrganisations(3));

            // }
        } catch (ServiceException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException e) {
            logger.warn(e);
        }
        //get directions

        request.getRequestDispatcher("/").forward(request, response);
    }

}
