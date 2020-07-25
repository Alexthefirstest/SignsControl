package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class UsersList implements Command {

    private static final Logger logger = LogManager.getLogger(UsersList.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        //только для админов, для админов организации - только их  юзеры, для 1 - все+сортировка по организации
        //только для админов, для админов организации - только их  юзеры

        IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();
        User[] users;

        String orgId = request.getParameter("id");

        if (orgId == null) {

            users = usersControllerService.getUsers();

        } else {
            users = usersControllerService.getUsers(Integer.parseInt(orgId));
        }


        request.setAttribute("users", users);
        request.setAttribute("organisations", ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations());
        logger.info(Arrays.toString(ServiceFactory.getINSTANCE().getOrganisationsControllerService().getOrganisations()));


        logger.info("inside execute");
        //qq

        request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/users_list.jsp").forward(request, response);
    }
}


