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

public class ChangeStandardSizeFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeStandardSize.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        int size = Integer.parseInt(request.getParameter("size"));
        int oldSize = Integer.parseInt(request.getParameter("old_size"));

        try {

            ServiceFactory.getINSTANCE().getStandardSizesControlService()
                    .setInfo(oldSize, request.getParameter("info"));

            if (size != oldSize) {
                ServiceFactory.getINSTANCE().getStandardSizesControlService()
                        .setSize(oldSize, size);
            }

        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        response.sendRedirect(request.getContextPath() + "/standard_sizes");

    }
}
