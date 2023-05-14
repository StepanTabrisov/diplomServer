package com.example.springserver.file_api;

import com.example.springserver.dir.Fields;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface iFileSystemStorage {
    void init();
    String saveFile(MultipartFile file);
    String saveFile(MultipartFile file, String username);
    Resource loadFile(String fileName);
    Resource loadFile(String fileName, String username);
    String createDir(String username);
    String saveUserDirList(String username, Fields data);
    String loadUserDirList(String username, String title);
    Fields loadUserDirList1(String username, String title);

}
