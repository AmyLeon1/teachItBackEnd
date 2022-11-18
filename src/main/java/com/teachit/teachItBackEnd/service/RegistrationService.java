package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService {

    //autowire an object of the repo so we can use the methods
    //repo injected
    @Autowired
    private RegistrationRepo repo;

    public User saveUser(User user){

        //call the inbuilt method of the repo
        //save user
        return repo.save(user);

    }


    public User fetchUserByEmail(String email){
        //check
       return repo.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword(String email, String password){
        //check
        return repo.findByEmailAndPassword(email, password);

    }
}
