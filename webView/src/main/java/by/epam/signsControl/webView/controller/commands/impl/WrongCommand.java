package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WrongCommand implements Command {

    private static final Logger logger = LogManager.getLogger(WrongCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        request.getRequestDispatcher("/WEB-INF/jsp/wrong_request.jsp").forward(request, response);
    }

}