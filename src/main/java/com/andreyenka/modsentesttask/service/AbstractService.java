package com.andreyenka.modsentesttask.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface AbstractService<K, T> {

    T create(@Valid T dto);

    T findById(K id);

    List<T> findAll();

    T update(@Valid T dto);

    void delete(K id);
}
