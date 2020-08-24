package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.ITypeOfWorkControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.orders.service.factory.ServiceFactory;
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


public class ChangeTypeOfWork implements Command {

    private static Logger logger = LogManager.getLogger(ChangeDeleteOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        AccessRulesChecker.organisationRoleCheck(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE);

        logger.info("inside execute");

        ITypeOfWorkControlService ordersControlService = ServiceFactory.getINSTANCE().getTypeOfWorkControlService();

        String orderAction = RequestParser.getSecondCommandFromURI(request);

        int id = Integer.parseInt(request.getParameter("id"));


        try {


            if (orderAction != null) {

                switch (orderAction) {

                    case "set_price":

                        ordersControlService.setPrice(id, Double.parseDouble(request.getParameter("price")));
                        break;

                    case "block":

                        ordersControlService.setBlock(id, true);
                        break;

                    case "unblock":

                        ordersControlService.setBlock(id, false);
                        break;
                    default:
                        return;

                }
            } else {
                logger.warn("orderAction is null");
            }

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

        response.sendRedirect(request.getContextPath() + "/types_of_work");
    }
}