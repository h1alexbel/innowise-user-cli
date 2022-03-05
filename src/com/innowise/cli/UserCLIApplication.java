package com.innowise.cli;

import com.innowise.cli.command.Command;
import com.innowise.cli.command.CommandFactory;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.OperationUtils;

import java.util.Scanner;

public class UserCLIApplication {

    public static void main(String[] args) throws CommandException {
        new UserCLIApplication().start();
    }

    private void start() throws CommandException {
        setUpConsoleLogs();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            switch (scanner.next()) {
                case OperationUtils.CREATE_USER_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.CREATE_USER_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                case OperationUtils.SAVE_IN_FILE_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.SAVE_IN_FILE_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                case OperationUtils.FIND_ALL_NUMBER -> {
                    Command listCommand = CommandFactory.defineCommand(OperationUtils.SHOW_ALL_USERS_COMMAND_TRIGGER);
                    listCommand.execute(scanner);
                }
                case OperationUtils.FIND_BY_ID_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.FIND_USER_BY_ID_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                case OperationUtils.UPDATE_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.UPDATE_USER_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                case OperationUtils.DELETE_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.DELETE_USER_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                case OperationUtils.ADD_ROLE_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.ADD_ROLE_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                case OperationUtils.ADD_PHONE_NUMBER -> {
                    Command command = CommandFactory.defineCommand(OperationUtils.ADD_PHONE_COMMAND_TRIGGER);
                    command.execute(scanner);
                }
                default -> System.out.println(OperationUtils.THERE_IS_NO_SUCH_COMMAND_MESSAGE);
            }
        }
    }

    private void setUpConsoleLogs() {
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
        System.out.println(OperationUtils.ADD_ROLE_TO_USER);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.ADD_PHONE_NUMBER_TO_USER);
        System.out.println(ConsoleMessagesUtils.CONSOLE_SEPARATOR);
        System.out.println(OperationUtils.ENTER);
    }
}