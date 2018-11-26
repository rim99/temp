package net.rim99.demo.account.support.repository;

import com.google.inject.Injector;
import net.rim99.demo.account.support.guice.GlobalInjector;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;

public class GuiceObjectFactory extends DefaultObjectFactory {

    private volatile Injector injector;

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        T object = super.create(type, constructorArgTypes, constructorArgs);
        if (injector == null) initInjector();
        injector.injectMembers(object);
        return object;
    }

    private synchronized void initInjector() {
        if (injector != null) return;
        injector = GlobalInjector.getInjector();
    }
}
