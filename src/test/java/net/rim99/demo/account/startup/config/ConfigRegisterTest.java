package net.rim99.demo.account.startup.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigRegisterTest {

    class TestConfig extends Config { }

    @Test
    void should_not_register_class_which_was_already_registered() {

        Class<? extends Config> configClass = TestConfig.class;
        ConfigRegister register = new ConfigRegister();
        register.register("file_path_1", configClass);

        assertThrows(RuntimeException.class,
                () -> register.register("file_path_2", configClass));
    }
}
