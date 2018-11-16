package net.rim99.demo.account.support.config;

import net.rim99.demo.account.support.config.impl.YamlConfigReader;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private static volatile ConfigManager manager = null;

    public static synchronized void initialize(ConfigRegister register) {
        if (manager == null) {
            ConfigManager manager1 = new ConfigManager(register);
            manager1.prepare();
            manager = manager1;
        }
    }

    public static ConfigManager get() {
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
        String key = clazz.getName();
        Object data = configCache.get(key);
        if (data != null) {
            return (T) data;
        }

        throw new RuntimeException("Could not find config, type: " + key);
    }
}
