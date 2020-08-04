package by.epam.signsControl.webView.controller;

import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.controller.commands.CommandName;
import by.epam.signsControl.webView.exceptions.AccessException;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * upload servlet
 * <p>
 * supply image file in get method
 * upload files by {@link by.epam.signsControl.webView.controller.commands.Command} in post method
 */
@WebServlet(name = "uploadServlet", urlPatterns = "/upload/*")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    /**
     * logger
     */
    private static final Logger logger = LogManager.getLogger(UploadServlet.class);

    /**
     * {@link CommandProvider} instance
     */
    private static CommandProvider commandProvider = CommandProvider.getCommandProvider();

    /**
     * return image from data base by output stream with 'image/gif' response contend type
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet get");

        int signID = Integer.parseInt(req.getParameter("id"));

        logger.info(signID);

        resp.setContentType("image/gif");

        try {

            byte[] picture = ServiceFactory.getINSTANCE().getPddSignsControlService().getPicture(signID);

            OutputStream os = resp.getOutputStream();
            os.write(picture);
            os.flush();
            os.close();
        } catch (ServiceException e) {
            logger.warn(e);
        }


        logger.info("inside servlet get");
    }

    /**
     * set picture to the data base by {@link Command}
     *
     * @param req  {@link HttpServletRequest}
     * @param resp {@link HttpServletResponse}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet post");


        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {

            if (CommandName.valueOf(RequestParser.getSecondCommandFromURI(req)) == CommandName.SET_SIGN_IMAGE) {

                commandProvider.getCommand(CommandName.SET_SIGN_IMAGE.toString()).execute(req, resp);

            } else {

                logger.warn("wrong set image uri" + req.getAttribute(Constants.REQUIRED_URI));
                commandProvider.getCommand(CommandName.WRONG_COMMAND.toString()).execute(req, resp);
            }


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

        logger.info("inside servlet post2");
    }
}
