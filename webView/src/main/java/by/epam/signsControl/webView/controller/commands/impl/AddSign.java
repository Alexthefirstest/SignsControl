package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddSign implements Command {

    private static final Logger logger = LogManager.getLogger(AddSign.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int role;

        logger.info("inside execute");

        if (1 == (role = (Integer) request.getSession().getAttribute("role"))) {

            ILocalSignsControlService localSignsControlService = ServiceFactory.getINSTANCE().getLocalSignsControlService();

            int signListID = Integer.parseInt(request.getParameter("sign_list"));
            int pddSignID = Integer.parseInt(request.getParameter("pdd_sign"));
            int standardSize = Integer.parseInt(request.getParameter("standard_size"));
            String dateOfAdd = ((request.getParameter("date_of_add")));
            String dateOfRemove = ((request.getParameter("date_of_remove")));
            String annotation = ((request.getParameter("annotation")));


            try {

                if (dateOfAdd.isEmpty() && dateOfRemove.isEmpty()) {


                    localSignsControlService.addSign(signListID, pddSignID, standardSize, annotation);
                } else if (dateOfRemove.isEmpty()) {

                    localSignsControlService.addSign(signListID, pddSignID, standardSize, dateOfAdd.replaceAll("-", "."), annotation);
                } else {

                    localSignsControlService.addSign(signListID, pddSignID, standardSize, dateOfAdd
                            .replaceAll("-", "."), dateOfRemove.replaceAll("-", "."), annotation);
                }


            } catch (ServiceException e) {
                logger.warn(e);
            }

        } else {
            logger.warn("wrong role " + role);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}

