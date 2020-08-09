package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceValidationException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.AccessRulesChecker;
import by.epam.signsControl.webView.exceptions.AccessException;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeUserFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        try {
            logger.info("inside execute");
            //qq

            IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();


            int id = Integer.parseInt(request.getParameter("id"));
            int role = Integer.parseInt(request.getParameter("role"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
//            int organisation = Integer.parseInt(request.getParameter("organisation"));
            String info = request.getParameter("info");
            String blockStr = request.getParameter("block");


            User user = ServiceFactory.getINSTANCE().getUsersControllerService().getUser(id);

            if (user == null) {
                throw new CommandControllerValidationException("wrong user id");
            }

            if ((Integer) request.getSession().getAttribute(Constants.USER_ID) == user.getId()){
                throw new AccessException("can't change your own account");
            }


                if (AccessRulesChecker.organisationRoleCheckBool(request, Constants.ADMINISTRATOR_ORGANISATION_ROLE)) {

                    if (user.getOrganisation().getId() != Constants.ADMINISTRATORS_ORGANISATION_ID
                            && user.getRole().getId() != Constants.ADMINISTRATOR_ROLE
                            || user.getOrganisation().getId() == Constants.ADMINISTRATORS_ORGANISATION_ID
                            && !AccessRulesChecker.userRoleCheckBool(request, Constants.ADMINISTRATOR_ROLE)) {
                        logger.warn("wrong action if");
                        throw new AccessException(" wrong role");
                    }

                } else {
                    AccessRulesChecker.userRoleCheck(request, Constants.ADMINISTRATOR_ROLE);
                    if (user.getOrganisation().getId() != (Integer) request.getSession().getAttribute(Constants.ORGANISATION_ID)) {
                        throw new AccessException("wrong role");
                    }
                }


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
//            if (request.getParameter("setOrganisation") != null) {
//                logger.info("org" + organisation);
//                usersControllerService.setOrganisation(id, organisation);
//            }
            if (request.getParameter("setRole") != null) {
                logger.info("rol" + role);
                usersControllerService.setRole(id, role);
            }


            response.sendRedirect(request.getContextPath() + "/user_profile/" + id);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (ServiceException e) {
            throw new CommandControllerException(e);
        }

    }
}