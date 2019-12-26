package com.training.SpringBootTask.services;

import com.training.SpringBootTask.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User findByLogin(String login);
    User findById(String id);
    List<User> getAll();
    User save(User user);
    User update(String id, User pUser);
    void resetPassword(User user, String newPassword);
    void delete(String id);
    User updateUserPhoto(String id, MultipartFile file) throws IOException;
}
