package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.IPDDSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import by.epam.signsControl.service.factory.ServiceFactory;
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
 * change pdd sign
 */
public class ChangePDDSign implements Command {

    private static final Logger logger = LogManager.getLogger(ChangePDDSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {


        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);

        try {
            int signID = Integer.parseInt(request.getParameter("sign_id"));

            IPDDSignsControlService signsControlService = ServiceFactory.getINSTANCE().getPddSignsControlService();

            signsControlService.updateName(signID, request.getParameter("name"));
            signsControlService.updateDescription(signID, request.getParameter("description"));


        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }


        response.sendRedirect(request.getContextPath() + "/pdd_signs");

    }
}
