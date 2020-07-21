package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.factory.ServiceFactory;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExecuteTransaction implements Command {

    private static final Logger logger = LogManager.getLogger(ExecuteTransaction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");

        try {
            int organisation = Integer.parseInt(request.getParameter("organisationID"));
            double money = Double.parseDouble(request.getParameter("money"));
            logger.info(organisation);
            logger.info(money);

            ServiceFactory.getINSTANCE().getFinanceOperationsManager().transferMoney(
                    (Integer) request.getSession().getAttribute(LoginFormHandler.ORGANISATION_ID),
                    organisation, money);

        } catch (by.epam.bank.service.exceptions.ServiceException e) {
            logger.warn(e);
        }

        response.sendRedirect(request.getContextPath() + "/organisations");

    }
}
