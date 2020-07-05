package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.IMapPointsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeDeleteLocalSign implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeDeleteLocalSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("inside execute");
        logger.info(request.getParameter("deleteSign"));

        ILocalSignsControlService lsc = ServiceFactory.getINSTANCE().getLocalSignsControlService();

        int localSignId = Integer.parseInt(request.getParameter("local_sign_id"));


        logger.info(Integer.parseInt(request.getParameter("local_sign_id")));
        logger.info(request.getParameter("annotation"));
        logger.info(((request.getParameter("date_of_add"))));
        logger.info(((request.getParameter("date_of_remove"))));

        try {
            if (request.getParameter("deleteSign") != null) {
                lsc.deleteSign(localSignId);
            } else {

                String annotation = request.getParameter("annotation");
                String dateOfAdd = ((request.getParameter("date_of_add")));
                String dateOfRemove = ((request.getParameter("date_of_remove")));

                if (!dateOfAdd.isEmpty() && dateOfRemove.isEmpty()) {

                    lsc.setParameters(localSignId, dateOfAdd.replaceAll("-", "."),
                            dateOfRemove.replaceAll("-", "."), annotation);
                } else {


                    if (!dateOfAdd.isEmpty()) {
                        lsc.setDateOfAdd(localSignId, dateOfAdd.replaceAll("-", "."));
                    }

                    if (!dateOfRemove.isEmpty()) {
                        lsc.setDateOfRemove(localSignId, dateOfRemove.replaceAll("-", "."));
                    }

                    lsc.setAnnotation(localSignId, annotation);

                }

            }


        } catch (ServiceException ex) {
            logger.warn(ex);
        }

        response.sendRedirect(request.getContextPath() + "/");

    }
}

