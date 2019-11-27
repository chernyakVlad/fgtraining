package com.training.SpringBootTask.services.impl;


import ch.qos.logback.core.util.FileUtil;
import com.training.SpringBootTask.services.ImageStore;
import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ImageStoreInFileSystem implements ImageStore {
    private final static String ROOT_IMAGES_PATH = "D:\\imgs";

    public ImageStoreInFileSystem() {
        super();
    }

    @Override
    public void save(MultipartFile file, String fileName) throws IOException {
        Path rootPath = Paths.get(ROOT_IMAGES_PATH);
        Files.copy(file.getInputStream(), rootPath.resolve(fileName));
    }

    @Override
    public String getFileAsBase64String(String fileName) throws IOException {
        Path rootPath = Paths.get(ROOT_IMAGES_PATH);
        byte[] bytes = Files.readAllBytes(rootPath.resolve(fileName));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
