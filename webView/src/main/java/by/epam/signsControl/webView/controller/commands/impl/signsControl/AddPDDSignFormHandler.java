package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.service.IPDDSignsControlService;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


public class AddPDDSignFormHandler implements Command {
    private static final Logger logger = LogManager.getLogger(AddPDDSignFormHandler.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {


        logger.info("inside execute");


        int section = Integer.parseInt(request.getParameter("pdd_section"));
        int sign = Integer.parseInt(request.getParameter("pdd_sign"));
        String kind = request.getParameter("pdd_kind");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part imagePart = request.getPart("image");
        long imageSize = imagePart.getSize();

        logger.info(imageSize);


        logger.info("after form");

        try {
            IPDDSignsControlService signsControl = ServiceFactory.getINSTANCE().getPddSignsControlService();

            Sign pddSign = signsControl.addSign(section, sign, kind.isEmpty() ? -1 : Integer.parseInt(kind), name, description);

            if (pddSign != null && imageSize > 0) {
                signsControl.setPicture(pddSign.getId(), imagePart.getInputStream(), imageSize);
            }

        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        response.sendRedirect(request.getContextPath() + "/pdd_signs");

    }
}
