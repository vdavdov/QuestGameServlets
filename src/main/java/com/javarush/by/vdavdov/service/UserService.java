package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.entity.User;

import java.util.Optional;

public interface UserService {
    void create(User user);

    Optional<User> get(long id);
}
