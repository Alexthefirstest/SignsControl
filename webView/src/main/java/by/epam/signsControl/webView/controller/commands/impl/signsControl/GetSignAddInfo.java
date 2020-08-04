package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.Sign;
import by.epam.signsControl.bean.StandardSize;
import by.epam.signsControl.service.exceptions.ServiceValidationException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import by.epam.signsControl.webView.exceptions.CommandControllerException;
import by.epam.signsControl.webView.exceptions.CommandControllerValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * get info for add sign in json format
 */
public class GetSignAddInfo implements Command {


    private static final Logger logger = LogManager.getLogger(GetSignAddInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandControllerException {

        logger.info("inside execute");

        response.setContentType("text/plain");


        ServiceFactory sf = ServiceFactory.getINSTANCE();

        try {

            Direction[] directions = sf.getDirectionsControlService().getPointDirectionsSignListID(request.getParameter("pointCoordinates"));
            StandardSize[] standardSizes = sf.getStandardSizesControlService().getStandardSizes();
            Sign[] pddSigns = sf.getPddSignsControlService().getPddSigns();

            String result = ResponseCreator.createAddPointInfoJSON(directions, standardSizes, pddSigns);

            logger.info(result);

            response.getWriter().write(result);

        } catch (ServiceValidationException e) {
            throw new CommandControllerValidationException(e);
        } catch (by.epam.signsControl.service.exceptions.ServiceException e) {
            throw new CommandControllerException(e);
        }
    }
}
