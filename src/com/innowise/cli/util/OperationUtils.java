package com.innowise.cli.util;

public final class OperationUtils {

    public static final String CREATE_USER = "1.Create user";
    public static final String SAVE_IN_FILE = "2.Save user in file";
    public static final String FIND_ALL = "3.Find all users";
    public static final String FIND_BY_ID = "4.Find user by id";
    public static final String UPDATE = "5.Update user";
    public static final String DELETE = "6.Delete user";
    public static final String ENTER = "Enter your choice";
    public static final String CREDENTIALS_FAILED_MESSAGE = "Credentials are invalid, try again!";

    private OperationUtils() {
        throw new UnsupportedOperationException();
    }
}