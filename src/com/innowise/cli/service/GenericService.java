package com.innowise.cli.service;

import com.innowise.cli.model.ModelAttribute;

import java.util.List;

public interface GenericService<T extends ModelAttribute, K> {

    T create(T entity);

    void update(T entity);

    List<T> findAll();

    boolean delete(K key);
}