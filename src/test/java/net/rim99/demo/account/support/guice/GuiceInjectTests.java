package net.rim99.demo.account.support.guice;

import com.google.inject.AbstractModule;
import io.restassured.RestAssured;
import net.rim99.demo.account.startup.Config;
import net.rim99.demo.account.support.ServerManager;
import net.rim99.demo.account.support.config.ConfigManager;
import net.rim99.demo.account.support.guice.help.Instance;
import net.rim99.demo.account.support.guice.help.InstanceImpl;
import net.rim99.demo.account.support.guice.help.TestResource;
import org.glassfish.jersey.internal.inject.InjectionManagerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class GuiceInjectTests {

    private static ServerManager serverManager;

    @BeforeClass
    public static void setUp() {
        String uri = "http://localhost/";
        ConfigManager.initialize(Config.register());
        GlobalInjector.builder().addModule(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Instance.class).to(InstanceImpl.class);
            }
        });
        int port = ConfigManager.get()
                .getConfig(ServerManager.Settings.class)
                .getPort();
        RestAssured.baseURI = uri;
        RestAssured.port = port;
        serverManager = new ServerManager();
        serverManager.start(ConfigManager.get(), new ResourceConfig(TestResource.class));
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
        serverManager.shutdown();
        GlobalInjector.clear();
    }

}
