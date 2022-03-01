package com.innowise.cli.service.impl;

import com.innowise.cli.dao.impl.UserDaoImpl;
import com.innowise.cli.exception.DaoException;
import com.innowise.cli.exception.ServiceException;
import com.innowise.cli.file.SavableInFile;
import com.innowise.cli.model.PhoneNumber;
import com.innowise.cli.model.RoleType;
import com.innowise.cli.model.User;
import com.innowise.cli.service.UserService;
import com.innowise.cli.util.ExceptionMessageUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService, SavableInFile<User> {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

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
    public Optional<User> findUserById(Long id) throws ServiceException {
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
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
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
    public void addRoleToUser(RoleType roleType, User user) throws ServiceException {
        if (roleType != null && user != null) {
            try {
                userDao.addRoleToUser(roleType, user);
            } catch (DaoException e) {
                throw new ServiceException(ExceptionMessageUtils.SERVICE_EXCEPTION_MESSAGE, e);
            }
        }
    }
}