package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.controller.AppointmentController;
import com.teachit.teachItBackEnd.model.Comment;
import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo repo;

//    public Comment saveComment(Comment comment){
//
//        //call the inbuilt method of the repo
//        //save user
//        return repo.save(comment);
//
//    }

}
