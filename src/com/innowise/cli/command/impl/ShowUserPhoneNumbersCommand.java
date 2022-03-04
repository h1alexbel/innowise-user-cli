package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.util.Scanner;

public class ShowUserPhoneNumbersCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println(ConsoleMessagesUtils.ENTER_ID_MESSAGE);
        long id = Long.parseLong(scanner.next());
        try {
            System.out.println(userService.getUserPhoneNumbers(id));
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
        }
    }
}