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
 *add money to the bank account, do not windrow money from sender
 */
public class AddMoney implements Command {

    private static final Logger logger = LogManager.getLogger(AddMoney.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);

        try {
            int organisation = Integer.parseInt(request.getParameter("addMoneyOrg"));
            double money = Double.parseDouble(request.getParameter("addMoneyValue"));
            logger.info(organisation);
            logger.info(money);

            ServiceFactory.getINSTANCE().getFinanceOperationsManager().addMoney(Constants.BANK_ID, organisation, money);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

        response.sendRedirect(request.getContextPath() + "/bank_accounts");

    }
}
