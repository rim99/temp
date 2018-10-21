package net.rim99.demo.account.repository;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcTest extends RepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(JdbcTest.class);

    @Test
    public void test() {
        logger.info("Good");
        new Thread(() -> logger.info("another thread")).start();
    }
}
