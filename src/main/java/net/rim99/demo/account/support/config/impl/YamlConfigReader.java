package net.rim99.demo.account.support.config.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.rim99.demo.account.support.config.Config;
import net.rim99.demo.account.support.config.ConfigReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class YamlConfigReader implements ConfigReader {

    private String readFile(String path) throws IOException {
        String filepath = this.getClass().getClassLoader().getResource(path).getPath();
        if (filepath == null) {
            throw new RuntimeException("filepath is null");
        }
        Path p = new File(filepath).toPath();
        BufferedReader content = Files.newBufferedReader(p);
        StringBuilder raw = new StringBuilder();
        String line;
        while ((line = content.readLine()) != null) {
            raw.append(line);
            raw.append("\n");
        }
        return raw.toString();

    }

    @Override
    public <T extends Config> T readFrom(String path, Class<T> as) {

        T config;
        try {
            String content = readFile(path);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            config = mapper.readValue(content, as);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to read config from: \"" + path
                    + "\" as type: " + as.getCanonicalName());
        }
        return config;
    }
}
