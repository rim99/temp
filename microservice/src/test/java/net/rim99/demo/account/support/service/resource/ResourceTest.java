package net.rim99.demo.account.support.service.resource;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import io.restassured.RestAssured;
import net.rim99.demo.account.support.Service;
import net.rim99.demo.account.support.ServerManager;
import net.rim99.demo.account.support.config.ConfigManager;
import net.rim99.demo.account.support.config.ConfigRegister;
import net.rim99.demo.account.support.guice.help.Instance;
import net.rim99.demo.account.support.guice.help.InstanceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.LinkedList;

public class ResourceTest {

    static Service testService;

    static ConfigRegister register() {
        ConfigRegister register = new ConfigRegister();
        register.register("config/Server.yml", ServerManager.Settings.class);
        return register;
    }

    @BeforeClass
    public static void setUp() {
        testService = new Service.Builder().build(register(), new ResourceConfig(Hello.class), new LinkedList<Module>() {{
            add(new AbstractModule() {
                @Override
                protected void configure() {
                    bind(Instance.class).to(InstanceImpl.class);
                }
            });
        }});

        ServerManager.Settings settings = ConfigManager.get().getConfig(ServerManager.Settings.class);
        RestAssured.baseURI = settings.getUri();
        RestAssured.port = settings.getPort();
        testService.run();
    }

    @AfterClass
    public static void tearDown() {
        testService.shutdown();
    }

}
