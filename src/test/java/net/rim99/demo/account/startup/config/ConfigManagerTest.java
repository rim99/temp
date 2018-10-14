package net.rim99.demo.account.startup.config;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ConfigManagerTest {

    static class NotRegistered extends Config {
    }

    @Test
    public void should_throw_exception_when_class_is_not_registerd() {
        ConfigManager manager = ConfigManager.getManager();
        assertThat(catchThrowable(() ->
                manager.getConfig(NotRegistered.class)))
                .isInstanceOf(RuntimeException.class);
    }
}
