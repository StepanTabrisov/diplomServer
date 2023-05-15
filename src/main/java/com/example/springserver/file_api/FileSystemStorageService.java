package com.example.springserver.file_api;

import com.example.springserver.dir.Fields;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Collections;
import java.util.Objects;

@Service
public class FileSystemStorageService implements iFileSystemStorage {
    private final Path dirLocation;

    //@Autowired
    public FileSystemStorageService(FileUploadProperties fileUploadProperties) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath()
                .normalize();
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(this.dirLocation);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String saveFile(MultipartFile file, String username) {
        try {
            String fileName = file.getOriginalFilename();
            Path pFile = null;
            if(Files.exists(Paths.get(this.dirLocation + "\\"+ username + "\\files"))){
                pFile = Paths.get(this.dirLocation + "\\"+ username + "\\files").resolve(Objects.requireNonNull(fileName));
            }
            Files.copy(file.getInputStream(), pFile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Resource loadFile(String fileName, String username) {
        try {
            Path file = Paths.get(this.dirLocation + "\\"+ username + "\\files").resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) return resource;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // создание папки и файла для сериализации
    // username -> /files (save files) , /data (save dir list)
    @Override
    public String createDir(String username) {
        try {
            Path dir = Files.createDirectory(Paths.get(this.dirLocation + "\\"+ username ));
            Files.createDirectories(Paths.get(dir + "\\"  + "data"));
            Files.createDirectories(Paths.get(dir + "\\"  + "files"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // сохранение списка папок/файлов пользователя
    @Override
    public String saveUserDirList(String username, Fields data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path dir = Paths.get(this.dirLocation + "\\" + username);
            Path file = Paths.get(dir + "\\" + "data" + "\\" + data.tempTitle + ".json").normalize();
            if(!Files.exists(file)) Files.createFile(file);
            objectMapper.writeValue(new File(file.toString()), data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // загрузка списка
    @Override
    public Fields loadUserDirList1(String username, String title) {
        try {
            Path dir = Paths.get(this.dirLocation + "\\" + username);
            Path file = Paths.get(dir + "\\" + "data" + "\\" + title + ".json").normalize();
            if(!Files.exists(file)) return null;
            ObjectMapper objectMapper = new ObjectMapper();
            Fields result = objectMapper.readValue(new File(file.toString()), Fields.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}