package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.controller.commands.impl.signsControl.ResponseCreator;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetOrdersChangeInfo implements Command {


    private static final Logger logger = LogManager.getLogger(GetOrdersChangeInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE, Constants.PERFORMERS_ORGANISATIONS_ROLE);

        response.setContentType("text/plain");

        ServiceFactory sf = ServiceFactory.getINSTANCE();

        String command = RequestParser.getSecondCommandFromURI(request);

        String coordinates = request.getParameter("pointCoordinates");

        String result;


        try {


            switch (command) {

                case "remove":
                    result = ResponseCreator.createJSON(sf.getOrdersControlService().getUnExecutedOrders(coordinates));
                    break;
                case "execute":


                    result = OrdersResponseCreator.createJSON(sf.getWorkersCrewControlService().getWorkersCrewsByUser(102),
                            sf.getOrdersControlService().getUnExecutedOrders(coordinates));
                    break;

                default:
                    return;
            }


            logger.info(result);


            response.getWriter().write(result);


        } catch (
                ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }

}
