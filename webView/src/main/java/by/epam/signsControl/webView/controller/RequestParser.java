package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * get parts from request
 */
public class RequestParser {

    /**
     * private constructor
     */
    private RequestParser() {
    }

    /**
     * return command using {@link Constants#REQUIRED_URI}attribute
     *
     * @param request {@link HttpServletRequest}
     * @return ContextPath/(return this) or "mani_page" if can't find command
     */
    public static String getCommandFromURI(HttpServletRequest request) {

        Pattern pattern = Pattern.compile(request.getContextPath() + "/([^/]+)");
        Matcher matcher = pattern.matcher(request.getAttribute(Constants.REQUIRED_URI).toString());

        return matcher.find() ? matcher.group(1) : Constants.MAIN_PAGE;
    }

    /**
     * return command using {@link Constants#REQUIRED_URI}attribute
     *
     * @param request {@link HttpServletRequest}
     * @return ContextPath/.../(return this) or null if can't find command
     */
    public static String getSecondCommandFromURI(HttpServletRequest request) {

        Pattern pattern = Pattern.compile(request.getContextPath() + "/[^/]+/([^/]+)");
        Matcher matcher = pattern.matcher(request.getAttribute(Constants.REQUIRED_URI).toString());

        return matcher.find() ? matcher.group(1) : null;
    }


}
