package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.model.HomeworkFile;
import com.teachit.teachItBackEnd.model.HomeworkResponse;
import com.teachit.teachItBackEnd.repository.HomeworkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepo homeworkRepo;

    public HomeworkResponse store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        HomeworkFile homeworkFile = new HomeworkFile(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes());
        homeworkRepo.save(homeworkFile);
        return  mapToHomeworkResponse(homeworkFile);
    }

    private HomeworkResponse mapToHomeworkResponse(HomeworkFile homeworkFile) {
        return new HomeworkResponse(homeworkFile.getId(), homeworkFile.getType(), homeworkFile.getName());
    }


    public HomeworkFile getFileById(String id) {

        Optional<HomeworkFile> fileOptional = homeworkRepo.findById(id);

        if(fileOptional.isPresent()) {
            return fileOptional.get();
        }
        return null;
    }

    public List<HomeworkResponse> getFileList(){
        return homeworkRepo.findAll().stream().map(this::mapToHomeworkResponse).collect(Collectors.toList());
    }







}
