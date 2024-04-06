package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.repository.Repository;
import com.javarush.by.vdavdov.repository.UserRepository;

import java.util.Optional;

public class UserUserServiceImpl implements UserService {
    public static Repository userRepository;
    private static UserService userService;

    public UserUserServiceImpl() {
        userRepository = new UserRepository();
    }
    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserUserServiceImpl();
        }
        return userService;
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    public Optional<User> get(long id) {
        return userRepository.get(id);
    }
}
