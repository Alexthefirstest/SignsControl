package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.IBankAccountsManagerService;
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
 * add bank account to the data base
 */
public class AddBankAccountFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(AddBankAccountFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);

        IBankAccountsManagerService bankAccountsManager = ServiceFactory.getINSTANCE().getBankAccountsManager();

        int orgID = Integer.parseInt(request.getParameter("orgID"));
        String minBalance = request.getParameter("minBalance");
        String blocked = request.getParameter("blocked");
        String info = request.getParameter("orgInfo");


        try {


            bankAccountsManager.createBankAccount(orgID);


            if (!minBalance.isEmpty()) {
                bankAccountsManager.setMinAllowedBalance(orgID, Double.parseDouble(minBalance));
            }


            if ("true".equals(blocked)) {
                bankAccountsManager.blockBankAccounts(orgID);
            }


            if (!info.isEmpty()) {
                bankAccountsManager.setInfo(orgID, info);
            }

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
        response.sendRedirect(request.getContextPath() + "/bank_accounts");
    }
}
