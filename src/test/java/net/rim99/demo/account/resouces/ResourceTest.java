package net.rim99.demo.account.resouces;

import io.restassured.RestAssured;
import net.rim99.demo.account.startup.Config;
import net.rim99.demo.account.support.ServerManager;
import net.rim99.demo.account.support.config.ConfigManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ResourceTest {

    static ServerManager manager;

    @BeforeClass
    public static void setUp() {
        String uri = "http://localhost/";
        ConfigManager.initialize(Config.register());
        int port = ConfigManager.get()
                .getConfig(ServerManager.Settings.class)
                .getPort();
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        manager = new ServerManager();
        manager.start(ConfigManager.get());
    }

    @AfterClass
    public static void tearDown() {
        manager.shutdown();
    }

}
