package net.rim99.demo.account.support.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import io.restassured.RestAssured;
import net.rim99.demo.account.support.Service;
import net.rim99.demo.account.support.ServerManager;
import net.rim99.demo.account.support.config.ConfigManager;
import net.rim99.demo.account.support.config.ConfigRegister;
import net.rim99.demo.account.support.guice.help.Instance;
import net.rim99.demo.account.support.guice.help.InstanceImpl;
import net.rim99.demo.account.support.guice.help.TestResource;
import org.glassfish.jersey.internal.inject.InjectionManagerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class GuiceInjectTests {

    private static Service testService;

    static ConfigRegister register() {
        ConfigRegister register = new ConfigRegister();
        register.register("config/Server.yml", ServerManager.Settings.class);
        return register;
    }

    @BeforeClass
    public static void setUp() {

        testService = new Service.Builder().build(register(), new ResourceConfig(TestResource.class), new LinkedList<Module>() {{
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

    @Test
    public void should_find_custom_factory() {

        ServiceLoader<InjectionManagerFactory> s = ServiceLoader.load(InjectionManagerFactory.class);
        List<InjectionManagerFactory> list = new LinkedList<InjectionManagerFactory>(){{
            s.forEach($ -> add($));
        }};

        assertTrue(list.stream().anyMatch( $ -> $ instanceof GuiceInjectionManagerFactory));
    }

    @Test
    public void should_get_guice_instance_in_resource() {

        String response = get("/test")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        assertThat(GlobalInjector.getInstance(Instance.class), notNullValue());

        assertThat(response, is("hello"));

    }

    @AfterClass
    public static void tearDown() {
        testService.shutdown();
    }

}
