package com.innowise.cli.service;

import com.innowise.cli.model.User;

import java.util.Optional;

public interface UserService extends GenericService<User, String> {

    Optional<User> findUserByEmail(String email);
}