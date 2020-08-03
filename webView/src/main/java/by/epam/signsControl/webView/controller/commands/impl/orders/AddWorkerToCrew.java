package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.IWorkersCrewControlService;
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
 * add worker to crew if organisation
 */
public class AddWorkerToCrew implements Command {

    private static final Logger logger = LogManager.getLogger(AddWorkerToCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {
        try {
            logger.info("inside execute");

            IWorkersCrewControlService workersCrewControlService = ServiceFactory.getINSTANCE().getWorkersCrewControlService();

            AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

            workersCrewControlService.addWorker(Integer.parseInt(request.getParameter("wc")),
                    Integer.parseInt(request.getParameter("userID")), (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID));

            response.sendRedirect(request.getContextPath() + "/workers_crews");

        } catch (
                ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (
                ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}