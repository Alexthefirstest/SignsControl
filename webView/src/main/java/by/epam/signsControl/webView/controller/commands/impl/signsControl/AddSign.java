package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
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
 * add local sign
 */
public class AddSign implements Command {

    private static final Logger logger = LogManager.getLogger(AddSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {


        logger.info("inside execute");

        AccessRulesChecker.organisationRoleCheck(request, Constants.ODD_ORGANISATION_ROLE);


            ILocalSignsControlService localSignsControlService = ServiceFactory.getINSTANCE().getLocalSignsControlService();

            int signListID = Integer.parseInt(request.getParameter("sign_list"));
            int pddSignID = Integer.parseInt(request.getParameter("pdd_sign"));
            int standardSize = Integer.parseInt(request.getParameter("standard_size"));
            String dateOfAdd = ((request.getParameter("date_of_add")));
            String dateOfRemove = ((request.getParameter("date_of_remove")));
            String annotation = ((request.getParameter("annotation")));

logger.info((request.getParameter("annotation")));

            try {

                if (dateOfAdd.isEmpty() && dateOfRemove.isEmpty()) {


                    localSignsControlService.addSign(signListID, pddSignID, standardSize, annotation);
                } else if (dateOfRemove.isEmpty()) {

                    localSignsControlService.addSign(signListID, pddSignID, standardSize, dateOfAdd.replaceAll("-", "."), annotation);
                } else {

                    localSignsControlService.addSign(signListID, pddSignID, standardSize, dateOfAdd
                            .replaceAll("-", "."), dateOfRemove.replaceAll("-", "."), annotation);
                }


            } catch (ServiceValidationException e) {
                throw new CommandControllerValidationException(e);
            } catch (ServiceException e) {
                throw new CommandControllerException(e);
            }


        response.sendRedirect(request.getContextPath() + "/");
    }
}

