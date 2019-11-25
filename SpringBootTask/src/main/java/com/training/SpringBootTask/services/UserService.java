package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findByLogin(String login);
    public User findById(Long id);
    public List<User> getAll();
    public User update(long id, User pUser);
    public Optional<User> save(User user);
}
