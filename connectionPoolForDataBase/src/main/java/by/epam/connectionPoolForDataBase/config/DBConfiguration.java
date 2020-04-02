package by.epam.connectionPoolForDataBase.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfiguration {

    private static final Logger logger = LogManager.getLogger(DBConfiguration.class);

    private DBConfiguration() {
        initProperties();
        logger.info("constructor done successfully");
    }

    private static DBConfiguration Instance = new DBConfiguration();

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int initPoolSize = 12;
    private int maxPoolSize = 30;
    private int poolIncreaseStep = 2;

    private void initProperties() {

        logger.info("init started");

        try (InputStream inputStream = new FileInputStream("./connectionPoolForDataBase/src/main/resources/db.properties")) {

            Properties properties = new Properties();
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
            logger.error("error during initialisation db properties");
            throw new Error("Properties has not been loaded", ex);
        }

    }


    public static DBConfiguration getInstance() {
        return Instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getInitPoolSize() {
        return initPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

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
