package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.service.IRequestBuilderService;
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
 * show bank accounts
 */
public class ShowBankAccounts implements Command {

    private static final Logger logger = LogManager.getLogger(ShowBankAccounts.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);

        try {



            IRequestBuilderService requestBuilder = ServiceFactory.getINSTANCE().getRequestBuilder();

            String blocked = request.getParameter("blocked");

            logger.info("blocked" + blocked);
            if ("1".equals(blocked)) {
                logger.info("if");
                requestBuilder.showOnlyBlocked();

            } else if ("0".equals(blocked)) {
                logger.info("else");
                requestBuilder.showOnlyUnblocked();
            }


            String balance = request.getParameter("balance");

            logger.info("balance" + balance);
            if ("positive".equals(balance)) {
                logger.info("if");
                requestBuilder.showOnlyWithPositiveBalance();
            } else if ("negative".equals(balance)) {
                logger.info("else");
                requestBuilder.showOnlyWithNegativeBalance();
            }

            String balanceMoreThen = request.getParameter("balanceMoreThenValue");

            logger.info("balancemoreThen" + balanceMoreThen);

            if (balanceMoreThen != null) {
                try {
                    requestBuilder.whereBalanceMoreThen(Integer.parseInt(balanceMoreThen));
                } catch (NumberFormatException ex) {
                    logger.warn("format exception more " + balanceMoreThen);
                }
            }

            String balanceLessThen = request.getParameter("balanceLessThenValue");
            logger.info("balanceLess" + balanceLessThen);

            if (balanceLessThen != null) {
                try {
                    requestBuilder.whereBalanceLessThen(Integer.parseInt(balanceLessThen));
                } catch (NumberFormatException ex) {
                    logger.warn("format exception less");
                }
            }

            String blockedSort = request.getParameter("blockedSort");

            logger.info("blockedSOrt " + blockedSort);
            if ("min".equals(blockedSort)) {
                requestBuilder.withSortByBlocked();
                logger.info("if");
            } else if ("max".equals(blockedSort)) {
                requestBuilder.withSortByBlocked().setDESC();
            }


            String minAllowedBalance = request.getParameter("minAllowedBalanceSort");

            logger.info("minAlBal" + minAllowedBalance);
            if ("min".equals(minAllowedBalance)) {
                requestBuilder.withSortByMinAllowedBalance();
                logger.info("if");
            } else if ("max".equals(minAllowedBalance)) {
                requestBuilder.withSortByMinAllowedBalance().setDESC();
            }

            String balanceSort = request.getParameter("balanceSort");

            logger.info("balanseSort " + balanceSort);
            if ("min".equals(balanceSort)) {
                requestBuilder.withSortByBalance();
                logger.info("if");
            } else if ("max".equals(balanceSort)) {
                logger.info("else");
                requestBuilder.withSortByBalance().setDESC();
            }

            String orgNameSort = request.getParameter("orgNameSort");

            logger.info("name" + orgNameSort);
            if ("min".equals(orgNameSort)) {
                requestBuilder.withSortByOrganisationName();
                logger.info("if");
            } else if ("max".equals(orgNameSort)) {
                requestBuilder.withSortByOrganisationName().setDESC();
            }

            request.setAttribute("bank_accounts",
                    ServiceFactory.getINSTANCE().getBankAccountsDeliver().executeRequest(requestBuilder.build()));

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/bank/bank_accounts.jsp").forward(request, response);

    }

}

