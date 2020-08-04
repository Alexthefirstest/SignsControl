package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
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
 * send to workers crews jsp
 */
public class WorkersCrews implements Command {

    private static final Logger logger = LogManager.getLogger(WorkersCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");
        //qq
        try {
            AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);
            AccessRulesChecker.organisationRoleCheck(request, Constants.PERFORMERS_ORGANISATIONS_ROLE);

            WorkersCrew[] workersCrews;

            int organisation = (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID);


            request.setAttribute("usersOfOrg", ServiceFactory.getINSTANCE().getUsersControllerService().getUsers(organisation));


            String showEmpty = request.getParameter("showEmpty");

            logger.info(showEmpty);

            IWorkersCrewControlService workersCrewControlService = by.epam.orders.service.factory.ServiceFactory.getINSTANCE().getWorkersCrewControlService();


            workersCrews = (showEmpty == null) ? workersCrewControlService.getWorkersCrews(organisation)
                    : workersCrewControlService.getEmptyWorkersCrews(organisation);


            request.setAttribute("workersCrews", workersCrews);

            request.getRequestDispatcher("/WEB-INF/jsp/orders/workers_crews.jsp").forward(request, response);

        } catch (ServiceValidationException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }
    }

}