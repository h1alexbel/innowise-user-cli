package com.innowise.cli.util;

public final class ConsoleMessagesUtils {

    public static final String ENTER_EMAIL_WITH_EXAMPLE_MESSAGE = "Enter user's email: [example of email : any@gmail.com]";
    public static final String ENTER_FIRST_NAME_MESSAGE = "Enter user's first name:";
    public static final String ENTER_LAST_NAME_MESSAGE = "Enter user's last name:";
    public static final String CREDENTIALS_FAILED = "Credential failed! caused by:";
    public static final String TRY_AGAIN_MESSAGE = "Try again!";
    public static final String ALL_USERS_MESSAGE = "All users that stored in database: ";
    public static final String CONSOLE_SEPARATOR = "--------------------------";
    public static final String EMAIL_EXISTS_MESSAGE = "User with this email is already exists!";
    public static final String USER_SUCCESSFULLY_DELETED = "User successfully deleted!";
    public static final String USER_IS_NOT_EXISTS = "User with this id is not exists!";
    public static final String ENTER_ID_MESSAGE = "Enter user's id:";
    public static final String NO_RIGHTS_TO_ADD_ROLE_MESSAGE = "You dont have rights to add role";
    public static final String NO_RIGHTS_TO_ADD_PHONE_MESSAGE = "You dont have rights to add phone";

    private ConsoleMessagesUtils() {
        throw new UnsupportedOperationException();
    }
}