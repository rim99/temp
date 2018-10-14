package net.rim99.demo.account.startup.config;

import net.rim99.demo.account.startup.config.data.ServerConfigData;
import net.rim99.demo.account.startup.config.impl.YamlConfigReader;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static ConfigRegister register() {
        ConfigRegister register = new ConfigRegister();
        register.register("config/Server.yml", ServerConfigData.class);
        return register;
    }

    private static ConfigManager manager = null;

    public static ConfigManager getManager() {
        if (manager == null) {
            synchronized (ConfigManager.class) {
                if (manager == null) {
                    manager = new ConfigManager(register());
                    manager.prepare();
                }
            }
        }
        return manager;
    }

    private Map<String, Config> configCache;
    private ConfigRegister register;

    private ConfigManager(ConfigRegister register) {
        this.configCache = new HashMap<>();
        this.register = register;
    }

    private void prepare() {
        ConfigReader reader = new YamlConfigReader();
        for (Map.Entry<String, String> entry : this.register.configPaths.entrySet()) {
            String className = entry.getKey();
            try {
                Class configClazz = Class.forName(className).asSubclass(Config.class);
                Config config = reader.readFrom(entry.getValue(), configClazz);
                configCache.put(className, config);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Fail to find class: " + className);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Config> T getConfig(Class<T> clazz) {
        String key = clazz.getCanonicalName();
        Object data = configCache.get(key);
        if (data != null) {
            return (T) data;
        }

        throw new RuntimeException("Could not find config, type: " + key);
    }
}
