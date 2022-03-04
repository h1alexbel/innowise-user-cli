package com.innowise.cli.util;

public final class OperationUtils {

    public static final String WELCOME_MESSAGE = "Welcome to USER-CLI, You have some options:";
    public static final String CREATE_USER = "1.Create user";
    public static final String SAVE_IN_FILE = "2.Save user in file";
    public static final String FIND_ALL = "3.Find all users";
    public static final String FIND_BY_ID = "4.Find user by id";
    public static final String UPDATE = "5.Update user";
    public static final String DELETE = "6.Delete user";
    public static final String ADD_ROLE_TO_USER = "7.Add role to user";
    public static final String ADD_PHONE_NUMBER_TO_USER = "8.Add phone to user";
    public static final String SHOW_USER_PHONE_INFO = "9. Show all user phones";
    public static final String SHOW_USER_ROLE_INFO = "10. Show all user roles";
    public static final String ENTER = "Enter your choice [just type 1,2 or etc]";
    public static final String THERE_IS_NO_SUCH_COMMAND_MESSAGE = "Is not command, try again!";

    private OperationUtils() {
        throw new UnsupportedOperationException();
    }
}