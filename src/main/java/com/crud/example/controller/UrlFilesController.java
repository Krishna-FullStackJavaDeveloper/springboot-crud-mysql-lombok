package com.crud.example.controller;

import com.crud.example.entity.URLFileInfo;
import com.crud.example.message.ResponseMessage;
import com.crud.example.service.URLFilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/users/Url_file")
public class UrlFilesController {

    @Autowired
    URLFilesStorageService urlFilesStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            urlFilesStorageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<URLFileInfo>> getListFiles() {
        List<URLFileInfo> fileInfos = urlFilesStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UrlFilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new URLFileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = urlFilesStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.name() + "\"").body(file);
    }
}
