package com.innowise.cli.util;

public final class ExceptionMessageUtils {

    public static final String DRIVER_ERROR = "DriverManager can not load driver";
    public static final String PROPERTIES_ERROR = "Data base can not load properties!";
    public static final String CONNECTION_ERROR = "Driver can not build connection";
    public static final String DAO_EXCEPTION_MESSAGE = "Dao layer exception message";
    public static final String SERVICE_EXCEPTION_MESSAGE = "Service layer exception message";

    private ExceptionMessageUtils() {
        throw new UnsupportedOperationException();
    }
}