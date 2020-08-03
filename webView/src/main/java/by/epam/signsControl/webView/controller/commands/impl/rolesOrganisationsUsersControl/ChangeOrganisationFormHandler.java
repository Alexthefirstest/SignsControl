package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
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

public class ChangeOrganisationFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeOrganisationFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        try {
            logger.info("inside execute");
            //qq

            AccessRulesChecker.organisationRoleCheck(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE);


            IOrganisationsControllerService organisationsControllerService = ServiceFactory.getINSTANCE().getOrganisationsControllerService();


            int id = Integer.parseInt(request.getParameter("id"));
            String roleStr = request.getParameter("role");
            String name = request.getParameter("name");
            String info = request.getParameter("info");
            String blockStr = request.getParameter("block");


            if(id==Constants.ADMINISTRATORS_ORGANISATION_ID){
                throw new CommandControllerValidationException("can't change admin organisation");
            }

            if (request.getParameter("setRole") != null) {
                organisationsControllerService.setRole(id, Integer.parseInt(roleStr));
            }

            if (request.getParameter("setName") != null) {
                organisationsControllerService.setName(id, name);
            }

            if (request.getParameter("setInfo") != null) {
                organisationsControllerService.setInfo(id, info);
            }

            if (blockStr != null) {

                logger.info("block" + blockStr);

                if ("true".equals(blockStr)) {
                    logger.info("true");
                    organisationsControllerService.setBlock(id, true);
                } else if ("false".equals(blockStr)) {
                    logger.info("false");
                    organisationsControllerService.setBlock(id, false);
                }

            }


            response.sendRedirect(request.getContextPath() + "/organisations");
        } catch (
                ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}


