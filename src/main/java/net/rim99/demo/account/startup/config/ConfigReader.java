package net.rim99.demo.account.startup.config;

public interface ConfigReader {
    <T extends Config> T readFrom(String path, Class<T> as);
}
