package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class WorkersCrews implements Command {

    private static final Logger logger = LogManager.getLogger(WorkersCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, by.epam.orders.service.exceptions.ServiceException {

        logger.info("inside execute");
        //qq

        WorkersCrew[] workersCrews;

        Object organisation = request.getSession().getAttribute(LoginFormHandler.ORGANISATION_ID);

        if (organisation != null) {

            int thisOrgId = (Integer) organisation;
            logger.info("hihi");
            request.setAttribute("usersOfOrg", ServiceFactory.getINSTANCE().getUsersControllerService().getUsers(thisOrgId));
        }

        String showEmpty = request.getParameter("showEmpty");

        logger.info(showEmpty);

        String organisationID = request.getParameter("organisationID");

        IWorkersCrewControlService workersCrewControlService = by.epam.orders.service.factory.ServiceFactory.getINSTANCE().getWorkersCrewControlService();


        workersCrews = (organisationID == null) ? workersCrewControlService.getWorkersCrews()
                : (showEmpty == null ? workersCrewControlService.getWorkersCrews(Integer.parseInt(organisationID))
                : workersCrewControlService.getEmptyWorkersCrews(Integer.parseInt(organisationID)));


        request.setAttribute("workersCrews", workersCrews);
        request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations());

        request.getRequestDispatcher("/WEB-INF/jsp/orders/workers_crews.jsp").forward(request, response);

    }

}