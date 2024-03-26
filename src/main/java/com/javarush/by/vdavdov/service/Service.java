package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface Service {
    void create(User user);

    Optional<User> get(long id);
}
