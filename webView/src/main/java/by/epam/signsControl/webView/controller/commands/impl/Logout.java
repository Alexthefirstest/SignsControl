package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout implements Command {

    private static final Logger logger = LogManager.getLogger(Logout.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        request.getSession().invalidate();

        response.sendRedirect(request.getContextPath() + "/");
    }
}
