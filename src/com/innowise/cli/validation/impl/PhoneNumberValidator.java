package com.innowise.cli.validation.impl;

import com.innowise.cli.util.ValidationErrorUtils;
import com.innowise.cli.validation.Error;
import com.innowise.cli.validation.RegexBase;
import com.innowise.cli.validation.ValidationResult;
import com.innowise.cli.validation.Validator;

public class PhoneNumberValidator implements Validator<String> {

    private static final PhoneNumberValidator INSTANCE = new PhoneNumberValidator();

    @Override
    public ValidationResult validate(String passwordToValidate) {
        ValidationResult validationResult = new ValidationResult();
        if (passwordToValidate != null) {
            if (!isPhoneNumberValid(passwordToValidate)) {
                validationResult.add(Error.of(
                        ValidationErrorUtils.PHONE_NUMBER_NOT_VALID_CODE,
                        ValidationErrorUtils.PHONE_NUMBER_NOT_VALID_MESSAGE
                ));
            }
        } else {
            validationResult.add(Error.of(
                    ValidationErrorUtils.PHONE_NUMBER_IS_NULL_CODE,
                    ValidationErrorUtils.PHONE_NUMBER_IS_NULL_MESSAGE
            ));
        }
        return validationResult;
    }

    private boolean isPhoneNumberValid(String password) {
        return password.matches(RegexBase.PHONE_REGEX);
    }

    public static PhoneNumberValidator getInstance() {
        return INSTANCE;
    }
}