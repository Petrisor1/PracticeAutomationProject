package common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties config;

    public ConfigLoader(String path) {
        config = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(path);
            if (input == null) {
                throw new IOException("Cannot find configuration file");
            }
            config.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load properties", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getProperty(String key) {
        return config.getProperty(key);
    }


}

