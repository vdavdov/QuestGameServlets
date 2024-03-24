package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.repository.Repository;
import com.javarush.by.vdavdov.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public class UserService implements Service {
    private final Repository userRepository;
    public UserService(Repository userRepository) {
        this.userRepository = userRepository;
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
