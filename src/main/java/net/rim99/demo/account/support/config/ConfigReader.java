package net.rim99.demo.account.support.config;

public interface ConfigReader {
    <T extends Config> T readFrom(String path, Class<T> as);
}
