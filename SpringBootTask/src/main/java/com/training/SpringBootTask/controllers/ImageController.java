package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.services.ImageStore;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.ImageStoreInFileSystem;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private UserService userService;
    private ImageStore imageStore;

    @Autowired
    public ImageController(UserServiceImpl userServiceImpl, ImageStoreInFileSystem imageStoreInFileSystem) {
        this.userService = userServiceImpl;
        this.imageStore = imageStoreInFileSystem;
    }

    @GetMapping(value = "")
    public ResponseEntity<String> getAsBase64String(@RequestParam("filename") String filename) throws Exception {
        return ResponseEntity.ok(imageStore.getFileAsBase64String(filename));
    }

    @PostMapping(value = "")
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file,
                                       @RequestParam("userId") String userId) throws IOException {
        return ResponseEntity.ok(userService.updateUserPhoto(userId, file));
    }
}
