package com.example.springserver.file_api;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileSytemStorage {
    void init();
    String saveFile(MultipartFile file);
    String saveFile(MultipartFile file, String username);
    Resource loadFile(String fileName);
    Resource loadFile(String fileName, String username);
}
