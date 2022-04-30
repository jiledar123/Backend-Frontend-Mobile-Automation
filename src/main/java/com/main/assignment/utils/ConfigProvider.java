package com.main.assignment.utils;

import java.io.*;
import java.util.Properties;

/**
 * The type Config provider.
 */
public final class ConfigProvider {

    private static Properties prop;

    /**
     * Instantiates a new Config provider.
     */
    public ConfigProvider() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     * @throws IOException the io exception
     */
    public static Properties getInstance() throws IOException {
        if (prop == null) {
            prop = loadProperties();
            return prop;
        } else {
            return prop;
        }
    }

    /**
     * Gets string.
     *
     * @param key the key
     * @return the string
     * @throws IOException the io exception
     */
    public static String getString(String key) throws IOException {
        return getInstance().getProperty(key);
    }

    private static Properties loadProperties() throws IOException {

        FileReader reader = null;
        Properties prop = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = null;
        try {
             is = loader.getClass().getResourceAsStream("/project.properties");
            prop = new Properties();
            prop.load(is);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            is.close();
        }
        return prop;
    }
}
