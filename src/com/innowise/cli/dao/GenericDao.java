package com.innowise.cli.dao;

import com.innowise.cli.exception.DaoException;
import com.innowise.cli.model.ModelAttribute;
import com.innowise.cli.model.User;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T extends ModelAttribute, K> {

    T save(T entity) throws DaoException;

    void update(T newEntityValue) throws DaoException;

    Optional<User> findUserById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    boolean deleteById(K key) throws DaoException;
}