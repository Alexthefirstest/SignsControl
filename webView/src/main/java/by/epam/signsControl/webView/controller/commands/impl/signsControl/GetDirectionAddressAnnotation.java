package by.epam.signsControl.webView.controller.commands.impl.signsControl;

import by.epam.signsControl.bean.Direction;
import by.epam.signsControl.bean.MapPoint;
import by.epam.signsControl.service.IDirectionsControlService;
import by.epam.signsControl.service.ILocalSignsControlService;
import by.epam.signsControl.service.IMapPointsControlService;
import by.epam.signsControl.service.exceptions.ServiceException;
import by.epam.signsControl.service.factory.ServiceFactory;
import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetDirectionAddressAnnotation implements Command {


    private static final Logger logger = LogManager.getLogger(GetDirectionAddressAnnotation.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute1");

        response.setContentType("text/plain");
        logger.info("inside execute2");
        try {

            MapPoint mp = ServiceFactory.getINSTANCE().getMapPointsControlService().getMapPoint(Integer.parseInt(request.getParameter("signList")));
            logger.info("inside execute3");
            String resultJSON = "{\"address\"" + ":\"" + mp.getAddresses().get(0) + "\",\"annotation\":\"" + mp.getAnnotations().get(0) + "\"}";

            logger.info(resultJSON);

            response.getWriter().write(resultJSON);

        } catch (ServiceException e) {
            logger.warn(e);
        }
    }
}