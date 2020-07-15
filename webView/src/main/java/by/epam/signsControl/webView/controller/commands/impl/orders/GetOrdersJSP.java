package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.bean.Order;
import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.exceptions.ServiceException;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetOrdersJSP implements Command {

    private static final Logger logger = LogManager.getLogger(GetOrdersJSP.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        Order[] orders;

        try {


            String command = request.getParameter("orders_option");

            if (command == null) {
                command = "default";
            }

            IOrdersControlService ordersControl = ServiceFactory.getINSTANCE().getOrdersControlService();

            switch (command) {

                case "all":
                    orders = ordersControl.getOrders();
                    break;
                case "executed":
                    orders = ordersControl.getExecutedOrders();
                    break;
                case "unexecuted":
                    orders = ordersControl.getUnExecutedOrders();
                    break;
                default:
                    orders = ordersControl.getOrders();
                    break;
            }
            request.setAttribute("orders", orders);

            request.getRequestDispatcher("/WEB-INF/jsp/orders/orders.jsp").forward(request, response);

        } catch (ServiceException e) {
            logger.warn(e);
        }
    }
}