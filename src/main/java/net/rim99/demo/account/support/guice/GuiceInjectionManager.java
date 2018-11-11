package net.rim99.demo.account.support.guice;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.internal.inject.*;
import org.jvnet.hk2.guice.bridge.api.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

public class GuiceInjectionManager implements InjectionManager {

    private InjectionManager delegate;

    public static GuiceInjectionManager create(InjectionManager manager) {
        // transfer from Guice to HK2
        ServiceLocator s = HK2Helper.getServiceLocator(manager);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(s);
        GuiceIntoHK2Bridge guiceBridge = s.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(GlobalInjector.getInjector());
        return new GuiceInjectionManager(manager);
    }

    private GuiceInjectionManager(InjectionManager manager) {
        this.delegate = manager;
    }

    @Override
    public void register(Binding binding) {
        this.delegate.register(binding);
    }

    @Override
    public void register(Iterable<Binding> descriptors) {
        this.delegate.register(descriptors);
    }

    @Override
    public void register(Binder binder) {
        this.delegate.register(binder);
    }

    @Override
    public void register(Object provider) throws IllegalArgumentException {
        this.delegate.register(provider);
    }

    @Override
    public boolean isRegistrable(Class<?> clazz) {
        return this.delegate.isRegistrable(clazz);
    }

    @Override
    public void completeRegistration() {
        this.delegate.completeRegistration();
    }

    @Override
    public void shutdown() {
        this.delegate.shutdown();
    }

    @Override
    public <T> T createAndInitialize(Class<T> createMe) {
        return this.delegate.createAndInitialize(createMe);
    }

    @Override
    public <T> List<ServiceHolder<T>> getAllServiceHolders(Class<T> contractOrImpl, Annotation... qualifiers) {
        return this.delegate.getAllServiceHolders(contractOrImpl, qualifiers);
    }

    @Override
    public <T> T getInstance(Class<T> contractOrImpl, Annotation... qualifiers) {
        return this.delegate.getInstance(contractOrImpl, qualifiers);
    }

    @Override
    public <T> T getInstance(Class<T> contractOrImpl) {
        return this.delegate.getInstance(contractOrImpl);
    }

    @Override
    public <T> T getInstance(Type contractOrImpl) {
        return this.delegate.getInstance(contractOrImpl);
    }

    @Override
    public <T> T getInstance(Class<T> contractOrImpl, String classAnalyzer) {
        return this.delegate.getInstance(contractOrImpl, classAnalyzer);
    }

    @Override
    public Object getInstance(ForeignDescriptor foreignDescriptor) {
        return this.delegate.getInstance(foreignDescriptor);
    }

    @Override
    public ForeignDescriptor createForeignDescriptor(Binding binding) {
        return this.delegate.createForeignDescriptor(binding);
    }

    @Override
    public <T> List<T> getAllInstances(Type contractOrImpl) {
        return this.delegate.getAllInstances(contractOrImpl);
    }

    @Override
    public void inject(Object injectMe) {
        this.delegate.inject(injectMe);
    }

    @Override
    public void inject(Object injectMe, String classAnalyzer) {
        this.delegate.inject(injectMe, classAnalyzer);
    }

    @Override
    public void preDestroy(Object preDestroyMe) {
        this.delegate.preDestroy(preDestroyMe);
    }

    InjectionManager getDelegate() {
        return delegate;
    }
}
