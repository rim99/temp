package net.rim99.demo.account.startup.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class ConfigRegisterTest {

    class TestConfig extends Config { }

    @Test
    public void should_not_register_class_which_was_already_registered() {

        ConfigRegister register = new ConfigRegister();
        register.register("file_path_1", TestConfig.class);


        assertThat(catchThrowable(() ->
                register.register("file_path_2", TestConfig.class)))
                .matches(ex -> ex.getClass().equals(IllegalArgumentException.class), "Exception type is not match");
    }
}
