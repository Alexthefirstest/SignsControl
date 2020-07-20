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

public class AddOrderFormHandler implements Command {

    private static Logger logger = LogManager.getLogger(AddOrderFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        int role;

        logger.info("inside execute");


        IOrdersControlService ordersControlService = ServiceFactory.getINSTANCE().getOrdersControlService();
        IFinanceOperationsManagerService financeOperationsService = by.epam.bank.service.factory.ServiceFactory.getINSTANCE().getFinanceOperationsManager();

        int customerID = (Integer) (request.getSession().getAttribute(LoginFormHandler.ORGANISATION_ID));
        int signListID = Integer.parseInt(request.getParameter("sign_list"));
        int pddSignID = Integer.parseInt(request.getParameter("pdd_sign"));
        int standardSize = Integer.parseInt(request.getParameter("standard_size"));
        int typeOfWork = Integer.parseInt(request.getParameter("type_of_work"));
        int orgTo = Integer.parseInt(request.getParameter("organisationTo"));
        String priceStr = request.getParameter("acceptPrice");
        double price;
        String annotation = ((request.getParameter("annotation")));

        Order order = null;

        try {

            double typeOfWorkPrice;


            if (orgTo == 0) {
                logger.info("if");
                ordersControlService.addOrder(signListID, pddSignID, standardSize, customerID, typeOfWork, annotation);
            } else if (!priceStr.isEmpty()
                    && (typeOfWorkPrice = ServiceFactory.getINSTANCE().getTypeOfWorkControlService().getTypeOfWork(typeOfWork).getPrice())
                            == (price = Double.parseDouble(priceStr))) {

                logger.info("else");


                logger.info(typeOfWorkPrice);
                logger.info(price);
                logger.info(typeOfWorkPrice == price);


                order = ordersControlService.addOrder(signListID, pddSignID, standardSize, customerID, typeOfWork, annotation);

                ordersControlService.setTransaction(order.getId(), financeOperationsService.transferMoney(customerID, orgTo, typeOfWorkPrice).getId());
            } else {
                //wrong price
                logger.warn("wrong price");
            }


        } catch (by.epam.orders.service.exceptions.ServiceException e) {
            logger.warn(e);
        } catch (by.epam.bank.service.exceptions.ServiceException ex) {

            try {
                ordersControlService.removeOrder(order.getId());
            } catch (by.epam.orders.service.exceptions.ServiceException e) {
                logger.warn(e);
                logger.warn(ex);
            }
            logger.warn(ex);
        }


        response.sendRedirect(request.getContextPath() + "/");
    }
}
