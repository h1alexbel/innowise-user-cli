package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.util.Scanner;

public class DeleteUserCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println(ConsoleMessagesUtils.ENTER_ID_MESSAGE);
        long id = Long.parseLong(scanner.next());
        try {
            boolean isDeleted = userService.deleteUserById(id);
            if (isDeleted) {
                System.out.println(ConsoleMessagesUtils.USER_SUCCESSFULLY_DELETED);
            } else {
                System.out.println(ConsoleMessagesUtils.USER_IS_NOT_EXISTS);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
        }
    }
}