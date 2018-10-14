package net.rim99.demo.account.startup.config.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.rim99.demo.account.startup.config.Config;

public class ServerConfigData extends Config {

    @JsonProperty
    private int port;

    public ServerConfigData() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
