package net.rim99.demo.account.startup;

import net.rim99.demo.account.resources.Hello;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ServerManager {

    private String uri;
    private int port;
    private Server server;

    public ServerManager(String uri, int port) {
        this.uri = uri;
        this.port = port;
    }

    public void start() {
        URI baseUri = UriBuilder.fromUri(uri).port(port).build();
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
