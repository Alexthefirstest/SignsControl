import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

    static final Logger rootLogger= LogManager.getRootLogger();
    static final Logger classLogger= LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        rootLogger.info("!root log!");
        classLogger.warn("!class log!");
    }

}
