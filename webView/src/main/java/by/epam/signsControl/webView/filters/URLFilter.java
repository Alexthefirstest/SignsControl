package by.epam.signsControl.webView.filters;


import by.epam.signsControl.webView.Constants;
import by.epam.signsControl.webView.controller.RequestParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * store request uri to the session attribute like a {@link Constants#REQUIRED_URI} param
 * <p>
 * check is xss attack
 * <p>
 * direct to main or upload servlet, or to the required resources
 */
@WebFilter(urlPatterns = "/*")
public class URLFilter implements Filter {

    /**
     * logger
     */
    private static final Logger logger = LogManager.getLogger(Filter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (isXSSAttack(httpRequest.getPathInfo()) || isXSSAttack(((HttpServletRequest) request).getRequestURI())) {
            return;
        }

        if (!Pattern.matches("[^.]*", httpRequest.getRequestURI())) {
            logger.warn("if, catch: " + httpRequest.getRequestURI());
            chain.doFilter(request, response);
            return;
        }


        String requestURI = httpRequest.getRequestURI();

        logger.info("filter start URI: " + requestURI);

        request.setAttribute(Constants.REQUIRED_URI, requestURI);

        logger.info("filter middle");

        if ("upload".equals(RequestParser.getCommandFromURI(httpRequest))) {
            chain.doFilter(request, response);
        } else {

            request.getRequestDispatcher(Constants.SERVLET_PATH).forward(request, response);
        }

        logger.info("end of filter");
    }

    /*check is xss attack*/
    private boolean isXSSAttack(String path) {

        if (path == null) {
            return false;
        }

        logger.info("is xss: " + path);

        if (path.contains("<") || path.contains(">")) {
            logger.warn("!!!XSS!!!: " + path);

            return true;

        }

        return false;
    }

}
