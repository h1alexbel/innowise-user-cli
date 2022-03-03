package com.innowise.cli.dao;

import com.innowise.cli.exception.DaoException;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.Role;
import com.innowise.cli.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

    PhoneNumber addPhoneNumberToUser(PhoneNumber phoneNumber, User user) throws DaoException;

    Role addRoleToUser(Role role, User user) throws DaoException;

    List<Role> findRolesByUserId(Long userId) throws DaoException;

    List<PhoneNumber> findPhoneNumbersByUserId(Long userId) throws DaoException;
}