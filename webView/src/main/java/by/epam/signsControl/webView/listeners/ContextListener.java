package by.epam.signsControl.webView.listeners;

import by.epam.connectionPoolForDataBase.connectionPool.factory.ConnectionPoolFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * initialize connection pool when app starting and destroy when finishing
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * logger
     */
    private Logger logger = LogManager.getLogger(ContextListener.class);

    /**
     * init connection pool
     *
     * @param sce super parameter
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("context listener initialized start");
        ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance();
        logger.info("context listener init finish");
    }

    /**
     * destroy connection pool
     *
     * @param sce super param
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("context listener destroy start");
        ConnectionPoolFactory.getINSTANCE().getConnectionPoolInstance().destroyConnectionPool();
        logger.info("context listener destroy finish");
    }
}
