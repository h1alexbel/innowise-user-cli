package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.model.User;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.util.Optional;
import java.util.Scanner;

public class FindUserByIdCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        long id = Long.parseLong(scanner.next());
        try {
            Optional<User> maybeUser = userService.getById(id);
            if (maybeUser.isPresent()) {
                User user = maybeUser.get();
                System.out.println(user);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
        }
    }
}