package net.rim99.demo.account.resouces;

import io.restassured.RestAssured;
import net.rim99.demo.account.startup.ServerManager;
import net.rim99.demo.account.startup.config.ConfigManager;
import net.rim99.demo.account.startup.config.data.ServerConfigData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResourceTest {

    private ServerManager manager;

    @BeforeAll
    void setUp() {
        String uri = "http://localhost/";
        int port = ConfigManager
                .getManager()
                .getConfig(ServerConfigData.class)
                .getPort();
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        manager = new ServerManager();
        manager.start();
    }

    @AfterAll
    void tearDown() {
        manager.shutdown();
    }

}
