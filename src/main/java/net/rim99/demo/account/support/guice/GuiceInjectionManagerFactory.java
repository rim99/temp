package net.rim99.demo.account.support.guice;

import org.glassfish.jersey.inject.hk2.Hk2InjectionManagerFactory;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.InjectionManagerFactory;

import javax.annotation.Priority;

/**
 * The factory relies on {@link Hk2InjectionManagerFactory} as delegate,
 * and create {@link GuiceInjectionManager} together. So it needs to be
 * a little bit prior to {@link Hk2InjectionManagerFactory}
 */
@Priority(11)
public class GuiceInjectionManagerFactory implements InjectionManagerFactory {

    private Hk2InjectionManagerFactory delegate;

    @Override
    public InjectionManager create(Object parent) {

        if (delegate == null) {
            initDelegate();
        }
        if (parent instanceof GuiceInjectionManager) {
            return GuiceInjectionManager.create(delegate.create(((GuiceInjectionManager) parent).getDelegate()));
        }
        return GuiceInjectionManager.create(delegate.create(parent));
    }

    private synchronized void initDelegate() {
        if (delegate != null) {
            return;
        }
        delegate = new Hk2InjectionManagerFactory();
    }
}
