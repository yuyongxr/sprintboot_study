import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    @Test
    public void testQuick(){
//        LOGGER.fatal("fatal");
        LOGGER.error("error");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        
    }
}
