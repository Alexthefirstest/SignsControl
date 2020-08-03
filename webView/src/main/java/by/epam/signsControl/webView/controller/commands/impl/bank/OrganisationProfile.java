package by.epam.signsControl.webView.controller.commands.impl.bank;

import by.epam.bank.bean.BankAccount;
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
import java.util.regex.Pattern;

public class OrganisationProfile implements Command {

    private static final Logger logger = LogManager.getLogger(OrganisationProfile.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandControllerException, ServletException, IOException {

        logger.info("inside execute");

        try {

            AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);

            String id = RequestParser.getSecondCommandFromURI(request);

            if (!Pattern.matches("\\d+",id)) {
                request.getRequestDispatcher("/").forward(request, response);
                return;
            }

            int organisationID = Integer.parseInt(id);

            BankAccount[] bankAccounts =
                    ServiceFactory.getINSTANCE().getBankAccountsDeliver()
                            .executeRequest(ServiceFactory.getINSTANCE().getRequestBuilder().findById(organisationID).build());

            if (bankAccounts.length < 1) {
                throw new CommandControllerValidationException("this organisation do not exist");
            }

            if ((Integer) request.getAttribute(Constants.ORGANISATION_ID) != bankAccounts[0].getOrganisation().getId()) {
                throw new CommandControllerValidationException("wrong access");
            }


            request.setAttribute("bank_account", bankAccounts[0]);
            request.setAttribute("organisations", by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations());

            request.getRequestDispatcher("/WEB-INF/jsp/bank/organisation_profile.jsp").forward(request, response);

        } catch (
                ServiceValidationException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (
                ServiceException | by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
