package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * forvard to the login form jsp
 */
public class Login implements Command {

    private static final Logger logger = LogManager.getLogger(Login.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
