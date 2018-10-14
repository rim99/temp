package net.rim99.demo.account.resouces;

import io.restassured.RestAssured;
import net.rim99.demo.account.startup.ServerManager;
import net.rim99.demo.account.startup.config.ConfigManager;
import net.rim99.demo.account.startup.config.data.ServerConfigData;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ResourceTest {

    static ServerManager manager;

    @BeforeClass
    public static void setUp() {
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

    @AfterClass
    public static void tearDown() {
        manager.shutdown();
    }

}
