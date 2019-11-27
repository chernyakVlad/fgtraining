package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findByLogin(String login);
    public User findById(String id);
    public List<User> getAll();
    public User update(String id, User pUser);
    public Optional<User> save(User user);
    public User updateUserPhoto(String id, String photoName);
}
