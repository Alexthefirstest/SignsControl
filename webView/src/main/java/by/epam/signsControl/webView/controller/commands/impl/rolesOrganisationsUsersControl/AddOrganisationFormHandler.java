package by.epam.signsControl.webView.controller.commands.impl.rolesOrganisationsUsersControl;

import by.epam.rolesOrganisationsUsersController.bean.Organisation;
import by.epam.rolesOrganisationsUsersController.bean.User;
import by.epam.rolesOrganisationsUsersController.service.IOrganisationsControllerService;
import by.epam.rolesOrganisationsUsersController.service.IUsersControllerService;
import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;
import by.epam.rolesOrganisationsUsersController.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.impl.bank.AddBankAccountFormHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrganisationFormHandler implements Command {

    private static final Logger logger = LogManager.getLogger(AddBankAccountFormHandler.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {

        logger.info("inside execute");
        //qq

        IOrganisationsControllerService organisationsControllerService = ServiceFactory.getINSTANCE().getOrganisationsControllerService();


        int role = Integer.parseInt(request.getParameter("role"));
        String name = request.getParameter("name");
        String info = request.getParameter("info");

        logger.info(name + " " + role);
        logger.info(info);

        Organisation organisation = organisationsControllerService.addOrganisation(name, role);

        if (!info.isEmpty()) {
            organisationsControllerService.setInfo(organisation.getId(), info);
        }

        response.sendRedirect(request.getContextPath() + "/organisations");
    }
}