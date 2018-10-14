package net.rim99.demo.account.startup.config;

import java.util.HashMap;
import java.util.Map;

class ConfigRegister {
    Map<String, String> configPaths;

    ConfigRegister() {
        this.configPaths = new HashMap<>();
    }

    <T extends Config> void register(String filePath, Class<T> clazz) {
        String key = clazz.getCanonicalName();
        String savedPath = configPaths.put(key, filePath);
        if (savedPath != null) {
            throw new IllegalArgumentException(
                    "Class " + key + " has registered by file: " + savedPath);
        }
    }
}
