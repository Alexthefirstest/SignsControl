package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.bean.Order;
import by.epam.orders.bean.TypeOfWork;
import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;

import by.epam.signsControl.webView.controller.commands.impl.signsControl.ResponseCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetOrdersChangeInfo implements Command {


    private static final Logger logger = LogManager.getLogger(GetOrdersChangeInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

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


        } catch (by.epam.orders.service.exceptions.ServiceException e) {
            logger.warn(e);
        }
    }

}
