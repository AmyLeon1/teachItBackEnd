package com.teachit.teachItBackEnd.service;

import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistrationService {

    //inject repository
    @Autowired
    private RegistrationRepo repo;

    /**** Method to save new user during registration ****/
    public User saveUser(User user) {
        //save user via the repository
        return repo.save(user);
    }

    /**** Method to retrieve a user by email ****/
    public User fetchUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    /**** Method to retrieve a user by email & password ****/
    public User fetchUserByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);

    }
}
