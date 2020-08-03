package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
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

public class AddStandardSize implements Command {
    private static final Logger logger = LogManager.getLogger(AddStandardSize.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);

        logger.info("inside execute");

        request.getRequestDispatcher("/WEB-INF/jsp/signs_control/add_standard_size.jsp").forward(request, response);


    }
}