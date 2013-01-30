package ru.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Author:      Dmitriy E. Nosov <br>
 * Date:        14.11.12, 18:54 <br>
 * Company:     <br>
 * Revision:    \$Id$ <br>
 * Description: <br>
 */

public class FindTest {
    final static Logger logger = LoggerFactory.getLogger(FindTest.class);

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 1000; i++) {
            logger.info("1!11111sdasdadadadasdasdadasd1 " + Math.random());
//            final IllegalArgumentException e = new IllegalArgumentException("r");
//            logger.error("exception", e);

        }
    }

}
