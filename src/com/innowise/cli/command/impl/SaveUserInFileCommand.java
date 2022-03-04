package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.model.User;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;
import com.innowise.cli.validation.ValidationResult;
import com.innowise.cli.validation.impl.EmailValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class SaveUserInFileCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final EmailValidator emailValidator = EmailValidator.getInstance();
    private static final String FILE_TO_WRITE = "resources/user-expose.txt";

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println(ConsoleMessagesUtils.ENTER_EMAIL_WITH_EXAMPLE_MESSAGE);
        String email = scanner.next();
        ValidationResult validationResult = emailValidator.validate(email);
        if (validationResult.isValid()) {
            System.out.println(ConsoleMessagesUtils.ENTER_FIRST_NAME_MESSAGE);
            String firstName = scanner.next();
            System.out.println(ConsoleMessagesUtils.ENTER_LAST_NAME_MESSAGE);
            String lastName = scanner.next();
            User user = User.builder()
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            try {
                userService.saveInFile(user, Path.of(FILE_TO_WRITE));
            } catch (IOException e) {
                throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
            }
        } else {
            System.out.println(ConsoleMessagesUtils.CREDENTIALS_FAILED
                               + validationResult.getErrors()
                               + ConsoleMessagesUtils.TRY_AGAIN_MESSAGE);
        }
    }
}