package by.epam.signsControl.webView.filters;


import by.epam.signsControl.webView.servlets.CommandController;
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

        logger.info("filter");
        request.setAttribute(REQUIRED_URI, ((HttpServletRequest)request).getRequestURI());
        request.getRequestDispatcher(SERVLET_PATH).forward(request, response);
        logger.info("filter middle");

        logger.info("end of filter");
    }

    @Override
    public void destroy() {

    }
}
