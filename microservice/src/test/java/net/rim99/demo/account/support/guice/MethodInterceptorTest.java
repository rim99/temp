package net.rim99.demo.account.support.guice;

import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import net.rim99.demo.account.support.guice.help.AlwaysTrue;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.hk2.annotations.Contract;
import org.jvnet.hk2.annotations.Service;
import org.jvnet.hk2.guice.bridge.api.HK2IntoGuiceBridge;

import static org.junit.Assert.assertEquals;

public class MethodInterceptorTest {

    ServiceLocator locator;

    @Before
    public void setUp() {
        locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.addClasses(locator, TestObj.class);
    }

    static class AlwaysTrueInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            invocation.proceed();
            return true;
        }
    }

    @Contract
    interface ReturnFalse {
        boolean returnFalse();
    }

    @Service
    static class TestObj implements ReturnFalse {
        @AlwaysTrue
        public boolean returnFalse() {
            return false;
        }
    }

    @Test
    public void assertGuiceAopSuitableForHk2service() {

        assertEquals(false, locator.getService(TestObj.class).returnFalse());

        ReturnFalse testObj = locator.create(TestObj.class);
        assertEquals(false, testObj.returnFalse());

        Module hk2Context = new HK2IntoGuiceBridge(locator);
        Injector parent = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bindInterceptor(Matchers.any(),
                        Matchers.annotatedWith(AlwaysTrue.class),
                        new AlwaysTrueInterceptor());
            }
        });
        Injector child = parent.createChildInjector(hk2Context);
        ReturnFalse testObj2 = child.getInstance(TestObj.class);
        assertEquals(true, testObj2.returnFalse());
    }

}
