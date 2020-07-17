package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetPointHistory implements Command {


    private static final Logger logger = LogManager.getLogger(GetPointHistory.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("text/plain");


        try {

            response.getWriter().write(ResponseCreator.createPointHistory
                    (ServiceFactory.getINSTANCE().getLocalSignsControlService().getSigns(request.getParameter("pointCoordinates")),
                            request.getContextPath()));

        } catch (ServiceException e) {
            logger.warn(e);
        }
    }
}
