package com.javarush.by.vdavdov.repository;

import com.javarush.by.vdavdov.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface Repository {
    void create(User user);

    void update(User user);

    void delete(User user);

    Collection<User> getAll();

    Optional<User> get(long id);

}
