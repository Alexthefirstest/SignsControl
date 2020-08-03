package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.orders.service.factory.ServiceFactory;
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

public class RemoveTypeOfWork implements Command {

    private static Logger logger = LogManager.getLogger(RemoveTypeOfWork.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {


        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE);

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            ServiceFactory.getINSTANCE().getTypeOfWorkControlService().removeTypeOfWork(id);
            response.sendRedirect(request.getContextPath() + "/types_of_work");


        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

    }
}
