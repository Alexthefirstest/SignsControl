package by.epam.signsControl.webView.filters;


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
import java.io.IOException;
import java.util.regex.Pattern;

//@WebFilter(urlPatterns = "/[^.]*") didn't working
@WebFilter(urlPatterns = "/*")
public class URLFilter implements Filter {


    private static final String SERVLET_PATH = "/app";
    public static final String REQUIRED_URI = "uri";
    private static final Logger logger = LogManager.getLogger(Filter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (!Pattern.matches("/[^.]*", httpRequest.getRequestURI())) {
            logger.info("if");
            chain.doFilter(request, response);
        }

        logger.info("filter start 1");

        logger.info("getServletPath: " + ((HttpServletRequest) request).getServletPath());
        logger.info("URI: " + ((HttpServletRequest) request).getRequestURI());
        logger.info("QUARYString: " + ((HttpServletRequest) request).getQueryString());

        logger.info("filter start 2");

        request.setAttribute(REQUIRED_URI, httpRequest.getRequestURI());

        request.getRequestDispatcher(SERVLET_PATH).forward(request, response);

        logger.info("filter middle" + request + "      " + response);

        logger.info("getServletPath: " + ((HttpServletRequest) request).getServletPath());
        logger.info("URI: " + ((HttpServletRequest) request).getRequestURI());
        logger.info("QUARYString: " + ((HttpServletRequest) request).getQueryString());

        logger.info("end of filter");
    }

    @Override
    public void destroy() {

    }
}
