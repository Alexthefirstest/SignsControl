package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.filters.URLFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "Main Servlet", urlPatterns = "/app")
@MultipartConfig
public class CommandController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(CommandController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet get");

        process(req, resp);

        logger.info("finish servlet get");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet post");
//        logger.info(req.getParameter("name"));
//        logger.info(req.getParameter("pdd_section"));
//        logger.info(req.getParameter("description"));
//        logger.info(req.getParts());
//        logger.info(req.getPart("picture"));
        logger.info("inside servlet post2");
        process(req, resp);


        logger.info("finish servlet post");
    }

    private static CommandProvider commandProvider = CommandProvider.getCommandProvider();

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        try {

            commandProvider.getCommand(RequestParser.getCommandFromURI(req)).execute(req, resp);
        } catch (Exception ex) {
            logger.warn(ex);

        }
    }

}
