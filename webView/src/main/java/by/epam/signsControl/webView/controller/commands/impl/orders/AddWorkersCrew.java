package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * add workers crew to db
 */
public class AddWorkersCrew implements Command {

    private static final Logger logger = LogManager.getLogger(AddWorkersCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

        try {
            by.epam.orders.service.factory.ServiceFactory.getINSTANCE().getWorkersCrewControlService()
                    .addWorkersCrew((new SimpleDateFormat("yyyy.MM.dd").format(new Date())), request.getParameter("info"),
                            (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID));
        }catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

        response.sendRedirect(request.getContextPath() + "/workers_crews");

    }

}