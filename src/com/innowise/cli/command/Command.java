package com.innowise.cli.command;

import com.innowise.cli.exception.CommandException;

import java.util.Scanner;

public interface Command {

    void execute(Scanner scanner) throws CommandException;
}