package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.bank.service.IFinanceOperationsManagerService;
import by.epam.orders.bean.Order;
import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayOrder implements Command {

    private static Logger logger = LogManager.getLogger(PayOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int role;

        logger.info("inside execute");


        IOrdersControlService ordersControlService = ServiceFactory.getINSTANCE().getOrdersControlService();
        IFinanceOperationsManagerService financeOperationsService = by.epam.bank.service.factory.ServiceFactory.getINSTANCE().getFinanceOperationsManager();

        int id = Integer.parseInt(request.getParameter("id"));

        Order order = null;

        try {

            order = ordersControlService.getOrder(id);

            if (order.getTransactionID() > 0) {
                //attribute
                logger.info("transaction sated");
            } else {

                int orgTo = Integer.parseInt(request.getParameter("organisationTo"));
                double price = Double.parseDouble(request.getParameter("acceptPrice"));
                int customer = (Integer) (request.getSession().getAttribute(LoginFormHandler.ORGANISATION_ID));
                double typeOfWorkPrice = order.getTypeOfWork().getPrice();


                logger.info(typeOfWorkPrice);
                logger.info(price);
                logger.info(typeOfWorkPrice == price);

                if (typeOfWorkPrice == price) {
                    ordersControlService.setTransaction(order.getId(), financeOperationsService.transferMoney(customer, orgTo, typeOfWorkPrice).getId());
                } else {
                    //wrong price
                    logger.warn("wrong price");
                }
            }

        } catch (by.epam.orders.service.exceptions.ServiceException | by.epam.bank.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        response.sendRedirect(request.getContextPath() + "/orders");
    }
}