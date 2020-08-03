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
import javax.servlet.http.Part;
import java.io.IOException;

public class SetSignImage implements Command {

    private static Logger logger = LogManager.getLogger(SetSignImage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        Part imagePart = request.getPart("image");
        long imageSize = imagePart.getSize();

        logger.info(imageSize);

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);

        try {


            if (imageSize > 0) {
                ServiceFactory.getINSTANCE().getPddSignsControlService().
                        setPicture(Integer.parseInt(request.getParameter("sign_id")), imagePart.getInputStream(), imageSize);
            }

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }


        response.sendRedirect(request.getContextPath() + "/pdd_signs");

    }

}
