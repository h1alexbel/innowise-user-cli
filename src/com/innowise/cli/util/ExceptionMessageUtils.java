package com.innowise.cli.util;

public final class ExceptionMessageUtils {

    public static final String DRIVER_ERROR = "DriverManager can not load driver";
    public static final String PROPERTIES_ERROR = "Data base can not load properties!";
    public static final String CONNECTION_ERROR = "Driver can not build connection";

    private ExceptionMessageUtils() {
        throw new UnsupportedOperationException();
    }
}