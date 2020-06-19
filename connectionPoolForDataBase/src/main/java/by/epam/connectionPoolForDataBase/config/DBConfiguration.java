package by.epam.connectionPoolForDataBase.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * DBConfiguration class
 * <p>
 * Transfer connection pool configuration parameters
 * from db.properties file to class variables
 *
 * @author Bulgak Alexander
 */
public class DBConfiguration {

    /**
     * field logger log4j2
     */
    private static final Logger logger = LogManager.getLogger(DBConfiguration.class);

    /**
     * this class instance
     */
    private static DBConfiguration instance = new DBConfiguration();

    /**
     * field data base url to connect
     */
    private String dbUrl;

    /**
     * field data base user to connect
     */
    private String dbUser;

    /**
     * field data base password to connect
     */
    private String dbPassword;

    /**
     * field init connection pool size with default value
     */
    private int initPoolSize = 12;

    /**
     * field max connection pool size with default value
     */
    private int maxPoolSize = 30;

    /**
     * field connection pool increase step with default value
     */
    private int poolIncreaseStep = 2;

    /*
     * private constructor
     * start {@link DBConfiguration#initPropert}
     */
    private DBConfiguration() {
        initProperties();
        logger.info("constructor done successfully");
    }

    /*
     * init properties from db.properties file
     * replace default values in case of conflict between default and file variables
     *
     * @throws Error if can't find db.properties file or exception inside method occurred
     */
    private void initProperties() {

        logger.info("init started");

        try (InputStream inputStream = DBConfiguration.class.getResourceAsStream("/db.properties")) {

            Properties properties = new Properties();

            if (inputStream == null) {
                throw new Error("Can't find db.properties");
            }

            properties.load(inputStream);

            dbUrl = properties.getProperty("dbUrl");
            dbUser = properties.getProperty("dbUser");
            dbPassword = properties.getProperty("dbPassword");

            if (properties.getProperty("initPoolSize") != null) {
                logger.info("init pool size load from db.properties started");
                initPoolSize = Integer.parseInt(properties.getProperty("initPoolSize"));
                logger.info("init pool size load from db.properties finished");
            }

            if (properties.getProperty("maxPoolSize") != null) {
                logger.info("init max pool size load from db.properties started");
                maxPoolSize = Integer.parseInt(properties.getProperty("maxPoolSize"));
                logger.info("init max pool size load from db.properties finished");
            }

            if (properties.getProperty("poolIncreaseStep") != null) {
                logger.info("init pool increase step load from db.properties started");
                poolIncreaseStep = Integer.parseInt(properties.getProperty("poolIncreaseStep"));
                logger.info("init pool increase step load from db.properties finished");
            }

            logger.info("init finished");

        } catch (IOException ex) {
            logger.error("error during initialisation db properties", ex);
            throw new Error("Properties has not been loaded", ex);
        }

    }

    /**
     * @return {@link DBConfiguration#instance}
     */
    public static DBConfiguration getInstance() {
        return instance;
    }

    /**
     * @return {@link DBConfiguration#dbUrl}
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * @return {@link DBConfiguration#dbUser}
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * @return {@link DBConfiguration#dbPassword}
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @return {@link DBConfiguration#initPoolSize}
     */
    public int getInitPoolSize() {
        return initPoolSize;
    }

    /**
     * @return {@link DBConfiguration#maxPoolSize}
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @return {@link DBConfiguration#poolIncreaseStep}
     */
    public int getPoolIncreaseStep() {
        return poolIncreaseStep;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("{dbUrl='").append(dbUrl).append('\'');
        sb.append(", dbUser='").append(dbUser).append('\'');
        sb.append(", dbPassword='").append(dbPassword).append('\'');
        sb.append(", initPoolSize=").append(initPoolSize);
        sb.append(", maxPoolSize=").append(maxPoolSize);
        sb.append(", poolIncreaseStep=").append(poolIncreaseStep);
        sb.append('}');
        return sb.toString();
    }

}
