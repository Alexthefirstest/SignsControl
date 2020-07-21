package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.bean.WorkersCrew;
import by.epam.orders.service.IWorkersCrewControlService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.RequestParser;
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

public class ChangeWorkersCrew implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeWorkersCrew.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, by.epam.orders.service.exceptions.ServiceException {

        IWorkersCrewControlService workersCrewControlService = by.epam.orders.service.factory.ServiceFactory.getINSTANCE().getWorkersCrewControlService();

        logger.info("inside execute");

        String command = RequestParser.getSecondCommandFromURI(request);

        switch (command) {
            case "delete":
                logger.info("gere1");
                workersCrewControlService.setDateOfRemove(
                        Integer.parseInt(request.getParameter("wc")), (new SimpleDateFormat("yyyy.MM.dd").format(new Date())));
                logger.info("gere1/");

                break;

            case "set_info":
                logger.info("gere2");
                workersCrewControlService.
                        setInfo(Integer.parseInt(request.getParameter("wc")), request.getParameter("info")
                        );
                logger.info("gere2/");
                break;

            case "delete_worker":
                logger.info("gere3");
                workersCrewControlService.
                        removeWorker(Integer.parseInt(request.getParameter("wc"))
                                , Integer.parseInt(request.getParameter("worker")));
                logger.info("gere3/");
                break;

        }

        logger.info(response);
        logger.info(request);
        response.sendRedirect(request.getContextPath() + "/workers_crews");

    }

}