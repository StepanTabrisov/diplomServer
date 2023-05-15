package com.example.springserver.file_api;

import com.example.springserver.dir.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    iFileSystemStorage fileSystemStorage;

    @PostMapping("/upload/{username}")
    public ResponseEntity<FileResponse> uploadSingleFileToDir (@PathVariable String username, @RequestParam("file") MultipartFile file) {
        String upfile = fileSystemStorage.saveFile(file, username);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/download/").path(upfile).toUriString();
        return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(upfile,fileDownloadUri,"File uploaded with success!"));
    }

    @PostMapping("/download/{username}")
    public ResponseEntity<Resource> downloadFileWithBodyAndPathVar(@PathVariable String username, @RequestBody String filename) {
        Resource resource = fileSystemStorage.loadFile(filename.trim().replaceAll("\"",""), username);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    // сохранение списка
    @PostMapping("/save/{username}")
    public ResponseEntity<String> saveDirList (@PathVariable String username, @RequestBody Fields data) {
        fileSystemStorage.saveUserDirList(username, data);
        return ResponseEntity.status(HttpStatus.OK).body("Save");
    }

    // получение списка
    @PostMapping("/load/{username}")
    public Fields loadDirList (@PathVariable String username, @RequestBody String data) {
        return fileSystemStorage.loadUserDirList1(username, data.trim().replaceAll("\"",""));
    }
}