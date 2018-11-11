package net.rim99.demo.account.resource;

import org.glassfish.jersey.server.ResourceConfig;

public class Resource {
    public static ResourceConfig config() {
        return new ResourceConfig(Hello.class);
    }
}