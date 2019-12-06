package com.training.SpringBootTask.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStore {
    public Resource save(MultipartFile file, String fileName) throws IOException;
    public String getAsBase64String(String fileName) throws IOException;
    public Resource getAsResource(String fileName) throws IOException;
}
