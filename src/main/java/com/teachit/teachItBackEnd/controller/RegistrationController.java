package com.teachit.teachItBackEnd.controller;


import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.model.Todo;
import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import com.teachit.teachItBackEnd.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RegistrationController {

    //use Autowired to inject dependency
    @Autowired
    private RegistrationService  registrationService;

    @Autowired
    private RegistrationRepo regRepo;
    //method whenever user submits the form
    //save data into the db
    //@RequestMapping to map it to a URL

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path= "/registerUser")
    public User registerUser(@RequestBody User user) throws Exception {

        //check if email already exists in db
        String tempEmail = user.getEmail();
        if(tempEmail != null && !equals(tempEmail)){

           User userObj = registrationService.fetchUserByEmail(tempEmail);
           if(userObj != null){
               throw new Exception("User with " + tempEmail + " is already registered");
           }
        }

        User userObj = null;
        //pass in user to the registration service save method
       userObj = registrationService.saveUser(user);
        return userObj;
    }

   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        //check if combo user id and password is present in db
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        User userObj = null; //create user object as we have to return it

        if(tempEmail !=null && tempPassword !=null){
           userObj = registrationService.fetchUserByEmailAndPassword(tempEmail, tempPassword);

        }

        if(userObj ==null){
            throw new Exception("This user does not exist");
        }
        return userObj;
    }

    //retrieve user details by searching their username
    //get request
    //username path variable

    //get user details by searching email
    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email){
        return regRepo.findByEmail(email);
        //return todoService.findById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return regRepo.findAll();
        //return todoService.findAll();
    }




}
