package com.innowise.cli.service;

import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.RoleType;
import com.innowise.cli.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user) throws ServiceException;

    Optional<User> findUserById(Long id) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    List<User> findAll() throws ServiceException;

    boolean deleteUserById(Long id) throws ServiceException;

    void addPhoneNumberToUser(PhoneNumber phoneNumber, User user) throws ServiceException;

    void addRoleToUser(RoleType roleType, User user) throws ServiceException;
}