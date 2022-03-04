package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.util.Scanner;

public class ShowAllUsersCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        try {
            System.out.println(ConsoleMessagesUtils.ALL_USERS_MESSAGE + userService.list());
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
        }
    }
}