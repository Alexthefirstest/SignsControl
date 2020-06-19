package by.epam.signsControl.webView.controller.commands;

import by.epam.rolesOrganisationsUsersController.service.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;

}
