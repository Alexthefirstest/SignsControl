package by.epam.signsControl.webView.controller;

import by.epam.signsControl.webView.filters.URLFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    private RequestParser() {
    }

    public static String getCommandFromURI(HttpServletRequest request) {

        Pattern pattern = Pattern.compile(request.getContextPath() + "/([^/]+)");
        Matcher matcher = pattern.matcher(request.getAttribute(URLFilter.REQUIRED_URI).toString());

        return matcher.find() ? matcher.group(1) : "main_page";
    }

    public static String getSecondCommandFromURI(HttpServletRequest request) {

        Pattern pattern = Pattern.compile(request.getContextPath() + "/[^/]+/([^/]+)");
        Matcher matcher = pattern.matcher(request.getAttribute(URLFilter.REQUIRED_URI).toString());

        return matcher.find() ? matcher.group(1) : null;
    }


}
