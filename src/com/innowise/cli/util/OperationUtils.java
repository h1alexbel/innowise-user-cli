package com.innowise.cli.util;

public final class OperationUtils {

    public static final String WELCOME_MESSAGE = "Welcome to USER-CLI, You have some options:";
    public static final String CREATE_USER_NUMBER = "1";
    public static final String SAVE_IN_FILE_NUMBER = "2";
    public static final String FIND_ALL_NUMBER = "3";
    public static final String FIND_BY_ID_NUMBER = "4";
    public static final String UPDATE_NUMBER = "5";
    public static final String DELETE_NUMBER = "6";
    public static final String ADD_ROLE_NUMBER = "7";
    public static final String ADD_PHONE_NUMBER = "8";
    public static final String CREATE_USER = "1.Create user";
    public static final String SAVE_IN_FILE = "2.Save user in file";
    public static final String FIND_ALL = "3.Find all users";
    public static final String FIND_BY_ID = "4.Find user by id";
    public static final String UPDATE = "5.Update user";
    public static final String DELETE = "6.Delete user";
    public static final String ADD_ROLE_TO_USER = "7.Add role to user";
    public static final String ADD_PHONE_NUMBER_TO_USER = "8.Add phone to user";
    public static final String ENTER = "Enter your choice [just type 1,2 or etc]";
    public static final String THERE_IS_NO_SUCH_COMMAND_MESSAGE = "Is not command, try again!";
    public static final String CREATE_USER_COMMAND_TRIGGER = "CREATE_USER";
    public static final String SAVE_IN_FILE_COMMAND_TRIGGER = "SAVE_IN_FILE";
    public static final String SHOW_ALL_USERS_COMMAND_TRIGGER = "SHOW_ALL_USERS";
    public static final String FIND_USER_BY_ID_COMMAND_TRIGGER = "FIND_USER_BY_ID";
    public static final String UPDATE_USER_COMMAND_TRIGGER = "UPDATE_USER";
    public static final String DELETE_USER_COMMAND_TRIGGER = "DELETE_USER";
    public static final String ADD_ROLE_COMMAND_TRIGGER = "ADD_ROLE";
    public static final String ADD_PHONE_COMMAND_TRIGGER = "ADD_PHONE";

    private OperationUtils() {
        throw new UnsupportedOperationException();
    }
}