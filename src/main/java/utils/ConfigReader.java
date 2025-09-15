package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader() .getResourceAsStream("config/config.properties")) {
            if (input == null) {
                throw new RuntimeException("config/config.properties not found in resources!");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getKey(String key) {
        return properties.getProperty(key);
    }
}
