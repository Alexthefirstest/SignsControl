package by.epam.signsControl.webView.servlets;

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
//    private static final CommandExecutor commandExecutor = new CommandExecutor();

    private String getCommandFromURI(HttpServletRequest request) {
        logger.warn("g1");
        Pattern pattern = Pattern.compile(request.getContextPath() + "/([^/]+)");
        logger.warn("g2");
        Matcher matcher = pattern.matcher(request.getAttribute(URLFilter.REQUIRED_URI).toString());
        logger.warn("g3");
        return matcher.find() ? matcher.group(1) : "mainPage";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        logger.info("inside servlet");

//        commandExecutor.execute(url); default - main jsp
        logger.warn("mm1 " + req.getAttribute(URLFilter.REQUIRED_URI));

        req.setAttribute("myCommand", getCommandFromURI(req));


        //eto bydet v commandah мб, мб сделать переход на интекс.жсп!!!!!!!!!!!!!!!!!
        //   req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/jsp/main.jsp").forward(req, resp);
        req.getRequestDispatcher("/").forward(req, resp);
        logger.warn("poch ya tut, a?");

    }

}
