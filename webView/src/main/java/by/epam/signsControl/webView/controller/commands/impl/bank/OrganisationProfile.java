package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.bean.BankAccount;
import by.epam.bank.service.exceptions.ServiceException;
import by.epam.bank.service.exceptions.ServiceValidationException;
import by.epam.bank.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.exceptions.AccessException;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * organisation profile: organisation and bank account info
 */
public class OrganisationProfile implements Command {

    private static final Logger logger = LogManager.getLogger(OrganisationProfile.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandControllerException, ServletException, IOException {

        logger.info("inside execute");

        try {

            ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();

            AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

            String id = RequestParser.getSecondCommandFromURI(request);

            if (!Pattern.matches("\\d+", id)) {
                request.getRequestDispatcher("/").forward(request, response);
                throw new CommandControllerValidationException("wrong id");
            }

            assert id != null;
            int organisationID = Integer.parseInt(id);

            BankAccount bankAccount =
                    ServiceFactory.getINSTANCE().getOrganisationsDeliver().findOrganisationByID(organisationID);

            if (bankAccount == null) {
                throw new CommandControllerValidationException("this organisation do not exist");
            }

            if ((Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID) != bankAccount.getOrganisation().getId()) {
                throw new AccessException("wrong access");
            }

            logger.info("AAAAAAAAAAAAAAAa: ");
            logger.info("INFO: "+bankAccount.getInfo());
            logger.info("INFO: "+(bankAccount.getInfo()==null));

            request.setAttribute("bank_account", bankAccount);
            request.setAttribute("bank_account_organisations",
                    serviceFactory.getBankAccountsDeliver().executeRequest(
                            serviceFactory.getRequestBuilder().withSortByOrganisationName().build()
                    ));

            request.getRequestDispatcher("/WEB-INF/jsp/bank/organisation_profile.jsp").forward(request, response);

        } catch (
                ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (
                ServiceException  e) {
            throw new CommandControllerException(e);
        }
    }
}
