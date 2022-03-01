package com.nextbasecrm.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    public static Properties properties = new Properties();

    static {

        try {
            // Open the file in Java memory : FileInputStream
            FileInputStream file = new FileInputStream("configuration.properties");
// Load the properties obj using FileInputStream
            properties.load(file);
            // close the file
            file.close();
        } catch (IOException e) {
            System.out.println("File not found in configuration file");
            e.printStackTrace();
        }

    }

    public static String getProperty(String keyWord) {

        return properties.getProperty(keyWord);
    }
}
