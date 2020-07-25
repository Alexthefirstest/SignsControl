package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.RequestParser;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeUserFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");
        //qq

        IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();


        int id = Integer.parseInt(request.getParameter("id"));
        int role = Integer.parseInt(request.getParameter("role"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int organisation = Integer.parseInt(request.getParameter("organisation"));
        String info = request.getParameter("info");
        String blockStr = request.getParameter("block");

        if (request.getParameter("setName") != null) {
            logger.info("name" + name);
            usersControllerService.setName(id, name);
        }

        if (request.getParameter("setSurname") != null) {
            logger.info("surn" + surname);
            usersControllerService.setSurname(id, surname);
        }
        if (blockStr != null) {

            logger.info("block" + blockStr);

            if ("true".equals(blockStr)) {
                logger.info("true");
                usersControllerService.setBlock(id, true);
            } else if ("false".equals(blockStr)) {
                logger.info("false");
                usersControllerService.setBlock(id, false);
            }

        }
        if (request.getParameter("setInfo") != null) {
            logger.info("inf " + info);
            usersControllerService.setInfo(id, info);

        }
        if (request.getParameter("setOrganisation") != null) {
            logger.info("org" + organisation);
            usersControllerService.setOrganisation(id, organisation);
        }
        if (request.getParameter("setRole") != null) {
            logger.info("rol" + role);
            usersControllerService.setRole(id, role);
        }


        response.sendRedirect(request.getContextPath() + "/user_profile/"+id);
    }
}