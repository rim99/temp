package net.rim99.demo.account.support.guice;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.inject.hk2.DelayedHk2InjectionManager;
import org.glassfish.jersey.inject.hk2.ImmediateHk2InjectionManager;
import org.glassfish.jersey.internal.inject.InjectionManager;

public class HK2Helper {

    static ServiceLocator getServiceLocator(InjectionManager manager) {
        ServiceLocator locator;
        if (manager instanceof ImmediateHk2InjectionManager) {
            locator = ((ImmediateHk2InjectionManager) manager).getServiceLocator();
        } else if(manager instanceof DelayedHk2InjectionManager) {
            // TODO: if support bridge related operation
            locator = ((DelayedHk2InjectionManager) manager).getServiceLocator();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported InjectionManager type: " + manager.getClass().getName());
        }
        return locator;
    }
}
