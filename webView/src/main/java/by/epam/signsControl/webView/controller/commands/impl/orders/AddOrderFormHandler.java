package by.epam.signsControl.webView.controller.commands.impl.orders;

import by.epam.orders.service.IOrdersControlService;
import by.epam.orders.service.factory.ServiceFactory;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.commands.Command;
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

        int customerID = Integer.parseInt(request.getParameter("customer_id"));
        int signListID = Integer.parseInt(request.getParameter("sign_list"));
            int pddSignID = Integer.parseInt(request.getParameter("pdd_sign"));
            int standardSize = Integer.parseInt(request.getParameter("standard_size"));
            int typeOfWork = Integer.parseInt(request.getParameter("type_of_work"));

            String annotation = ((request.getParameter("annotation")));

            logger.info((request.getParameter("annotation")));

            try {


                  ordersControlService.addOrder(signListID, pddSignID, standardSize, customerID, typeOfWork, annotation);



            } catch (by.epam.orders.service.exceptions.ServiceException e) {
                logger.warn(e);
            }


        response.sendRedirect(request.getContextPath() + "/");
    }
}
