package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.entity.UserParameterHistoryObject;
import com.training.SpringBootTask.exception.ItemNotFoundException;
import com.training.SpringBootTask.entity.Role;
import com.training.SpringBootTask.entity.User;
import com.training.SpringBootTask.repository.RoleRepository;
import com.training.SpringBootTask.repository.UserParameterRepository;
import com.training.SpringBootTask.repository.UserRepository;
import com.training.SpringBootTask.services.ImageStore;
import com.training.SpringBootTask.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private ImageStore imageStore;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserParameterRepository userParameterRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ImageStoreInFileSystem imageStoreInFileSystem,
                           UserParameterRepository userParameterRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.imageStore = imageStoreInFileSystem;
        this.userParameterRepository = userParameterRepository;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("No user found with id - " + id));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ItemNotFoundException("No user found with login - " + login));
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if(users.size() <= 0) {
            throw new ItemNotFoundException("No users found");
        }
        return users;
    }

    @Override
    public User update(String id, User pUser) {
        User user = findById(id);
        BeanUtils.copyProperties(pUser, user, "id", "password");
        userParameterRepository.save(new UserParameterHistoryObject(pUser.getWeight(), pUser.getHeight()));
        return userRepository.save(user);
    }

    @Override
    public User save(User pUser) {
        Role userRole = roleRepository.findByRole("USER").get();
        pUser.setRole(userRole);
        String password = pUser.getPassword() != null ? pUser.getPassword() : User.DEFAULT_USER_PASSWORD;
        pUser.setPassword(bCryptPasswordEncoder.encode(password));
        userParameterRepository.save(new UserParameterHistoryObject(pUser.getWeight(), pUser.getHeight()));
        return userRepository.save(pUser);
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("No user found with id - " + id));
        userRepository.delete(user);
    }

    @Override
    public User updateUserPhoto(String id, MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID().toString() + ".jpg";
        Resource resource = imageStore.save(file, imageName);
        User user = findById(id);
        user.setAvatar(resource.getURL().toString());
        return userRepository.save(user);
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
