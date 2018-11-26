package net.rim99.demo.account.support.config;

import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ConfigManagerTest {

    static class NotRegistered extends Config {
    }

    @Test
    public void should_throw_exception_when_class_is_not_registerd() {
        ConfigManager.initialize(new ConfigRegister());
        ConfigManager manager = ConfigManager.get();
        assertThat(catchThrowable(() ->
                manager.getConfig(NotRegistered.class)))
                .isInstanceOf(RuntimeException.class);
    }

    @After
    public void teardown() {
        ConfigManager.clear();
    }
}
