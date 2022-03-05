package com.innowise.cli.command.impl;

import com.innowise.cli.command.Command;
import com.innowise.cli.exception.CommandException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.model.Role;
import com.innowise.cli.model.RoleType;
import com.innowise.cli.model.User;
import com.innowise.cli.service.impl.UserServiceImpl;
import com.innowise.cli.util.ConsoleMessagesUtils;
import com.innowise.cli.util.ExceptionMessageUtils;
import com.innowise.cli.util.RoleUtils;

import java.util.Optional;
import java.util.Scanner;

public class AddRoleToUserCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public void execute(Scanner scanner) throws CommandException {
        System.out.println(ConsoleMessagesUtils.ENTER_ID_MESSAGE);
        long id = Long.parseLong(scanner.next());
        try {
            Optional<User> maybeUser = userService.getById(id);
            if (maybeUser.isPresent()) {
                User user = maybeUser.get();
                String roleTypeSign = scanner.next();
                RoleType roleType = RoleUtils.defineRoleType(roleTypeSign);
                boolean hasRightsToAddRole = userService.isUserHasRightsToAddRole(user, roleType);
                if (hasRightsToAddRole) {
                    Role role = RoleUtils.mapRoleTypeToRole(roleType);
                    role.setUser(user);
                    userService.addRoleToUser(role, user);
                } else {
                    System.out.println(ConsoleMessagesUtils.NO_RIGHTS_TO_ADD_ROLE_MESSAGE + roleType);
                }
            } else {
                System.out.println(ConsoleMessagesUtils.USER_IS_NOT_EXISTS);
            }
        } catch (ServiceException e) {
            throw new CommandException(ExceptionMessageUtils.COMMAND_EXCEPTION_MESSAGE, e);
        }
    }
}