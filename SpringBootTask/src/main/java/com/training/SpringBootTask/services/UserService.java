package com.training.SpringBootTask.services;

import com.training.SpringBootTask.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByLogin(String login);
    User findById(String id);
    List<User> getAll();
    Optional<User> save(User user);
    User update(String id, User pUser);
    void delete(String id);
    User updateUserPhoto(String id, MultipartFile file) throws IOException;
}
