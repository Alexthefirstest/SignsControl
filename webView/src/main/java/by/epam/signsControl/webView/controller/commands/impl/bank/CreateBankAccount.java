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
 * create bank account
 */
public class CreateBankAccount implements Command {

    private static final Logger logger = LogManager.getLogger(CreateBankAccount.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        AccessRulesChecker.organisationRoleCheck(request, Constants.BANK_ORGANISATION_ROLE);

        logger.info("inside execute");


        try {

            request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsDeliver().showOrganisationsWithoutBankAccounts());

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/bank/add_bank_account.jsp").forward(request, response);
    }
}
