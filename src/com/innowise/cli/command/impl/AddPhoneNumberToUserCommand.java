package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.User;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;
import com.innowise.cli.validation.ValidationResult;
import com.innowise.cli.validation.impl.PhoneNumberValidator;

import java.util.Optional;
import java.util.Scanner;

public class AddPhoneNumberToUserCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final PhoneNumberValidator phoneNumberValidator = PhoneNumberValidator.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println(ConsoleMessagesUtils.ENTER_ID_MESSAGE);
        long id = Long.parseLong(scanner.next());
        try {
            Optional<User> maybeUser = userService.getById(id);
            if (maybeUser.isPresent()) {
                User user = maybeUser.get();
                boolean hasRights = userService.isUserHasRightsToAddPhoneNumber(user);
                if (hasRights) {
                    String phoneNumber = scanner.next();
                    PhoneNumber number = PhoneNumber.builder()
                            .phoneNumber(phoneNumber)
                            .user(user)
                            .build();
                    ValidationResult validationResult = phoneNumberValidator.validate(number);
                    if (validationResult.isValid()) {
                        userService.addPhoneNumberToUser(number, user);
                    } else {
                        System.out.println(ConsoleMessagesUtils.CREDENTIALS_FAILED
                                           + validationResult.getErrors()
                                           + ConsoleMessagesUtils.TRY_AGAIN_MESSAGE);
                    }
                } else {
                    System.out.println(ConsoleMessagesUtils.NO_RIGHTS_TO_ADD_PHONE_MESSAGE);
                }
            } else {
                System.out.println(ConsoleMessagesUtils.USER_IS_NOT_EXISTS);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
        }
    }
}