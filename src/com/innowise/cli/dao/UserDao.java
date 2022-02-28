package com.innowise.cli.dao;

import com.innowise.cli.exception.DaoException;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.RoleType;
import com.innowise.cli.model.User;

public interface UserDao extends GenericDao<User, Long> {

    PhoneNumber addPhoneNumberToUser(PhoneNumber phoneNumber, User user) throws DaoException;

    void addRoleToUser(RoleType roleType, User user) throws DaoException;
}