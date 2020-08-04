package by.epam.signsControl.webView.controller.commands.impl.signsControl;

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
 * remove standard size in case it didn't used in local signs
 */
public class RemoveStandardSize implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveStandardSize.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  CommandControllerException {

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);

        logger.info("inside execute");

        try {
            ServiceFactory.getINSTANCE().getStandardSizesControlService().
                    removeStandardSize(Integer.parseInt(request.getParameter("size")));

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }


        response.sendRedirect(request.getContextPath() + "/standard_sizes");
    }
}
