package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.repository.Repository;
import com.javarush.by.vdavdov.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public class UserService implements Service {
    private static Repository userRepository;
    private static Service userService;

    private UserService() {
        userRepository = new UserRepository();
    }
    public static Service getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> get(long id) {
        return userRepository.get(id);
    }
}
