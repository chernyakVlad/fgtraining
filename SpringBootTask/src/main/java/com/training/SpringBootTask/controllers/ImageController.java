package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.services.ImageStore;
import com.training.SpringBootTask.services.UserService;
import com.training.SpringBootTask.services.impl.ImageStoreInFileSystem;
import com.training.SpringBootTask.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam("userId") String userId) throws IOException {
        String imageName = UUID.randomUUID().toString() + ".jpg";
        imageStore.save(file, imageName);
        userService.updateUserPhoto(userId, imageName);
        return ResponseEntity.ok(imageName);
    }
}
