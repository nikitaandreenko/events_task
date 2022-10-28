package com.andreyenka.modsentesttask.repository;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<K, T> {
    T create(T entity);

    Optional<T> findById(K id);

    List<T> findAll();

    T update(T entity);

    boolean delete(K id);
}
