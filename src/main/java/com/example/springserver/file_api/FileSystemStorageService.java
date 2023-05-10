package com.example.springserver.file_api;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
            //throw new FileStorageException("Could not create upload dir!");
        }
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (Exception e) {
            e.printStackTrace();
            //throw new FileStorageException("Could not upload file");
        }
        return null;
    }

    @Override
    public String saveFile(MultipartFile file, String username) {
        try {
            String fileName = file.getOriginalFilename();
            Path pFile;
            if(Files.exists(Paths.get(this.dirLocation + "\\"+ username ))){
                pFile = Paths.get(this.dirLocation + "\\"+ username ).resolve(fileName);
            }else{
                pFile = Files.createDirectory(Paths.get(this.dirLocation + "\\"+ username )).resolve(fileName);
            }
            Files.copy(file.getInputStream(), pFile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                //throw new FileNotFoundException("Could not find file");
            }
        }
        catch (MalformedURLException e) {
            //throw new FileNotFoundException("Could not download file");
        }
        return null;
    }

    @Override
    public Resource loadFile(String fileName, String username) {
        try {
            Path file = Paths.get(this.dirLocation + "\\"+ username).resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                //throw new FileNotFoundException("Could not find file");
            }
        }
        catch (MalformedURLException e) {
            //throw new FileNotFoundException("Could not download file");
        }
        return null;
    }
}