package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetSignAddInfo implements Command {


    private static final Logger logger = LogManager.getLogger(GetSignAddInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        ServiceFactory sf = ServiceFactory.getINSTANCE();

        try {

            Direction[] directions = sf.getDirectionsControlService().getPointDirectionsSignListID(request.getParameter("pointCoordinates"));
            StandardSize[] standardSizes = sf.getStandardSizesControlService().getStandardSizes();
            Sign[] pddSigns = sf.getPddSignsControlService().getPddSigns();

            String result = ResponseCreator.createAddPointInfoJSON(directions, standardSizes, pddSigns);

            logger.info(result);

            response.getWriter().write(result);

        } catch (ServiceException e) {
            logger.warn(e);
        }
    }
}
