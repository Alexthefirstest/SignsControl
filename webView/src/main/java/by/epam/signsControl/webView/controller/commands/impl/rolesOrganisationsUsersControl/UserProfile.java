package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.LoginFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProfile implements Command {

    private static final Logger logger = LogManager.getLogger(UserProfile.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        //только для админов, для админов организации - только их  юзеры, для 1 - все+сортировка по организации
        //только для админов, для админов организации - только их  юзеры
//контроль роли, иначе налл поинтер
        int id = (Integer)request.getSession().getAttribute(LoginFormHandler.USER_ID);
        IUsersControllerService usersControllerService = ServiceFactory.getINSTANCE().getUsersControllerService();

        request.setAttribute("user", usersControllerService.getUser(id));

        logger.info("inside execute");
        //qq

        request.getRequestDispatcher("/WEB-INF/jsp/users_organisations_control/user_profile.jsp").forward(request, response);
    }
}
