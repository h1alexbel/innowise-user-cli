package com.innowise.cli.util;

public final class ValidationErrorUtils {

    public static final String EMAIL_IS_NULL_CODE = "email.is.null";
    public static final String EMAIL_IS_NULL_MESSAGE = "Email can not be null!";
    public static final String EMAIL_NOT_VALID_MESSAGE = "email.invalid";
    public static final String EMAIL_NOT_VALID_CODE = "Email is invalid!";
    public static final String PHONE_NUMBER_IS_NULL_CODE = "phone.number.is.null";
    public static final String PHONE_NUMBER_IS_NULL_MESSAGE = "Phone number can not be null!";
    public static final String PHONE_NUMBER_NOT_VALID_CODE = "phone.number.invalid";
    public static final String PHONE_NUMBER_NOT_VALID_MESSAGE = "Phone number is invalid!";

    private ValidationErrorUtils() {
        throw new UnsupportedOperationException();
    }
}