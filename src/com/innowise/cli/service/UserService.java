package com.innowise.cli.service;

import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.Role;
import com.innowise.cli.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user) throws ServiceException;

    void updateUser(User user) throws ServiceException;

    Optional<User> getById(Long id) throws ServiceException;

    List<User> list() throws ServiceException;

    List<Role> getUserRoles(Long userId) throws ServiceException;

    List<PhoneNumber> getUserPhoneNumbers(Long userId) throws ServiceException;

    boolean deleteUserById(Long id) throws ServiceException;

    boolean isUserHasRightsToAddPhoneNumber(User user) throws ServiceException;

    void addPhoneNumberToUser(PhoneNumber phoneNumber, User user) throws ServiceException;

    void addRoleToUser(Role role, User user) throws ServiceException;
}