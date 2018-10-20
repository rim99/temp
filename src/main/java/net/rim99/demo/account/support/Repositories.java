package net.rim99.demo.account.support;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Repositories {

    private static Repositories factory;

    public static synchronized void initializeFactory(AbstractModule module) {
        if (factory != null) return;
        factory = new Repositories(module);
    }

    public static Repositories getFactory() {
        return factory;
    }

    private final Injector injector;

    private Repositories(AbstractModule module) {
        this.injector = mybatisInjector(module);
    }

    private Injector mybatisInjector(AbstractModule module) {
        return Guice.createInjector(module);
    }

    public <T> T get(Class<T> clazz) {
        return injector.getInstance(clazz);
    }
}
