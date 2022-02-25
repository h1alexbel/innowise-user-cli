package com.innowise.cli.dbconnection;

import com.innowise.cli.exception.DataBaseException;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesManager {

    private static final Properties PROPERTIES = new Properties();
    private static final String ROOT = "application.properties";
    private static final String DRIVER_KEY = "db.driver";

    static {
        loadProperties();
        loadDriver();
    }

    private PropertiesManager() {
        throw new UnsupportedOperationException();
    }

    private static void loadProperties() {
        try (InputStream stream = PropertiesManager
                .class
                .getClassLoader()
                .getResourceAsStream(ROOT)) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new DataBaseException(ExceptionMessageUtils.PROPERTIES_ERROR, e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName(PropertiesManager.getPropertyByKey(DRIVER_KEY));
        } catch (ClassNotFoundException e) {
            throw new DataBaseException(ExceptionMessageUtils.DRIVER_ERROR, e);
        }
    }

    public static String getPropertyByKey(String key) {
        return PROPERTIES.getProperty(key);
    }
}