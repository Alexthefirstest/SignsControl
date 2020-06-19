package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.filters.URLFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Main Servlet", urlPatterns = "/app")

public class CommandController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(CommandController.class);


    private String getCommandFromURI(HttpServletRequest request) {

        Pattern pattern = Pattern.compile(request.getContextPath() + "/([^/]+)");
        Matcher matcher = pattern.matcher(request.getAttribute(URLFilter.REQUIRED_URI).toString());

        return matcher.find() ? matcher.group(1) : "main_page";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet get");

        process(req, resp);

        logger.info("finish servlet get");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet post");

        process(req, resp);


        logger.info("finish servlet post");
    }

    private static CommandProvider commandProvider = CommandProvider.getCommandProvider();

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            commandProvider.getCommand(getCommandFromURI(req)).execute(req, resp);
        } catch (Exception ex) {
            logger.warn(ex);

        }
    }

}
