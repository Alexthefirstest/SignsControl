package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
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

public class ChangeStandardSize implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeStandardSize.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {



        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);

        int size = Integer.parseInt(request.getParameter("size"));

        try {

            request.setAttribute("size", size);
           request.setAttribute("info", ServiceFactory.getINSTANCE().getStandardSizesControlService()
                    .getInfo(size));

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/signs_control/change_standard_size.jsp").forward(request, response);
    }
}
