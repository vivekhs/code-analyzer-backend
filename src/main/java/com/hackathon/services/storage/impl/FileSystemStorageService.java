package com.hackathon.services.storage.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.exception.storage.StorageException;
import com.hackathon.models.analyzerproperties.AnalyzerProperties;
import com.hackathon.services.storage.StorageService;

@Service
public class FileSystemStorageService implements StorageService {

    @Autowired
    private AnalyzerProperties properties;

    @Override
    public String store(MultipartFile file, String fileName, String userId) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + fileName);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Path filePath = getFilePath(userId, fileName);

                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                return filePath.toString();
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
    }

    private Path getFilePath(String userId, String fileName) {
        createDirectory(userId);
        return Paths.get(properties.getCodesDirectory(), userId, fileName);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(properties.getCodesDirectory()).toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(Paths.get(properties.getCodesDirectory()));
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void deleteDirectory(String fileDirectory) {
        Path directoryPath = Paths.get(fileDirectory);
        FileSystemUtils.deleteRecursively(directoryPath.toFile());
    }

    @Override
    public void createDirectory(String directoryName) {
        File directory = new File(properties.getCodesDirectory() + "/" + directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
