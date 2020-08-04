package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.exceptions.AccessException;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * mani servlet
 * <p>
 * direct to the commands
 */
@WebServlet(name = "Main Servlet", urlPatterns = "/app")
public class CommandController extends HttpServlet {

    /**
     * logger
     */
    private static final Logger logger = LogManager.getLogger(CommandController.class);

    /**
     * {@link CommandProvider} instance
     */
    private static CommandProvider commandProvider = CommandProvider.getCommandProvider();

    /**
     * Direct request to the command by {@link by.epam.signsControl.webView.Constants#REQUIRED_URI} param
     * <p>
     * set response and request character encoding UTF-8
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet get");

        process(req, resp);

        logger.info("finish servlet get");

    }

    /**
     * Direct request to the command by {@link by.epam.signsControl.webView.Constants#REQUIRED_URI} param
     * <p>
     * set response and request character encoding UTF-8
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet post");

        process(req, resp);


        logger.info("finish servlet post");
    }

    /*
     * Direct request to the command by {@link by.epam.signsControl.webView.Constants#REQUIRED_URI} param
     * <p>
     * set response and request character encoding UTF-8
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {

            commandProvider.getCommand(RequestParser.getCommandFromURI(req)).execute(req, resp);

        } catch (AccessException ex) {

            logger.warn("wrong access", ex);

            req.getRequestDispatcher("/WEB-INF/error_pages/access_forbidden.jsp").forward(req, resp);

        } catch (CommandControllerValidationException ex) {

            logger.warn("validation exception", ex);

            req.setAttribute("message", ex.getMessage());

            req.getRequestDispatcher("/WEB-INF/error_pages/validation_exception.jsp").forward(req, resp);

        } catch (CommandControllerException ex) {
            logger.warn("!SERIOUS EXCEPTION!", ex);
            req.getRequestDispatcher("/WEB-INF/error_pages/errors.jsp").forward(req, resp);
        }
    }

}
