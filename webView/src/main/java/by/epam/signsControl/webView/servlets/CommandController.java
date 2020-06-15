//package by.epam.signsControl.webView.servlets;
//
//import by.epam.signsControl.webView.filters.URLFilter;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@WebServlet(name = "Main Servlet", urlPatterns = "/app")
//
//public class CommandController extends HttpServlet {
//
//    private static final Logger logger = LogManager.getLogger(CommandController.class);
////    private static final CommandExecutor commandExecutor = new CommandExecutor();
//
//    private String getCommandFromURI(String uri, String contextPath) {
//
//        Pattern pattern = Pattern.compile(contextPath + "/([^/]+)");
//        Matcher matcher = pattern.matcher(uri);
//
//        return matcher.find() ? matcher.group(1) : "mainPage";
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String contextPath = req.getContextPath();
//
//        logger.info("inside servlet");
//
////        commandExecutor.execute(url); default - main jsp
//        logger.warn("mm1");
//
//        req.setAttribute("myCommand",
//                getCommandFromURI(req.getAttribute(URLFilter.REQUIRED_URI).toString(), contextPath));
//
//        logger.warn(req.getRequestURI());
//
//        //eto bydet v commandah мб сделать переход на интекс.жсп!!!!!!!!!!!!!!!!!
//        req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
//        logger.warn("poch ya tut, a?");
//    }
//
//}
