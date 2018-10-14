package net.rim99.demo.account.startup;

import net.rim99.demo.account.resources.Hello;
import net.rim99.demo.account.startup.config.ConfigManager;
import net.rim99.demo.account.startup.config.data.ServerConfigData;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ServerManager {

    private String uri;
    private Server server;

    public ServerManager() {
        this.uri = "http://localhost";
    }

    public void start() {
        ConfigManager manager = ConfigManager.getManager();
        URI baseUri = UriBuilder.fromUri(uri).port(manager.getConfig(ServerConfigData.class).getPort()).build();
        ResourceConfig config = new ResourceConfig(Hello.class);
        server = JettyHttpContainerFactory.createServer(baseUri, config);
    }

    public void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to shutdown, detail: " + e.getMessage());
        }
    }
}
