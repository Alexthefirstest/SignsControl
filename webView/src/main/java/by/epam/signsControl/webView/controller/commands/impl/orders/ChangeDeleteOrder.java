package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.exceptions.ServiceValidationException;
import by.epam.orders.service.factory.ServiceFactory;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDeleteOrder implements Command {

    private static Logger logger = LogManager.getLogger(ChangeDeleteOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE, Constants.PERFORMERS_ORGANISATIONS_ROLE);

        IOrdersControlService ordersControlService = ServiceFactory.getINSTANCE().getOrdersControlService();

        String orderAction = request.getParameter("order_action");

        int orderID = Integer.parseInt(request.getParameter("order_id"));


        try {


            if ((Integer) request.getSession().getAttribute(Constants.ORGANISATION_ROLE) == Constants.ODD_ORGANISATION_ROLE
                    && orderAction.equals("delete")) {

                ordersControlService.removeOrder(orderID);

            }else  if ((Integer) request.getSession().getAttribute(Constants.ORGANISATION_ROLE) == Constants.PERFORMERS_ORGANISATIONS_ROLE
                    && orderAction.equals("execute")) {

                String info = ordersControlService.getInfo(orderID);
                String newInfo = request.getParameter("annotation");

                int workers_crews = Integer.parseInt(request.getParameter("workers_crew"));

                ordersControlService.setDateOfExecution(orderID, (new SimpleDateFormat("yyyy.MM.dd").format(new Date())));
                ordersControlService.setWorkersCrew(orderID, workers_crews);
                ordersControlService.setInfo(orderID, (info == null ? (newInfo) : info + " " + newInfo));

                response.sendRedirect(request.getContextPath() + "/");
            } else{
                throw new CommandControllerValidationException("wrong role or action");
            }


        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }



    }
}
