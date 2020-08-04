package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.controller.commands.impl.bank.AddBankAccountFormHandler;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * add organisation form handler
 */
public class AddOrganisationFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(AddBankAccountFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");
        //qq
        try {

            AccessRulesChecker.organisationRoleCheck(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE);

            IOrganisationsControllerService organisationsControllerService = ServiceFactory.getINSTANCE().getOrganisationsControllerService();


            int role = Integer.parseInt(request.getParameter("role"));
            String name = request.getParameter("name");
            String info = request.getParameter("info");

            logger.info(name + " " + role);
            logger.info(info);

            Organisation organisation = organisationsControllerService.addOrganisation(name, role);

            if (!info.isEmpty()) {
                organisationsControllerService.setInfo(organisation.getId(), info);
            }

            response.sendRedirect(request.getContextPath() + "/organisations");


        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
