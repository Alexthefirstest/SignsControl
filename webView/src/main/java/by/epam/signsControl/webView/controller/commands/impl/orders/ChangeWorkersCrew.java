package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
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
 * change workers crew in case crew organisation equals user organisation  session attribute
 */
public class ChangeWorkersCrew implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeWorkersCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        try {

            AccessRulesChecker.organisationRoleCheck(request, Constants.PERFORMERS_ORGANISATIONS_ROLE);
            AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

            IWorkersCrewControlService workersCrewControlService = by.epam.orders.service.factory.ServiceFactory.getINSTANCE().getWorkersCrewControlService();

            String command = RequestParser.getSecondCommandFromURI(request);

            if(command==null){
                throw new CommandControllerValidationException("wrong command");
            }

            switch (command) {

                case "delete":

                    workersCrewControlService.setDateOfRemove(
                            Integer.parseInt(request.getParameter("wc")), (new SimpleDateFormat("yyyy.MM.dd").format(new Date())),
                            (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID));


                    break;

                case "set_info":

                    workersCrewControlService.
                            setInfo(Integer.parseInt(request.getParameter("wc")), request.getParameter("info"),
                                    (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID));

                    break;

                case "delete_worker":

                    workersCrewControlService.
                            removeWorker(Integer.parseInt(request.getParameter("wc"))
                                    , Integer.parseInt(request.getParameter("worker")),
                                    (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID));

                    break;

            }


            response.sendRedirect(request.getContextPath() + "/workers_crews");

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }

}