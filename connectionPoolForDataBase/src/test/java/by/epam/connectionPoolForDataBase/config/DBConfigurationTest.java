package by.epam.connectionPoolForDataBase.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBConfigurationTest {

    private static DBConfiguration configs;

    private static final Logger logger= LogManager.getLogger(DBConfiguration.class);

    @BeforeClass
    public static void beforeClass() {
        System.out.println("DBConfigs test before class");
        logger.info("DBConfigs test before class");
        configs = DBConfiguration.getInstance();
    }

    @Test
    public void testToString() {
        System.out.println("tostring configs test ");
       logger.info("tostring configs test ");
        Assert.assertNotNull(configs.toString());
    }
}