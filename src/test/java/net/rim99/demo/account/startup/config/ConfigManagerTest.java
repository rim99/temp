package net.rim99.demo.account.startup.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigManagerTest {

    static class NotRegistered extends Config { }

    @Test
    void should_throw_exception_when_class_is_not_registerd() {
        ConfigManager manager = ConfigManager.getManager();
        assertThrows(RuntimeException.class,
                () -> manager.getConfig(NotRegistered.class));
    }
}
