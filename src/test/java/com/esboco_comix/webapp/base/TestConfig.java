package com.esboco_comix.webapp.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static Properties properties;

    static {
        try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Falha em carregar config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
