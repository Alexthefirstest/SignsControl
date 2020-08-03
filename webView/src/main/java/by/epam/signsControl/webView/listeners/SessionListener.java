package by.epam.signsControl.webView.listeners;

import by.epam.signsControl.webView.Constants;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * set {@link Constants#USER_ANONYM_ROLE} when session started
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * set anonym role
     *
     * @param se super param
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(Constants.USER_ROLE, Constants.USER_ANONYM_ROLE);
    }

}
