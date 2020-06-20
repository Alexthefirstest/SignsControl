package by.epam.signsControl.webView.controller.commands.impl;

import by.epam.signsControl.webView.controller.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCurrentPoints implements Command {

    private static final Logger logger = LogManager.getLogger(GetCurrentPoints.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("inside execute");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = "{\"type\": \"FeatureCollection\",\"features\": [{\"type\": \"Feature\", \"id\": 0, \"geometry\": {\"type\": \"Point\", \"coordinates\": [53.90, 27.56]}, \"properties\": {\"balloonContent\": \"balloon loading...\", \"clusterCaption\" : \"cluster0\", \"hintContent\": \"hint here\"}} ] }";
        logger.warn(json);
        response.getWriter().write(json);
    }
}
