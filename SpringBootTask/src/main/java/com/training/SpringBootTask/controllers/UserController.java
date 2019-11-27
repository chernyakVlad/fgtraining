package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable String  id) {
        return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/login/{login}")
    public ResponseEntity<User> getByLogin(@PathVariable String login) {
        return new ResponseEntity<User>(userService.findByLogin(login), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.update(id, user), HttpStatus.CREATED);
    }
}
