package net.rim99.demo.account.support.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class GlobalInjector {

    private static Injector injector;

    public static Injector getInjector() {
        assertInjectorCreated();
        return injector;
    }

    static Injector getInjector(Module module) {
        assertInjectorCreated();
        return injector.createChildInjector(module);
    }

    public static <T> T getInstance(Class<T> clazz) {
        assertInjectorCreated();
        return injector.getInstance(clazz);
    }

    private static void assertInjectorCreated() {
        if (injector == null) {
            initInjector();
        }
    }

    private static synchronized void initInjector() {
        if (injector != null) {
            return;
        }
        Builder.get().build();
    }

    /**
     * only for test teardown usage
     */
    @Deprecated
    public static void clear() {
        injector = null;
    }

    public static Builder builder() {
        return Builder.get();
    }

    public static class Builder {

        private static Builder builder = new Builder();

        Collection<Module> moduleCollection;

        private Builder() {
            moduleCollection = Collections.synchronizedCollection(new LinkedList<>());
        }

        private static Builder get() {
            return builder;
        }

        public Builder addModule(Module module) {
            moduleCollection.add(module);
            return this;
        }

        public Builder addModules(Collection<Module> modules) {
            moduleCollection.addAll(modules);
            return this;
        }

        private void build() {
            injector = Guice.createInjector(moduleCollection);
        }
    }
}
