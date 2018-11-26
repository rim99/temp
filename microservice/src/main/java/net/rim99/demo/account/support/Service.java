package net.rim99.demo.account.support;

import com.google.inject.Module;
import net.rim99.demo.account.support.config.ConfigManager;
import net.rim99.demo.account.support.config.ConfigRegister;
import net.rim99.demo.account.support.guice.GlobalInjector;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.Collection;

public class Service {

    private ResourceConfig resourceConfig;
    private ServerManager serverManager;

    private Service(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
    }

    public void run() {
        serverManager = new ServerManager();
        serverManager.start(resourceConfig);
    }

    public void shutdown() {
        if (serverManager == null) {
            throw new IllegalStateException("Service hasn't started yet");
        }
        serverManager.shutdown();
        GlobalInjector.clear();
        ConfigManager.clear();
    }

    public static class Builder {
        public Service build(ConfigRegister configRegister, ResourceConfig resourceConfig, Collection<Module> modules) {
            ConfigManager.initialize(configRegister);
            GlobalInjector.builder().addModules(modules);
            return new Service(resourceConfig);
        }
    }
}
