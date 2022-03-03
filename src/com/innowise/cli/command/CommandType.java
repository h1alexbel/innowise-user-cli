package com.innowise.cli.command;

import com.innowise.cli.command.impl.AddPhoneNumberToUserCommand;
import com.innowise.cli.command.impl.AddRoleToUserCommand;
import com.innowise.cli.command.impl.CreateUserCommand;
import com.innowise.cli.command.impl.DeleteUserCommand;
import com.innowise.cli.command.impl.FindUserByIdCommand;
import com.innowise.cli.command.impl.SaveUserInFileCommand;
import com.innowise.cli.command.impl.ShowAllUsersCommand;
import com.innowise.cli.command.impl.ShowUserPhoneNumbersCommand;
import com.innowise.cli.command.impl.ShowUserRolesCommand;
import com.innowise.cli.command.impl.UpdateUserCommand;

public enum CommandType {

    CREATE_USER(new CreateUserCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    SAVE_IN_FILE(new SaveUserInFileCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    SHOW_USER_ROLES(new ShowUserRolesCommand()),
    SHOW_USER_PHONE_NUMBERS(new ShowUserPhoneNumbersCommand()),
    FIND_USER_BY_ID(new FindUserByIdCommand()),
    ADD_ROLE(new AddRoleToUserCommand()),
    ADD_PHONE(new AddPhoneNumberToUserCommand()),
    DELETE_USER(new DeleteUserCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}