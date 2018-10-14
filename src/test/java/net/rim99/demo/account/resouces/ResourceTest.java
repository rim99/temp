package net.rim99.demo.account.resouces;

import io.restassured.RestAssured;
import net.rim99.demo.account.startup.ServerManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResourceTest {

    private ServerManager manager;

    @BeforeAll
    void setup() {
        String uri = "http://localhost/";
        int port = 9888;
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        manager = new ServerManager( uri, port);
        manager.start();
    }

    @AfterAll
    void teardown() {
        manager.shutdown();
    }

}
