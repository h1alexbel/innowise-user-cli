package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.model.User;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;
import com.innowise.cli.validation.ValidationResult;
import com.innowise.cli.validation.impl.EmailValidator;

import java.util.Scanner;

public class UpdateUserCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println(ConsoleMessagesUtils.ENTER_ID_MESSAGE);
        long id = Long.parseLong(scanner.next());
        System.out.println(ConsoleMessagesUtils.ENTER_EMAIL_WITH_EXAMPLE_MESSAGE);
        String email = scanner.next();
        ValidationResult validationResult = emailValidator.validate(email);
        if (validationResult.isValid()) {
            try {
                if (!userService.isUserWithEmailExists(email)) {
                    System.out.println(ConsoleMessagesUtils.ENTER_FIRST_NAME_MESSAGE);
                    String firstName = scanner.next();
                    System.out.println(ConsoleMessagesUtils.ENTER_LAST_NAME_MESSAGE);
                    String lastName = scanner.next();
                    User userToUpdate = User.builder()
                            .id(id)
                            .email(email)
                            .firstName(firstName)
                            .lastName(lastName)
                            .build();
                    userService.updateUser(userToUpdate);
                } else {
                    System.out.println(ConsoleMessagesUtils.EMAIL_EXISTS_MESSAGE
                                       + ConsoleMessagesUtils.TRY_AGAIN_MESSAGE);
                }
            } catch (ServiceException e) {
                throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
            }
        } else {
            System.out.println(ConsoleMessagesUtils.CREDENTIALS_FAILED
                               + validationResult.getErrors()
                               + ConsoleMessagesUtils.TRY_AGAIN_MESSAGE);
        }
    }
}