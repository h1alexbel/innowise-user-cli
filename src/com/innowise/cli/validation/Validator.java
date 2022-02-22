package com.innowise.cli.validation;

@FunctionalInterface
public interface Validator<T> {

    ValidationResult validate(T entityToValidate);
}