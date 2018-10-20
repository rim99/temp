package net.rim99.demo.account.startup;

import net.rim99.demo.account.support.ServerManager;
import net.rim99.demo.account.support.config.ConfigRegister;

public class Config {

    public static ConfigRegister register() {
        ConfigRegister register = new ConfigRegister();
        register.register("config/Server.yml", ServerManager.Settings.class);
        return register;
    }
}
