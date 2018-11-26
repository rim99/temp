package net.rim99.demo.account.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.rim99.demo.account.support.config.Config;
import net.rim99.demo.account.support.config.ConfigManager;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

class ServerManager {

    private String uri;
    private int port;
    private Server server;

    ServerManager() {
        Settings settings = ConfigManager.get().getConfig(Settings.class);
        this.uri = settings.uri;
        this.port = settings.port;
    }

    void start(ResourceConfig resourceConfig) {
        URI baseUri = UriBuilder.fromUri(uri).port(port).build();
        server = JettyHttpContainerFactory.createServer(baseUri, resourceConfig);
    }

    void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to shutdown, detail: " + e.getMessage());
        }
    }

    public static class Settings extends Config {

        @JsonProperty()
        private String uri;

        @JsonProperty()
        private int port;

        public Settings() {
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }
}
