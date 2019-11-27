package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.exceptions.ItemNotFoundException;
import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.repositorys.UserRepository;
import com.training.SpringBootTask.services.TokenStore;
import com.training.SpringBootTask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.orElseThrow(() -> new ItemNotFoundException("No user found with id - " + id));
        return userOptional.get();
    }

    @Override
    public User findByLogin(String login) {
        List<User> userOptional = userRepository.findUserByLogin(login);
        //userOptional.orElseThrow(() -> new ItemNotFoundException("No user found with login - " + login));
        return userOptional.get(0);
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
        pUser.setId(user.getId());
        pUser.setPassword(user.getPassword());
        return userRepository.save(pUser);
    }

    @Override
    public Optional<User> save(User pUser) {
        pUser.setPassword(bCryptPasswordEncoder.encode(pUser.getPassword()));
        return Optional.of(userRepository.save(pUser));

    }

    @Override
    public User updateUserPhoto(String id, String photoName) {
        User user = findById(id);
        user.setAvatar(photoName);
        return userRepository.save(user);
    }
}
