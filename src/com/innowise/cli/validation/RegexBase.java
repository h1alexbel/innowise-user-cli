package com.innowise.cli.validation;

public final class RegexBase {

    public static final String PHONE_REGEX = "^375 ?\\d{9}$";
    public static final String EMAIL_REGEX = "^[A-Za-z.]+\\w+@[A-Za-z]+\\.+[A-Za-z]{1,}$";

    private RegexBase() {
        throw new UnsupportedOperationException();
    }
}