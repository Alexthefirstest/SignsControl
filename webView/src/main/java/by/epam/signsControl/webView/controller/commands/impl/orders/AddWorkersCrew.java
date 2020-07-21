package by.epam.signsControl.webView.controller.commands.impl.orders;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWorkersCrew implements Command {

    private static final Logger logger = LogManager.getLogger(AddWorkersCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, by.epam.orders.service.exceptions.ServiceException {

        logger.info("inside execute");
        logger.info("ger2 "+request.getSession().getAttribute(LoginFormHandler.ORGANISATION_ID));
        by.epam.orders.service.factory.ServiceFactory.getINSTANCE().getWorkersCrewControlService()
                .addWorkersCrew((new SimpleDateFormat("yyyy.MM.dd").format(new Date())), request.getParameter("info"),
                        (Integer) request.getSession().getAttribute(LoginFormHandler.ORGANISATION_ID));
logger.info("gere");

        response.sendRedirect(request.getContextPath() + "/workers_crews");

    }

}