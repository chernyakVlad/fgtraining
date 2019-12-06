package com.training.SpringBootTask.services.impl;


import com.training.SpringBootTask.services.ImageStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ImageStoreInFileSystem implements ImageStore {
    private final static String ROOT_IMAGES_PATH = "D:\\imgs";
    private File root;
    public ImageStoreInFileSystem() {
        this.root = new File(ROOT_IMAGES_PATH);
    }

    @Override
    public Resource save(MultipartFile file, String fileName) throws IOException {
        Path rootPath = Paths.get(ROOT_IMAGES_PATH);
        Files.copy(file.getInputStream(), rootPath.resolve(fileName));
        return new FileSystemResource(rootPath.resolve(fileName));
    }

    @Override
    public String getAsBase64String(String fileName) throws IOException {
        Path rootPath = Paths.get(ROOT_IMAGES_PATH);
        byte[] bytes = Files.readAllBytes(rootPath.resolve(fileName));
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public Resource getAsResource(String fileName) throws IOException {
        File file = new File(this.root, fileName);
        return new FileSystemResource(file);
    }
}
