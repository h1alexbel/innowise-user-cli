package com.innowise.cli.command;

import com.innowise.cli.command.impl.DefaultCommand;

public final class CommandFactory {

    private CommandFactory() {
        throw new UnsupportedOperationException();
    }

    public static Command defineCommand(String commandName) {
        if (commandName != null) {
            return CommandType.valueOf(commandName.toUpperCase()).getCommand();
        }
        return new DefaultCommand();
    }
}