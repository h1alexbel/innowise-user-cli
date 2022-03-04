package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.OperationUtils;

import java.util.Scanner;

public class DefaultCommand implements Command {

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println("Null is unsupported!");
        System.out.println(OperationUtils.WELCOME_MESSAGE);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.CREATE_USER);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.SAVE_IN_FILE);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.FIND_ALL);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.FIND_BY_ID);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.UPDATE);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.DELETE);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.ENTER);
    }
}