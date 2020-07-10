package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddPDDSignFormHandler implements Command {
    private static final Logger logger = LogManager.getLogger(AddPDDSignFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        logger.info("inside execute");



        //logger.info(request.getPart("picture").getInputStream());
        logger.info(request.getParameter("pdd_section"));
        logger.info(request.getParameter("pdd_sign"));
        logger.info(request.getParameter("pdd_kind"));
        logger.info(request.getParameter("name"));
        logger.info(request.getParameter("description"));
        logger.info(request.getParameter("picture"));
//        logger.info(request.getPart("picture"));

        logger.info("after form");

//        try {
//            ServiceFactory.getINSTANCE().getStandardSizesControlService().
//                    addStandardSize(Integer.parseInt(request.getParameter("size")),
//                            request.getParameter("info"));
//
//        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
//            logger.warn(e);
//        }


        response.sendRedirect(request.getContextPath() + "/pdd_signs");

    }
}
