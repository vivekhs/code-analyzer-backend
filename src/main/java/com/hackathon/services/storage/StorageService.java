package com.hackathon.services.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();

    String store(MultipartFile file, String fileName, String userId);

    void deleteDirectory(String filePath);

    void deleteAll();

    void createDirectory(String directoryName);

}
