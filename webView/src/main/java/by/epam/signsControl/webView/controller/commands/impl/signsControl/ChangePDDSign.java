package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePDDSign  implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePDDSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        logger.info("inside execute");

        int size = Integer.parseInt(request.getParameter("size"));

        try {

            request.setAttribute("size", size);
            request.setAttribute("info", ServiceFactory.getINSTANCE().getStandardSizesControlService()
                    .getInfo(size));

        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        request.getRequestDispatcher("/WEB-INF/jsp/change_standard_size.jsp").forward(request, response);
    }
}
