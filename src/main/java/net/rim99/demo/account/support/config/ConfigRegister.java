package net.rim99.demo.account.support.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigRegister {
    Map<String, String> configPaths;

    public ConfigRegister() {
        this.configPaths = new HashMap<>();
    }

    public <T extends Config> void register(String filePath, Class<T> clazz) {
        String key = clazz.getName();
        String savedPath = configPaths.put(key, filePath);
        if (savedPath != null) {
            throw new IllegalArgumentException(
                    "Class " + key + " has registered by file: " + savedPath);
        }
    }
}
