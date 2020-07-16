package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int role;
        int workersCrewsOrganisation;

        logger.info("inside execute");

        IOrdersControlService ordersControlService = ServiceFactory.getINSTANCE().getOrdersControlService();

        String orderAction = request.getParameter("order_action");

        int orderID = Integer.parseInt(request.getParameter("order_id"));


        try {


            switch (orderAction) {

                case "delete":
                    ordersControlService.removeOrder(orderID);
                    break;
                case "execute":

                    String info = ordersControlService.getInfo(orderID);
                    String newInfo = request.getParameter("annotation");

                    int workers_crews = Integer.parseInt(request.getParameter("workers_crew"));

                    ordersControlService.setDateOfExecution(orderID, (new SimpleDateFormat("yyyy.MM.dd").format(new Date())));
                    ordersControlService.setWorkersCrew(orderID, workers_crews);
                    ordersControlService.setInfo(orderID, (info == null ? (newInfo) : info+" " + newInfo));
                    break;
                default:
                    return;

            }


        } catch (by.epam.orders.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        response.sendRedirect(request.getContextPath() + "/");
    }
}
