package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.models.User;
import com.training.SpringBootTask.services.ImageStore;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.ImageStoreInFileSystem;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users/{id}/photo")
public class UserProfilePhotoRestController {
    private UserService userService;
    private ImageStore imageStore;

    @Autowired
    public UserProfilePhotoRestController(UserServiceImpl userServiceImpl, ImageStoreInFileSystem imageStoreInFileSystem) {
        this.userService = userServiceImpl;
        this.imageStore = imageStoreInFileSystem;
    }

    @GetMapping
    public ResponseEntity<Resource> download(@PathVariable("id") String userId) throws Exception {
        User user = userService.findById(userId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageStore.getAsResource(user.getAvatar()));
    }

    @GetMapping(value = "/asBase64")
    public ResponseEntity<String> downloadAsBase64(@PathVariable("id") String userId) throws Exception {
        User user = userService.findById(userId);
        return ResponseEntity.ok(imageStore.getAsBase64String(user.getAvatar()));
    }

    @PostMapping
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file,
                                       @PathVariable("id") String userId) throws IOException {
        return ResponseEntity.ok(userService.updateUserPhoto(userId, file));
    }
}
