package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetTypesOfWork implements Command {

    private static Logger logger = LogManager.getLogger(GetTypesOfWork.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        try {

            request.setAttribute("types_of_work", ServiceFactory.getINSTANCE().getTypeOfWorkControlService().getTypesOfWork());

            request.getRequestDispatcher("WEB-INF/jsp/orders/types_of_work.jsp").forward(request, response);

        } catch (
                ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

    }
}
