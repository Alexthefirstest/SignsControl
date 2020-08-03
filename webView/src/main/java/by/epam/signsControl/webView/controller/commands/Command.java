package by.epam.signsControl.webView.controller.commands;


import by.epam.signsControl.webView.exceptions.CommandControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * commands for execution interface
 */
public interface Command {

    /**
     * execute command
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws CommandControllerException, ServletException, IOException;

}
