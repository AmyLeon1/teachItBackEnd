package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import com.teachit.teachItBackEnd.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationRepo regRepo;

    /**** Method to register a user ****/
    @PostMapping(path = "/registerUser")
    public User registerUser(@RequestBody User user) throws Exception {

        /* Checking if email already exists in the db*/
        //get email and store in variable
        String tempEmail = user.getEmail();

        //if tempEmail isn't blank enter if block
        if (tempEmail != null && !equals(tempEmail)) {
            // search for a user with tempEmail & assign it to userObj
            User userObj = registrationService.fetchUserByEmail(tempEmail);
            //if userObj isn't null(user is found/already registered with this email) - throw exception
            if (userObj != null) {
                throw new Exception("User with " + tempEmail + " is already registered");
            }
        }

        // otherwise pass in user to the registration service save method to be stored in the db
        User userObj = null;
        userObj = registrationService.saveUser(user);
        return userObj;
    }

    /**** Method to login ****/
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        //getting user email and password and storing them in variables
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        User userObj = null; //create user object as we have to return it

        /* if email and password are not null check the database for this combination of
         username & password and assign it to userObj*/
        if (tempEmail != null && tempPassword != null) {
            userObj = registrationService.fetchUserByEmailAndPassword(tempEmail, tempPassword);
        }

        /* if this combination is not found, userObj is null and an error is thrown */
        if (userObj == null) {
            throw new Exception("This user does not exist");
        }

        //if combo is correct and user is found return userObj
        return userObj;
    }


    /**** Method to get user details by searching email ****/
    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email) {
        return regRepo.findByEmail(email);
    }

    /**** Method to retrieve all users ****/
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return regRepo.findAll();
    }

}
