package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.bean.MapPoint$Orders;
import by.epam.orders.service.IOrdersControlService;
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

/**
 * return ajax orders
 */
public class GetOrders implements Command {

    private static final Logger logger = LogManager.getLogger(GetOrders.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE, Constants.PERFORMERS_ORGANISATIONS_ROLE);

        String command = RequestParser.getSecondCommandFromURI(request);

        response.setContentType("application/json");

        MapPoint$Orders[] orders;

        IOrdersControlService ordersControl = ServiceFactory.getINSTANCE().getOrdersControlService();

        try {

            switch (command) {

                case "all":
                    orders = ordersControl.getOrdersMapPoint();
                    break;
                case "executed":
                    orders = ordersControl.getExecutedOrdersMapPoint();
                    break;
                case "unexecuted":
                    orders = ordersControl.getUnExecutedOrdersMapPoint();
                    break;
                default:
                    orders = ordersControl.getOrdersMapPoint();
                    break;
            }

            response.getWriter().write(OrdersResponseCreator.createPointsJSON(orders));

        } catch (
                ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
