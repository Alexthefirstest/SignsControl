package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.bank.service.factory.ServiceFactory;
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

/**
 * send money from one bank account to the another
 */
public class ExecuteTransaction implements Command {

    private static final Logger logger = LogManager.getLogger(ExecuteTransaction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

        try {
            int organisation = Integer.parseInt(request.getParameter("organisationID"));
            double money = Double.parseDouble(request.getParameter("money"));
            logger.info(organisation);
            logger.info(money);

            ServiceFactory.getINSTANCE().getFinanceOperationsManager().transferMoney(
                    (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID),
                    organisation, money);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

        response.sendRedirect(request.getContextPath() + "/show_transactions_history/1");

    }
}
