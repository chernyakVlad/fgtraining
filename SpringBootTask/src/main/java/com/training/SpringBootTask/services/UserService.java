package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> save(User user);
    public Optional<User> findByLogin(String login);
}
