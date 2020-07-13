package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.IBankAccountsManagerService;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBankAccountFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(AddBankAccountFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        IBankAccountsManagerService bankAccountsManager = ServiceFactory.getINSTANCE().getBankAccountsManager();

        int orgID = Integer.parseInt(request.getParameter("orgID"));
        String minBalance = request.getParameter("minBalance");
        String blocked = request.getParameter("blocked");
        String info = request.getParameter("orgInfo");

        logger.info(orgID);
        logger.info(minBalance);
        logger.info(blocked);
        logger.info(info);
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

        } catch (ServiceException ex) {
            logger.warn(ex);
        }
        response.sendRedirect(request.getContextPath() + "/bank_accounts");
    }
}
