package net.rim99.demo.account.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.rim99.demo.account.support.config.Config;
import net.rim99.demo.account.support.config.ConfigManager;
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

    public void start(ConfigManager manager, ResourceConfig resourceConfig) {
        URI baseUri = UriBuilder.fromUri(uri).port(manager.getConfig(Settings.class).getPort()).build();
        server = JettyHttpContainerFactory.createServer(baseUri, resourceConfig);
    }

    public void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to shutdown, detail: " + e.getMessage());
        }
    }

    public static class Settings extends Config {

        @JsonProperty
        private int port;

        public Settings() {
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

    }
}
