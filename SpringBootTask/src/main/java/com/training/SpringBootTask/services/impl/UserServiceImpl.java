package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.exceptions.ItemNotFoundException;
import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.repositorys.UserRepository;
import com.training.SpringBootTask.services.ImageStore;
import com.training.SpringBootTask.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private ImageStore imageStore;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ImageStoreInFileSystem imageStoreInFileSystem) {
        this.userRepository = userRepository;
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
        pUser.setPassword(bCryptPasswordEncoder.encode(pUser.getPassword()));
        return Optional.of(userRepository.save(pUser));
    }

    @Override
    public User updateUserPhoto(String id, MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID().toString() + ".jpg";
        imageStore.save(file, imageName);
        User user = findById(id);
        user.setAvatar(imageName);
        return userRepository.save(user);
    }
}
