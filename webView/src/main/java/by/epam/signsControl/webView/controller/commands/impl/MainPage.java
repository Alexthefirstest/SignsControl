package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * main page, forward to the main page, set attributes
 */
public class MainPage implements Command {

    private static final Logger logger = LogManager.getLogger(MainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");


        try {

            if ((Integer) (request.getSession().getAttribute(Constants.USER_ROLE)) != Constants.USER_ANONYM_ROLE) {

                request.setAttribute("types_of_work", ServiceFactory.getINSTANCE().getTypeOfWorkControlService().getUnblockedTypesOfWork());
                request.setAttribute("organisations",
                        by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory.getINSTANCE().getOrganisationsControllerService()
                                .getUnblockedOrganisations(Constants.PERFORMERS_ORGANISATIONS_ROLE));

            }

        } catch (ServiceValidationException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }


        request.getRequestDispatcher("/").forward(request, response);
    }

}
