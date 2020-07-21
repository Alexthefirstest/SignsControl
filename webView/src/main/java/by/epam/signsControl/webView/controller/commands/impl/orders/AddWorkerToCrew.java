package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddWorkerToCrew implements Command {

    private static final Logger logger = LogManager.getLogger(AddWorkerToCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, by.epam.orders.service.exceptions.ServiceException {

        logger.info("inside execute");

        ServiceFactory.getINSTANCE().getWorkersCrewControlService().addWorker(Integer.parseInt(request.getParameter("wc")),
                Integer.parseInt(request.getParameter("userID")));

        response.sendRedirect(request.getContextPath() + "/workers_crews");

    }
}