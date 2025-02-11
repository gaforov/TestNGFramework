package com.hrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {
    public static Properties properties;

    /**
     * This method will read from properties file
     *
     * @param filePath
     */
    public static void readProperties(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will retrieve data from properties file
     *
     * @param key
     * @return String value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
