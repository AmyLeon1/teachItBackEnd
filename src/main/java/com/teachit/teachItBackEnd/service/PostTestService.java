package com.teachit.teachItBackEnd.service;


import com.teachit.teachItBackEnd.model.PostTest;
import com.teachit.teachItBackEnd.repository.PostTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostTestService {

   @Autowired
    private PostTestRepository repository;

    public PostTest saveNewPost(PostTest post){

        //call the inbuilt method of the repo
        //save user
        return repository.save(post);
    }


}
