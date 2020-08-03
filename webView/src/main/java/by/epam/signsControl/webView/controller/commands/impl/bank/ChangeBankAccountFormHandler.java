package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.IBankAccountsManagerService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
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
 * change bank account
 */
public class ChangeBankAccountFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeBankAccountFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);

        IBankAccountsManagerService bankAccountsManagerService = ServiceFactory.getINSTANCE().getBankAccountsManager();

        logger.info("inside execute");

        String command = RequestParser.getSecondCommandFromURI(request);
        logger.info(command);

        try {

            int id = Integer.parseInt(request.getParameter("orgID"));
            logger.info("before switch");
            switch (command) {

                case "set_min_balance":
                    logger.info("set min balance");
                    bankAccountsManagerService.setMinAllowedBalance(id, Double.parseDouble(request.getParameter("minBalance")));
                    break;

                case "set_block":
                    logger.info("set block");
                    if ("true".equals(request.getParameter("block"))) {
                        bankAccountsManagerService.blockBankAccounts(id);
                    } else {
                        bankAccountsManagerService.unblockBankAccounts(id);
                    }
                    break;

                case "set_info":
                    logger.info("set info");
                    bankAccountsManagerService.setInfo(id, request.getParameter("orgInfo"));
                    break;
                default:
                    logger.info("default");
                    break;
            }


            request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsDeliver().showOrganisationsWithoutBankAccounts());

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

        response.sendRedirect(request.getContextPath() + "/bank_accounts");
    }
}
