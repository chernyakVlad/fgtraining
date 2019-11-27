package com.training.SpringBootTask.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStore {
    public void save(MultipartFile file, String fileName) throws IOException;
    public String getFileAsBase64String(String fileName) throws IOException;
    
}
