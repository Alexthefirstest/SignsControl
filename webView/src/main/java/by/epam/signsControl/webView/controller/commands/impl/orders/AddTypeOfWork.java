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

/**
 * add type of work
 */
public class AddTypeOfWork implements Command {

    private static Logger logger = LogManager.getLogger(AddTypeOfWork.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {


        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE);

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            ServiceFactory.getINSTANCE().getTypeOfWorkControlService().addTypeOfWork(name, price);
            response.sendRedirect(request.getContextPath() + "/types_of_work");
        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

    }

}
