package com.training.SpringBootTask.services.impl;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.repositorys.UserRepository;
import com.training.SpringBootTask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<User> save(User pUser) {
        pUser.setPassword(bCryptPasswordEncoder.encode(pUser.getPassword()));
        return Optional.of(userRepository.save(pUser));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
