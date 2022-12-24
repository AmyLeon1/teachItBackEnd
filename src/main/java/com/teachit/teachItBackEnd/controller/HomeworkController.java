package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.HomeworkFile;
import com.teachit.teachItBackEnd.model.HomeworkResponse;
import com.teachit.teachItBackEnd.repository.HomeworkRepo;
import com.teachit.teachItBackEnd.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private HomeworkRepo homeworkRepo;


    @PostMapping("/homework/upload")
    public HomeworkResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return homeworkService.store(file);
    }

    @GetMapping("homework/{id}")
    public HomeworkFile getFile(@PathVariable String id) {

        return homeworkService.getFileById(id);

    }

    @GetMapping("/homework/list")
    public List<HomeworkResponse> getFileList() {
        return homeworkService.getFileList();
    }


    @GetMapping("/download")
    public void downloadFile(String fileName, HttpServletResponse res){
        res.setHeader("Content-Disposition", "attachment: filename"+fileName);
    }




}