package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.factory.ServiceFactory;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTypeOfWork implements Command {

    private static Logger logger = LogManager.getLogger(AddTypeOfWork.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        logger.info("inside execute");

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            ServiceFactory.getINSTANCE().getTypeOfWorkControlService().addTypeOfWork(name, price);
            response.sendRedirect(request.getContextPath() + "/types_of_work");
        } catch (by.epam.orders.service.exceptions.ServiceException e) {
            logger.warn(e);
        }

    }

}
