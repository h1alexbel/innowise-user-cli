package com.innowise.cli.validation.impl;

import com.innowise.cli.util.ValidationErrorUtils;
import com.innowise.cli.validation.Error;
import com.innowise.cli.validation.RegexBase;
import com.innowise.cli.validation.ValidationResult;
import com.innowise.cli.validation.Validator;

public class EmailValidator implements Validator<String> {

    private static final EmailValidator INSTANCE = new EmailValidator();

    private EmailValidator() {

    }

    public static EmailValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult validate(String emailToValidate) {
        ValidationResult validationResult = new ValidationResult();
        if (emailToValidate != null) {
            if (!isEmailValid(emailToValidate)) {
                validationResult.add(Error.of(
                        ValidationErrorUtils.EMAIL_NOT_VALID_CODE,
                        ValidationErrorUtils.EMAIL_NOT_VALID_MESSAGE));
            }
        } else {
            validationResult.add(Error.of(
                    ValidationErrorUtils.EMAIL_IS_NULL_CODE,
                    ValidationErrorUtils.EMAIL_IS_NULL_MESSAGE));
        }
        return validationResult;
    }

    private boolean isEmailValid(String email) {
        return email.matches(RegexBase.EMAIL_REGEX);
    }
}