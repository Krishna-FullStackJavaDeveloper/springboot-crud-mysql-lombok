package com.crud.example.service;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


//this is multipart file storage service with url
public interface URLFilesStorageService {

    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
