package org.example.car.aplication.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfiguration {
    private final String filePath;
    private Properties properties;

    public AppConfiguration(String filePath) {
        this.filePath = filePath;
    }

    public void load() throws IOException {
        if (properties != null)
            throw new IllegalStateException("Application configuration already loaded");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            properties = new Properties();
            properties.load(inputStream);
        }
    }

    public String getString(String propertyName, String defaultValue) {
        return properties.getProperty(propertyName, defaultValue);
    }

    public String getString(String propertyName) {
        return getString(propertyName, null);
    }

    public int getInt(String propertyName, int defaultValue) {
        String valueAsText = getString(propertyName, "" + defaultValue);

        try {
            return Integer.parseInt(valueAsText);
        } catch (NumberFormatException _) {
            return defaultValue;
        }
    }
}
