package com.innowise.cli.service.impl;

import com.innowise.cli.dao.impl.UserDaoImpl;
import com.innowise.cli.exception.DaoException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.file.SavableInFile;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.Role;
import com.innowise.cli.model.RoleType;
import com.innowise.cli.model.User;
import com.innowise.cli.service.UserService;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService, SavableInFile<User> {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final int MAX_USER_PHONES_SIZE = 3;

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveInFile(User user, Path path) throws IOException {
        if (user != null && path != null) {
            try (ObjectOutputStream objectOutputStream =
                         new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                objectOutputStream.writeObject(user);
            }
        }
    }

    @Override
    public void saveUser(User user) throws ServiceException {
        if (user != null) {
            try {
                userDao.save(user);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        if (user != null) {
            try {
                userDao.update(user);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
    }

    @Override
    public boolean deleteUserById(Long id) throws ServiceException {
        boolean result = false;
        if (id != null) {
            try {
                result = userDao.deleteById(id);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
        return result;
    }

    @Override
    public boolean isUserHasRightsToAddPhoneNumber(User user) throws ServiceException {
        boolean flag = false;
        if (user != null && user.getId() != null) {
            if (getUserPhoneSize(user.getId()) >= MAX_USER_PHONES_SIZE) {
                return false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Optional<User> getById(Long id) throws ServiceException {
        if (id != null) {
            try {
                return userDao.findUserById(id);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> list() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public boolean isUserWithEmailExists(String email) throws ServiceException {
        boolean result = false;
        try {
            Optional<User> maybeUser = userDao.findByEmail(email);
            if (maybeUser.isPresent()) {
                result = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
        }
        return result;
    }

    @Override
    public List<Role> getUserRoles(Long userId) throws ServiceException {
        if (userId != null) {
            try {
                return userDao.findRolesByUserId(userId);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<PhoneNumber> getUserPhoneNumbers(Long userId) throws ServiceException {
        if (userId != null) {
            try {
                return userDao.findPhoneNumbersByUserId(userId);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void addPhoneNumberToUser(PhoneNumber phoneNumber, User user) throws ServiceException {
        if (phoneNumber != null && user != null) {
            try {
                userDao.addPhoneNumberToUser(phoneNumber, user);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
    }

    @Override
    public boolean isUserHasRightsToAddRole(User user, RoleType roleTypeToAdd) throws ServiceException {
        boolean result = false;
        if (user != null && roleTypeToAdd != null) {
            if (isUserHasSuperAdminRole(user)) {
                return result;
            } else if (isUserHasSameRole(user, roleTypeToAdd)) {
                return result;
            } else if (isUserHasUserRole(user) && roleTypeToAdd.equals(RoleType.CUSTOMER)) {
                return result;
            } else if (isUserHasCustomerRole(user) && roleTypeToAdd.equals(RoleType.USER)) {
                return result;
            } else if (isUserHasAdminRole(user) && roleTypeToAdd.equals(RoleType.PROVIDER)) {
                return result;
            } else if (isUserHasProviderRole(user) && roleTypeToAdd.equals(RoleType.ADMIN)) {
                return result;
            } else {
                result = true;
            }
        } else {
            return result;
        }
        return result;
    }

    @Override
    public void addRoleToUser(Role role, User user) throws ServiceException {
        if (role != null && user != null) {
            try {
                userDao.addRoleToUser(role, user);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
    }

    private int getUserPhoneSize(Long userId) throws ServiceException {
        try {
            return userDao.findPhoneNumbersByUserId(userId).size();
        } catch (DaoException e) {
            throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
        }
    }

    private boolean isUserHasSameRole(User user, RoleType roleType) throws ServiceException {
        return getUserRoles(user.getId())
                .stream()
                .anyMatch(role -> role.getRoleType()
                        .equals(roleType));
    }

    private boolean isUserHasSuperAdminRole(User user) throws ServiceException {
        return getUserRoles(user.getId())
                .stream()
                .anyMatch(role -> role.getRoleType()
                        .equals(RoleType.SUPER_ADMIN));
    }

    private boolean isUserHasAdminRole(User user) throws ServiceException {
        return getUserRoles(user.getId())
                .stream()
                .anyMatch(role -> role.getRoleType()
                        .equals(RoleType.ADMIN));
    }

    private boolean isUserHasUserRole(User user) throws ServiceException {
        return getUserRoles(user.getId())
                .stream()
                .anyMatch(role -> role.getRoleType()
                        .equals(RoleType.USER));
    }

    private boolean isUserHasCustomerRole(User user) throws ServiceException {
        return getUserRoles(user.getId())
                .stream()
                .anyMatch(role -> role.getRoleType()
                        .equals(RoleType.CUSTOMER));
    }

    private boolean isUserHasProviderRole(User user) throws ServiceException {
        return getUserRoles(user.getId())
                .stream()
                .anyMatch(role -> role.getRoleType()
                        .equals(RoleType.PROVIDER));
    }
}