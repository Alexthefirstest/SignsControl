package by.epam.signsControl.webView.controller;


import by.epam.orders.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "uploadServlet", urlPatterns = "/upload/*")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(UploadServlet.class);
    private static CommandProvider commandProvider = CommandProvider.getCommandProvider();

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
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            logger.warn(e);
        }


        logger.info("inside servlet get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("inside servlet post");


        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {

            commandProvider.getCommand(RequestParser.getSecondCommandFromURI(req)).execute(req, resp);
        } catch (Exception ex) {
            logger.warn(ex);

        }

        logger.info("inside servlet post2");
    }
}
