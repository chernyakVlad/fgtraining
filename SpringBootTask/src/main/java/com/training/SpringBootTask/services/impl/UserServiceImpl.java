package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.exceptions.ItemNotFoundException;
import com.training.SpringBootTask.models.Role;
import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.repositorys.RoleRepository;
import com.training.SpringBootTask.repositorys.UserRepository;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ImageStoreInFileSystem imageStoreInFileSystem) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.imageStore = imageStoreInFileSystem;
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
        return userRepository.save(user);
    }

    @Override
    public Optional<User> save(User pUser) {
        Set<Role> roles = new HashSet<Role>();
        Role admin = roleRepository.findByRole("ADMIN").get();
        Role user = roleRepository.findByRole("USER").get();
        roles.add(admin);
        roles.add(user);
        pUser.setRoles(roles);
        pUser.setPassword(bCryptPasswordEncoder.encode(pUser.getPassword()));
        return Optional.of(userRepository.save(pUser));
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
}
